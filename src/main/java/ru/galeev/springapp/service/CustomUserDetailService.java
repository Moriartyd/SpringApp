package ru.galeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.UUID;

@Service("userDetailService")
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;

    private final String link = "http://192.168.1.70:8090";
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(s);
    }

    public boolean createUser(User user) {
        User userFromDb = userRepository.findUserByLogin(user.getLogin());
        if (userFromDb != null) {
            return false;
        }
        user.setRole(Role.USER.getAuthority());
        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format("Здравствуйте, %s %s!\n" +
                "Добро пожаловать в EventTracker\n" +
                "Посетите пожалуйста эту ссылку для активации:\n" +
                "%s/activate/%s",
                    user.getName(),
                    user.getSurname(),
                    link,
                    user.getActivationCode()
                    );
        mailService.send(user.getEmail(), "Код активации", message);
        user.setActive(false);
        userRepository.saveAndFlush(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.saveAndFlush(user);
        return true;
    }
}

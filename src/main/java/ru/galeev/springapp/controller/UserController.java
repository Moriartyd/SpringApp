//package ru.galeev.springapp.controller;
//
////import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.galeev.springapp.persistence.entity.User;
//import ru.galeev.springapp.persistence.repository.UserRepository;
//
//
////@Slf4j
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @GetMapping()
//    public String index(Model model) {
//        return "user/index";
//    }
//
//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findUserById(id);
//        if (user != null) {
//            model.addAttribute(user);
//            return "user/showuserpage";
//        }
//        return null;
//    }
//
//    @GetMapping("/log_in")
//    public String oldUser(Model model) {
//        model.addAttribute("user", new User());
//
//        return "user/log_in";
//    }
//
//    @GetMapping("/logging_in")
//    public String loginUser(@ModelAttribute("user") User user, Model model) {
//        User expected = userRepository.findUserByLogin(user.getLogin());
//        if (expected != null && expected.getPassword().equals(user.getPassword())) {
////            Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
//            return "user/edit_user";
//        }
//        return oldUser(model);
//    }
//
//    @GetMapping("/sign_up")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//
//        return "user/sign_up";
//    }
//
//    @PostMapping("createuser")
//    public String createUser(@ModelAttribute("user") User user) {
//        if (userRepository.findUserByLogin(user.getLogin()) == null &&
//                !user.getPassword().isEmpty()) {
//            userRepository.saveAndFlush(user);
////            log.info(String.format("User with login:%s id:%d created!",
////                    user.getLogin(), user.getId()));
//            return "user/showuserpage";
//        }
////        log.error(String.format("Can not create user with login:%s",
////                user.getLogin()));
//        return "user/index";
//    }
//}

package ru.akimov.springapp.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public Gson myGson() {
        return new GsonBuilder()
                .setExclusionStrategies(new HiddenAnnotationExclusionStrategy())
                .create();
    }
}

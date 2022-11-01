package ru.akimov.springapp.configs;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import lombok.NoArgsConstructor;
import ru.akimov.springapp.utils.Hidden;

@NoArgsConstructor
public class HiddenAnnotationExclusionStrategy implements ExclusionStrategy {
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(Hidden.class) != null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Hidden.class) != null;
    }
}

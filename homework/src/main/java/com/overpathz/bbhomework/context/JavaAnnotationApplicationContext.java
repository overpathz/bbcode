package com.overpathz.bbhomework.context;

import com.overpathz.bbhomework.context.annotation.Bean;
import com.overpathz.bbhomework.context.exception.NoSuchBeanException;
import com.overpathz.bbhomework.context.exception.NoUniqueBeanException;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.*;

import static java.util.stream.Collectors.*;

public class JavaAnnotationApplicationContext implements ApplicationContext {

     private final String packageName;
     private static final Class<Bean> BEAN_CLASS = Bean.class;
     private final Map<String, Object> objects = new HashMap<>();

    public JavaAnnotationApplicationContext(String packageName) {
        this.packageName = packageName;
        createBeans();
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        Map<String, T> allBeansBySpecificType = getAllBeans(beanType);
        if (allBeansBySpecificType.size() > 1) {
            throw new NoUniqueBeanException();
        }
        return allBeansBySpecificType.values().stream()
                .findAny().orElseThrow(NoSuchBeanException::new);
    }

    @Override
    public <T> T getBean(String name, Class<T> beanType) {
        Map<String, T> allBeans = getAllBeans(beanType);
        return allBeans.entrySet().stream()
                .filter(entry -> entry.getKey().equals(name))
                .map(Map.Entry::getValue)
                .findAny()
                .orElseThrow(NoSuchBeanException::new);
    }

    @Override
    public <T> Map<String, T> getAllBeans(Class<T> beanType) {
        return objects.entrySet().stream()
                .filter(entry -> beanType.isAssignableFrom(entry.getValue().getClass()))
                .collect(toMap(Map.Entry::getKey, entry -> beanType.cast(entry.getValue())));
    }

    private Set<Class<?>> getClassesFullNames() {
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(BEAN_CLASS);
    }

    @SneakyThrows
    private void createBeans() {
        for (Class<?> type : getClassesFullNames()) {
            objects.put(resolveBeanName(type), type.getConstructor().newInstance());
        }
    }

    private String resolveBeanName(Class<?> clazz) {
        String value = clazz.getAnnotation(BEAN_CLASS).value();
        return value.equals("null") ? getDefaultName(clazz) : value;
    }

    private String getDefaultName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return simpleName.replaceFirst("^.", String.valueOf(simpleName.charAt(0)).toLowerCase());
    }
}

package com.zervladpy.tallerpaco.Core.Utils.Managers;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Storage for dependency injection
 * */
public class DependencyManager {

    private final HashMap<Type, Object> dependencies;
    private static DependencyManager instance;

    private DependencyManager() {
        dependencies = new HashMap<>();
    }

    public static DependencyManager getInstance() {
        if (instance == null) {
            synchronized (DependencyManager.class) {
                instance = new DependencyManager();
            }
        }
        return instance;
    }

    public <T> void set(Class<T> clazz, T object) {

        if (dependencies.containsKey(clazz)) {
            throw new RuntimeException("Dependency " + clazz.getName() + " already exists");
        }

        dependencies.put(clazz, object);

    }

    public <T> T get(Class<T> clazz) {
        if (!dependencies.containsKey(clazz)) {
            throw new RuntimeException("Dependency " + clazz.getName() + " does not exist");
        }

        return clazz.cast(dependencies.get(clazz));
    }

    public void remove(Class<?> clazz) {
        if (!dependencies.containsKey(clazz)) {
            throw new RuntimeException("Dependency " + clazz.getName() + " does not exist");
        }
        dependencies.remove(clazz);
    }

    public void clear() {
        dependencies.clear();
    }

}

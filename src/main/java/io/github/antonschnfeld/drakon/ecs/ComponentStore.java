package io.github.antonschnfeld.drakon.ecs;

import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentStore {
    private final Map<Class<?>, SparseSet<?>> components;

    public ComponentStore() {
        this.components = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void addComponent(long entity, Object component) {
        int id = EntityUtil.getId(entity);

        SparseSet<Object> set = (SparseSet<Object>) components.get(component.getClass());
        if (set == null) {
            set = new SparseSet<>();
            components.put(component.getClass(), set);
        }

        set.set(id, component);
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(long entity, Class<T> component) {
        int id = EntityUtil.getId(entity);

        SparseSet<Object> set = (SparseSet<Object>) components.get(component);
        if (set == null) {
            return null;
        }

        return (T) set.get(id);
    }

    public void removeComponent(long entity, Object component) {
        SparseSet<?> set = components.get(component.getClass());
        if (set == null) {
            return;
        }

        set.remove(EntityUtil.getId(entity));
    }

    public void removeComponents(long entity) {
        for (SparseSet<?> set : components.values()) {
            set.remove(EntityUtil.getId(entity));
        }
    }

    public List<Object> getComponents(long entity) {
        List<Object> list = new ArrayList<>();

        for (SparseSet<?> set : components.values()) {
            Object object = set.get(EntityUtil.getId(entity));
            if (object != null) {
                list.add(object);
            }
        }

        return list;
    }
}

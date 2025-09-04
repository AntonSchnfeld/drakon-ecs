package io.github.antonschnfeld.drakon.ecs;

import java.util.ArrayList;
import java.util.List;

public class EntityWorldImpl implements EntityWorld {
    private final EntityStore entityStore;
    private final ComponentStore componentStore;

    public EntityWorldImpl() {
        this.entityStore = new EntityStore();
        this.componentStore = new ComponentStore();
    }

    @Override
    public long createEntity(Object... components) {
        long entity = entityStore.createEntity();

        for (int i = 0; i < components.length; i++) {
            componentStore.addComponent(entity, components[i]);
        }

        return entity;
    }

    @Override
    public List<Object> getComponents(long entity) {
        if (!entityStore.contains(entity)) return new ArrayList<>();
        return componentStore.getComponents(entity);
    }

    @Override
    public void addComponent(long entity, Object component) {
        if (!entityStore.contains(entity)) {
            return;
        }
        componentStore.addComponent(entity, component);
    }

    @Override
    public <T> T getComponent(long entity, Class<T> componentType) {
        if (!entityStore.contains(entity)) {
            return null;
        }
        return componentStore.getComponent(entity, componentType);
    }

    @Override
    public void removeComponent(long entity, Class<?> component) {
        if (!entityStore.contains(entity)) {
            return;
        }
        componentStore.removeComponent(entity, component);
    }

    @Override
    public void removeComponents(long entity) {
        if (!entityStore.contains(entity)) {
            return;
        }
        componentStore.removeComponents(entity);
    }

    @Override
    public void destroyEntity(long entity) {
        if (!entityStore.contains(entity)) {
            return;
        }
        entityStore.deleteEntity(entity);
        componentStore.removeComponents(entity);
    }

    @Override
    public boolean isEntityAlive(long entity) {
        return entityStore.contains(entity);
    }
}

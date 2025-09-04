package io.github.antonschnfeld.drakon.ecs;

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
            if (components[i] == null) {
                throw new IllegalArgumentException("null components are not allowed");
            }
            componentStore.addComponent(entity, components[i]);
        }

        return entity;
    }

    @Override
    public List<Object> getComponents(long entity) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        return componentStore.getComponents(entity);
    }

    @Override
    public void addComponent(long entity, Object component) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        if (component == null) {
            throw new IllegalArgumentException("null components are not allowed");
        }
        componentStore.addComponent(entity, component);
    }

    @Override
    public <T> T getComponent(long entity, Class<T> componentType) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        return componentStore.getComponent(entity, componentType);
    }

    @Override
    public void removeComponent(long entity, Class<?> component) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        componentStore.removeComponent(entity, component);
    }

    @Override
    public void removeComponents(long entity) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        componentStore.removeComponents(entity);
    }

    @Override
    public void destroyEntity(long entity) {
        if (!entityStore.contains(entity)) {
            throw new IllegalArgumentException("entity does not exist");
        }
        entityStore.deleteEntity(entity);
        componentStore.removeComponents(entity);
    }

    @Override
    public boolean isEntityAlive(long entity) {
        return entityStore.contains(entity);
    }
}

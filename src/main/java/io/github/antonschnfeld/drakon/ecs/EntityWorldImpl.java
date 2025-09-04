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
            componentStore.addComponent(entity, components[i]);
        }

        return entity;
    }

    @Override
    public List<Object> getComponents(long entity) {
        return componentStore.getComponents(entity);
    }

    @Override
    public void addComponent(long entity, Object component) {
        componentStore.addComponent(entity, component);
    }

    @Override
    public <T> T getComponent(long entity, Class<T> componentType) {
        return componentStore.getComponent(entity, componentType);
    }

    @Override
    public void removeComponent(long entity, Class<?> component) {
        componentStore.removeComponent(entity, component);
    }

    @Override
    public void removeComponents(long entity) {
        componentStore.removeComponents(entity);
    }

    @Override
    public void destroyEntity(long entity) {
        entityStore.deleteEntity(entity);
        componentStore.removeComponents(entity);
    }

    @Override
    public boolean isEntityAlive(long entity) {
        return entityStore.contains(entity);
    }
}

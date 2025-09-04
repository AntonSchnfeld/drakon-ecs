package io.github.antonschnfeld.drakon.ecs;

import java.util.List;

public interface EntityWorld {
    long createEntity(Object... components);

    List<Object> getComponents(long entity);

    void addComponent(long entity, Object component);

    <T> T getComponent(long entity, Class<T> componentType);

    void removeComponent(long entity, Class<?> component);

    void removeComponents(long entity);

    void destroyEntity(long entity);

    boolean isEntityAlive(long entity);
}

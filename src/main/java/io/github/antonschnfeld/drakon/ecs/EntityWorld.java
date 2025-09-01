package io.github.antonschnfeld.drakon.ecs;

public interface EntityWorld {
    long createEntity(Object... components);

    void destroyEntity(long entity);

    boolean isEntityAlive(long entity);
}

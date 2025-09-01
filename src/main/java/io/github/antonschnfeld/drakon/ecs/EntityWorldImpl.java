package io.github.antonschnfeld.drakon.ecs;

public class EntityWorldImpl implements EntityWorld {
    private final EntityStore entityStore;

    public EntityWorldImpl(EntityStore entityStore) {
        this.entityStore = entityStore;
    }

    @Override
    public long createEntity(Object... components) {
        return entityStore.createEntity();
    }

    @Override
    public void destroyEntity(long entity) {
        entityStore.deleteEntity(entity);
    }

    @Override
    public boolean isEntityAlive(long entity) {
        return entityStore.contains(entity);
    }
}

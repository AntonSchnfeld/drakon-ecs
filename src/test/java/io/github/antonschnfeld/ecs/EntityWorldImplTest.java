package io.github.antonschnfeld.ecs;

import io.github.antonschnfeld.drakon.ecs.EntityWorld;
import io.github.antonschnfeld.drakon.ecs.EntityWorldImpl;

class EntityWorldImplTest extends EntityWorldTest {
    @Override
    protected EntityWorld getEntityWorld() {
        return new EntityWorldImpl();
    }
}

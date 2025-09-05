package io.github.antonschnfeld.drakon.ecs.primitive;

import io.github.antonschnfeld.drakon.ecs.EntityWorld;

public interface PrimitiveComponent {
    int getId();

    EntityWorld getWorld();
}

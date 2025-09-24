package io.github.antonschnfeld.drakon.ecs.util;

import io.github.antonschnfeld.drakon.ecs.Archetype;
import io.github.antonschnfeld.drakon.ecs.ArchetypeLocation;
import io.github.antonschnfeld.drakon.ecs.ComponentSet;
import io.github.antonschnfeld.drakon.ecs.SparseSet;

import java.util.HashMap;

public class ArchetypeManager {
    private final SparseSet<ArchetypeLocation> archetypeLocations;
    private final HashMap<ComponentSet, Archetype> archetypes;

    public ArchetypeManager() {
        this.archetypeLocations = new SparseSet<>();
        this.archetypes = new HashMap<>();
    }

    public Archetype getArchetype(ComponentSet componentSet) {
        return archetypes.get(componentSet);
    }

    public ArchetypeLocation getArchetypeLocation(int id) {
        return archetypeLocations.get(id);
    }

    public void moveArchetype()
}

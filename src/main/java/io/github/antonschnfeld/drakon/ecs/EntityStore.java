package io.github.antonschnfeld.drakon.ecs;

import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;

import java.util.*;

public class EntityStore {
    private final Deque<Integer> freeIds;
    private final List<Integer> generations;

    public EntityStore() {
        freeIds = new ArrayDeque<>();
        generations = new ArrayList<>();
    }

    public boolean contains(long entity) {
        int id = EntityUtil.getId(entity);
        if (id >= generations.size()) {
            return false;
        }
        return generations.get(id) == EntityUtil.getGeneration(entity);
    }

    public long createEntity() {
        long entity = 0;
        int generation;
        int id;
        if (!freeIds.isEmpty()) {
            id = freeIds.pop();
            generation = generations.get(id);
        } else {
            id = generations.size();
            generation = 0;
            generations.add(generation);
        }
        entity = EntityUtil.setId(entity, id);
        entity = EntityUtil.setGeneration(entity, generation);
        return entity;
    }

    public void deleteEntity(long entity) {
        int id = EntityUtil.getId(entity);
        if (id >= generations.size()) {
            return;
        }
        generations.set(id, generations.get(id) + 1); // increment generation
        freeIds.push(id); // recycle ID
    }
}

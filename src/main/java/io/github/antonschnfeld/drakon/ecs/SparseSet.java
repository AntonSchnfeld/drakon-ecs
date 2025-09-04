package io.github.antonschnfeld.drakon.ecs;

import java.util.ArrayList;

public class SparseSet<T> {
    private final ArrayList<Integer> sparse;
    private final ArrayList<Integer> dense;
    private final ArrayList<T> components;

    public SparseSet() {
        sparse = new ArrayList<>();
        dense = new ArrayList<>();
        components = new ArrayList<>();
    }

    public T get(int id) {
        if (id >= sparse.size()) return null;
        int denseIdx = sparse.get(id);
        if (denseIdx == -1) return null;
        return components.get(denseIdx);
    }

    public void set(int id, T component) {
        ensureCapacity(id);
        int denseIdx = dense.size();
        sparse.set(id, denseIdx);
        dense.add(id);
        components.add(component);
    }

    public void remove(int id) {
        if (id >= sparse.size()) return;
        int denseIdx = sparse.get(id);
        if (denseIdx == -1) return;

        // Move last element to fill gap
        int lastIdx = dense.size() - 1;
        if (denseIdx != lastIdx) {
            components.set(denseIdx, components.get(lastIdx));
            int lastEntityId = dense.get(lastIdx);
            dense.set(denseIdx, lastEntityId);
            sparse.set(lastEntityId, denseIdx);
        }

        sparse.set(id, -1);
        dense.removeLast();
        components.removeLast();
    }

    private void ensureCapacity(int id) {
        while (sparse.size() <= id) {
            sparse.add(-1);
        }
    }
}
package io.github.antonschnfeld.drakon.ecs;

import java.util.Arrays;

public class ComponentSet {
    private final Class<?>[] componentTypes;
    private final int hashCode;

    public ComponentSet(Class<?>[] componentTypes) {
        this.componentTypes = componentTypes;
        this.hashCode = Arrays.hashCode(componentTypes);
    }

    public Class<?>[] getComponentTypes() {
        return componentTypes;
    }

    public boolean contains(Class<?> componentType) {
        for (Class<?> c : componentTypes) {
            if (c.equals(componentType)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(ComponentSet componentSet) {
        for (Class<?> c : componentSet.getComponentTypes()) {
            if (!contains(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentSet componentSet = (ComponentSet) o;
        return Arrays.equals(componentTypes, componentSet.componentTypes);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}

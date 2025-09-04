package io.github.antonschnfeld;

import io.github.antonschnfeld.drakon.ecs.EntityWorld;
import io.github.antonschnfeld.drakon.ecs.EntityWorldImpl;
import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityWorld ecs = new EntityWorldImpl();

        long entity = ecs.createEntity("Component", 2);

        System.out.println("Created: " + EntityUtil.toString(entity) + ": " + ecs.getComponents(entity));
        System.out.println("Component 1 of " + EntityUtil.toString(entity) + ": " + ecs.getComponent(entity, String.class));

        ecs.destroyEntity(entity);

        System.out.println("Destroyed: " + EntityUtil.toString(entity) + ": " + ecs.getComponents(entity));

        entity = ecs.createEntity(2, 3, "Different Components");

        System.out.println("Created: " + EntityUtil.toString(entity) + ": " + ecs.getComponents(entity));

        long anotherEntity = ecs.createEntity(10, 11, new Date());

        System.out.println("Created: " + EntityUtil.toString(anotherEntity) + ": " + ecs.getComponents(anotherEntity));
        System.out.println("Component 2 of " + EntityUtil.toString(anotherEntity) + ": " + ecs.getComponent(anotherEntity, Date.class));
    }
}

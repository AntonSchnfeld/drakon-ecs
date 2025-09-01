package io.github.antonschnfeld;

import io.github.antonschnfeld.drakon.ecs.EntityStore;
import io.github.antonschnfeld.drakon.ecs.EntityWorld;
import io.github.antonschnfeld.drakon.ecs.EntityWorldImpl;
import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;

public class Main {
    public static void main(String[] args) {
        EntityWorld ecs = new EntityWorldImpl(new EntityStore());

        long entity = ecs.createEntity();

        System.out.println(EntityUtil.toString(entity));

        ecs.destroyEntity(entity);
        System.out.println("Destroyed Entity: " + EntityUtil.toString(entity));
        System.out.println("Is Entity alive: " + ecs.isEntityAlive(entity));

        entity = ecs.createEntity();
        System.out.println("Created Entity: " + EntityUtil.toString(entity));
        System.out.println("Is Entity alive: " + ecs.isEntityAlive(entity));
        long anotherEntity = ecs.createEntity();
        System.out.println("Created another Entity: " + EntityUtil.toString(anotherEntity));
        System.out.println("Is Entity alive: " + ecs.isEntityAlive(anotherEntity));
    }
}

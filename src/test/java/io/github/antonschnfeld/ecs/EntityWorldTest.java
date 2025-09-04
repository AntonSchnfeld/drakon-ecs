package io.github.antonschnfeld.ecs;

import io.github.antonschnfeld.drakon.ecs.EntityWorld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class EntityWorldTest {
    private static final int MANY = 100_000;

    private EntityWorld ecs;

    protected abstract EntityWorld getEntityWorld();

    @BeforeEach
    public void setUp() {
        ecs = getEntityWorld();
    }

    @Test
    public void createEntity_thenEntityExists() {
        long entity = Assertions.assertDoesNotThrow(() -> ecs.createEntity());
        Assertions.assertTrue(ecs.isEntityAlive(entity));
    }

    @Test
    public void dontCreateEntity_thenEntityDoesNotExist() {
        Assertions.assertFalse(ecs.isEntityAlive(0));
    }

    @Test
    public void createManyEntities_thenEntitiesExists() {
        long[] entities = new long[MANY];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = Assertions.assertDoesNotThrow(() -> ecs.createEntity());
        }

        for (long entity : entities) {
            Assertions.assertTrue(ecs.isEntityAlive(entity));
        }
    }

    @Test
    public void destroyEntity_thenEntityDoesNotExist() {
        long entity = Assertions.assertDoesNotThrow(() -> ecs.createEntity());
        Assertions.assertTrue(ecs.isEntityAlive(entity));

        Assertions.assertDoesNotThrow(() -> ecs.destroyEntity(entity));

        Assertions.assertFalse(ecs.isEntityAlive(entity));
    }

    @Test
    public void destroyNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ecs.destroyEntity(0));
    }

    @Test
    public void destroyManyEntities_thenEntitiesDontExists() {
        long[] entities = new long[MANY];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = Assertions.assertDoesNotThrow(() -> ecs.createEntity());
        }

        for (int i = 0; i < entities.length; i++) {
            final int finalI = i;
            Assertions.assertTrue(ecs.isEntityAlive(entities[i]));
            Assertions.assertDoesNotThrow(() -> ecs.destroyEntity(entities[finalI]));
        }

        for (long entity : entities) {
            Assertions.assertFalse(ecs.isEntityAlive(entity));
        }
    }

    @Test
    public void createEntityWithComponents_thenEntityHasComponents() {
        Object[] components = new Object[]{"Component 1", 15};
        long entity = Assertions.assertDoesNotThrow(() -> ecs.createEntity(components));
        Assertions.assertTrue(ecs.isEntityAlive(entity));

        Assertions.assertArrayEquals(components, ecs.getComponents(entity).toArray());

        Assertions.assertEquals(components[0], ecs.getComponent(entity, String.class));
        Assertions.assertEquals(components[1], ecs.getComponent(entity, Integer.class));
    }

    @Test
    public void createManyEntityWithComponents_thenEntitiesHaveComponents() {
        long[] entities = new long[MANY];
        String stringComponent = "Component 1";
        for (int i = 0; i < entities.length; i++) {
            final int finalI = i;
            entities[i] = Assertions.assertDoesNotThrow(() -> ecs.createEntity());
            Assertions.assertDoesNotThrow(() -> ecs.addComponent(entities[finalI], stringComponent));
            Assertions.assertDoesNotThrow(() -> ecs.addComponent(entities[finalI], finalI));
        }

        for (int i = 0; i < entities.length; i++) {
            Assertions.assertTrue(ecs.isEntityAlive(entities[i]));

            Assertions.assertArrayEquals(new Object[]{stringComponent, i}, ecs.getComponents(entities[i]).toArray());

            Assertions.assertEquals(stringComponent, ecs.getComponent(entities[i], String.class));
            Assertions.assertEquals(i, ecs.getComponent(entities[i], Integer.class));
        }
    }

    @Test
    public void removeComponent_thenComponentIsRemoved() {
        long entity = Assertions.assertDoesNotThrow(() -> ecs.createEntity("Component"));
        Assertions.assertTrue(ecs.isEntityAlive(entity));
        Assertions.assertDoesNotThrow(() -> ecs.removeComponent(entity, String.class));
        Assertions.assertNull(ecs.getComponent(entity, String.class));
        Assertions.assertArrayEquals(new Object[0], ecs.getComponents(entity).toArray());
    }

    @Test
    public void addComponentToExistingEntity_thenEntityHasComponent() {
        long entity = ecs.createEntity();
        String component = "Test Component";

        Assertions.assertDoesNotThrow(() -> ecs.addComponent(entity, component));
        Assertions.assertEquals(component, ecs.getComponent(entity, String.class));
        Assertions.assertTrue(ecs.getComponents(entity).contains(component));
    }

    @Test
    public void addMultipleComponentsOfSameType_thenOnlyLastComponentExists() {
        long entity = ecs.createEntity();
        String first = "First";
        String second = "Second";

        ecs.addComponent(entity, first);
        ecs.addComponent(entity, second);

        Assertions.assertEquals(second, ecs.getComponent(entity, String.class));
        Assertions.assertEquals(1, ecs.getComponents(entity).size());
    }

    @Test
    public void getComponentOfNonExistentType_thenReturnsNull() {
        long entity = ecs.createEntity("String Component");
        Assertions.assertNull(ecs.getComponent(entity, Integer.class));
    }

    @Test
    public void removeAllComponents_thenEntityHasNoComponents() {
        long entity = ecs.createEntity("Component1", 42, 3.14);

        ecs.removeComponents(entity);

        Assertions.assertTrue(ecs.getComponents(entity).isEmpty());
        Assertions.assertNull(ecs.getComponent(entity, String.class));
        Assertions.assertNull(ecs.getComponent(entity, Integer.class));
        Assertions.assertNull(ecs.getComponent(entity, Double.class));
    }

    @Test
    public void addComponentToNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ecs.addComponent(0, "Component"));
    }

    @Test
    public void getComponentFromNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ecs.getComponent(0, String.class));
    }

    @Test
    public void getComponentsFromNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ecs.getComponents(0));
    }

    @Test
    public void removeComponentFromNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ecs.removeComponent(0, String.class));
    }

    @Test
    public void removeComponentsFromNonExistentEntity_thenThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ecs.removeComponents(0));
    }

    @Test
    public void removeNonExistentComponentType_thenNoEffect() {
        long entity = ecs.createEntity("String Component");

        Assertions.assertDoesNotThrow(() -> ecs.removeComponent(entity, Integer.class));
        Assertions.assertEquals("String Component", ecs.getComponent(entity, String.class));
    }

    @Test
    public void destroyAndRecreateEntity_thenNewEntityHasDifferentHandle() {
        long entity1 = ecs.createEntity();
        ecs.destroyEntity(entity1);
        long entity2 = ecs.createEntity();

        Assertions.assertNotEquals(entity1, entity2);
        Assertions.assertFalse(ecs.isEntityAlive(entity1));
        Assertions.assertTrue(ecs.isEntityAlive(entity2));
    }

    @Test
    public void createEntityAfterDestroy_thenOldHandleStillInvalid() {
        long entity1 = ecs.createEntity("Component");
        ecs.destroyEntity(entity1);

        // Create many new entities to potentially reuse slots
        for (int i = 0; i < 10; i++) {
            ecs.createEntity();
        }

        Assertions.assertFalse(ecs.isEntityAlive(entity1));
    }

    @Test
    public void createEntityWithNullComponent_thenThrows() {
        // Behavior depends on your implementation - might throw or ignore
        Assertions.assertThrows(IllegalArgumentException.class, () -> ecs.createEntity((Object) null));
    }

    @Test
    public void createEntityWithEmptyComponents_thenEntityExists() {
        long entity = ecs.createEntity();
        Assertions.assertTrue(ecs.isEntityAlive(entity));
        Assertions.assertTrue(ecs.getComponents(entity).isEmpty());
    }

    @Test
    public void addNullComponent_thenThrows() {
        long entity = ecs.createEntity();
        // Define expected behavior in your implementation
        Assertions.assertThrows(IllegalArgumentException.class, () -> ecs.addComponent(entity, null));
    }
}

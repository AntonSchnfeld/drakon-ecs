package io.github.antonschnfeld.ecs;

import io.github.antonschnfeld.drakon.ecs.EntityStore;
import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EntityStoreTest {
    private EntityStore store;

    @BeforeEach
    void setUp() {
        store = new EntityStore();
    }

    @Test
    void createEntity_shouldReturnValidEntity() {
        long entity = store.createEntity();
        assertTrue(store.contains(entity));
    }

    @Test
    void contains_shouldReturnFalseForUnknownEntity() {
        long fakeEntity = 123456789L;
        assertFalse(store.contains(fakeEntity));
    }

    @Test
    void deleteEntity_shouldInvalidateEntity() {
        long entity = store.createEntity();
        assertTrue(store.contains(entity));

        store.deleteEntity(entity);
        assertFalse(store.contains(entity));
    }

    @Test
    void deleteEntity_shouldRecycleIdAndIncrementGeneration() {
        long e1 = store.createEntity();
        int id1 = EntityUtil.getId(e1);
        int gen1 = EntityUtil.getGeneration(e1);

        store.deleteEntity(e1);
        long e2 = store.createEntity();
        int id2 = EntityUtil.getId(e2);
        int gen2 = EntityUtil.getGeneration(e2);

        assertEquals(id1, id2);
        assertTrue(gen2 > gen1);
    }

    @Test
    void createEntity_shouldAllowMultipleEntitiesToCoexist() {
        long e1 = store.createEntity();
        long e2 = store.createEntity();
        long e3 = store.createEntity();

        assertTrue(store.contains(e1));
        assertTrue(store.contains(e2));
        assertTrue(store.contains(e3));

        store.deleteEntity(e2);

        assertTrue(store.contains(e1));
        assertFalse(store.contains(e2));
        assertTrue(store.contains(e3));
    }

    @Test
    void deleteEntity_shouldNotThrowForNonExistentEntity() {
        long fakeEntity = 987654321L;
        assertDoesNotThrow(() -> store.deleteEntity(fakeEntity));
    }

    @Test
    void oldEntityReference_shouldBeInvalidAfterIdRecycling() {
        long e1 = store.createEntity();
        store.deleteEntity(e1);

        long e2 = store.createEntity();

        assertFalse(store.contains(e1));
        assertTrue(store.contains(e2));
    }

    @Test
    void createManyEntities_shouldYieldUniqueIdsBeforeRecycling() {
        Set<Integer> ids = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            long entity = store.createEntity();
            int id = EntityUtil.getId(entity);
            assertFalse(ids.contains(id));
            ids.add(id);
        }
    }

    @Test
    void deleteEntity_shouldNotThrowForNegativeId() {
        long negativeIdEntity = EntityUtil.setId(0L, -1);
        assertDoesNotThrow(() -> store.deleteEntity(negativeIdEntity));
    }

    @Test
    void contains_shouldReturnFalseForNegativeId() {
        long negativeIdEntity = EntityUtil.setId(0L, -1);
        assertFalse(store.contains(negativeIdEntity));
    }
}

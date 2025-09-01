package io.github.antonschnfeld.ecs.util;

import io.github.antonschnfeld.drakon.ecs.util.EntityUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityUtilTest {

    @Test
    void setId_shouldStoreAndGetCorrectly() {
        long entity = 0L;
        int id = 123456789;
        entity = EntityUtil.setId(entity, id);
        assertEquals(id, EntityUtil.getId(entity));
    }

    @Test
    void setGeneration_shouldStoreAndGetCorrectly() {
        long entity = 0L;
        int generation = 987654321;
        entity = EntityUtil.setGeneration(entity, generation);
        assertEquals(generation, EntityUtil.getGeneration(entity));
    }

    @Test
    void getNextGeneration_shouldIncrementGenerationOnly() {
        long entity = 0L;
        entity = EntityUtil.setGeneration(entity, 5);
        entity = EntityUtil.setId(entity, 42);

        long next = EntityUtil.getNextGeneration(entity);

        assertEquals(6, EntityUtil.getGeneration(next));
        assertEquals(42, EntityUtil.getId(next));
    }

    @Test
    void idEquals_shouldReturnTrueForSameId() {
        long e1 = 0L;
        long e2 = 0L;
        e1 = EntityUtil.setId(e1, 42);
        e1 = EntityUtil.setGeneration(e1, 1);
        e2 = EntityUtil.setId(e2, 42);
        e2 = EntityUtil.setGeneration(e2, 999);

        assertTrue(EntityUtil.idEquals(e1, e2));
    }

    @Test
    void idEquals_shouldReturnFalseForDifferentId() {
        long e1 = 0L;
        long e2 = 0L;
        e1 = EntityUtil.setId(e1, 42);
        e2 = EntityUtil.setId(e2, 43);

        assertFalse(EntityUtil.idEquals(e1, e2));
    }

    @Test
    void toString_shouldReturnFormattedString() {
        long entity = 0L;
        entity = EntityUtil.setId(entity, 123);
        entity = EntityUtil.setGeneration(entity, 456);

        String expected = "Entity[id=123, generation=456]";
        assertEquals(expected, EntityUtil.toString(entity));
    }

    @Test
    void setIdAndGeneration_shouldNotAffectEachOther() {
        long entity = 0L;
        entity = EntityUtil.setId(entity, 111);
        entity = EntityUtil.setGeneration(entity, 222);

        assertEquals(111, EntityUtil.getId(entity));
        assertEquals(222, EntityUtil.getGeneration(entity));
    }

    @Test
    void setGenerationAndId_shouldNotAffectEachOther() {
        long entity = 0L;
        entity = EntityUtil.setGeneration(entity, 333);
        entity = EntityUtil.setId(entity, 444);

        assertEquals(444, EntityUtil.getId(entity));
        assertEquals(333, EntityUtil.getGeneration(entity));
    }
}

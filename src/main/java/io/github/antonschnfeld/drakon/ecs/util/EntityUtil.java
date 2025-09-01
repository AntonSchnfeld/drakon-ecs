package io.github.antonschnfeld.drakon.ecs.util;

public final class EntityUtil {

    public static final int GENERATION_BIT = 32;

    public static final int GENERATION_SIZE = 32;

    public static final int ID_BIT = 0;

    public static final int ID_SIZE = 32;

    private EntityUtil() {
        // Prevent instantiation
    }

    public static int getGeneration(long entity) {
        return (int) LongUtil.getBitRange(entity, GENERATION_BIT, GENERATION_SIZE);
    }

    public static long setGeneration(long entity, int generation) {
        return LongUtil.setBitRange(entity, GENERATION_BIT, GENERATION_SIZE, generation);
    }

    public static int getId(long entity) {
        return (int) LongUtil.getBitRange(entity, ID_BIT, ID_SIZE);
    }

    public static long setId(long entity, int id) {
        return LongUtil.setBitRange(entity, ID_BIT, ID_SIZE, id);
    }

    public static long getNextGeneration(long entity) {
        return LongUtil.addToBitRange(entity, GENERATION_BIT, GENERATION_SIZE, 1);
    }

    public static boolean idEquals(long entity1, long entity2) {
        return getId(entity1) == getId(entity2);
    }

    public static String toString(long entity) {
        return String.format("Entity[id=%d, generation=%d]", getId(entity), getGeneration(entity));
    }
}

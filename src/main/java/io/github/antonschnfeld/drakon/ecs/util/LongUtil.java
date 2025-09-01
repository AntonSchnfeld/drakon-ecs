package io.github.antonschnfeld.drakon.ecs.util;

public class LongUtil {

    public static boolean getBit(long value, int bit) {
        return ((value >>> bit) & 1L) == 1L;
    }

    public static long setBit(long value, int bit, boolean bitValue) {
        long mask = 1L << bit;
        return bitValue ? (value | mask) : (value & ~mask);
    }

    public static long getBitRange(long value, int startBit, int length) {
        long mask = (1L << length) - 1;
        return (value >>> startBit) & mask;
    }

    public static long setBitRange(long value, int startBit, int length, long bitRangeValue) {
        long mask = ((1L << length) - 1) << startBit;
        return (value & ~mask) | ((bitRangeValue << startBit) & mask);
    }

    public static long addToBitRange(long value, int startBit, int length, long valueToAdd) {
        long current = getBitRange(value, startBit, length);
        long updated = (current + valueToAdd) & ((1L << length) - 1);
        return setBitRange(value, startBit, length, updated);
    }
}

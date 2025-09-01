package io.github.antonschnfeld.ecs.util;

import io.github.antonschnfeld.drakon.ecs.util.LongUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongUtilTest {

    @Test
    void getBit_shouldReturnCorrectBitValue() {
        long value = 0b1;
        assertTrue(LongUtil.getBit(value, 0));
        assertFalse(LongUtil.getBit(value, 1));
    }

    @Test
    void setBit_shouldSetBitToTrueOrFalse() {
        long value = 0b0L;
        long updated = LongUtil.setBit(value, 0, true);
        assertEquals(0b1, updated);

        updated = LongUtil.setBit(updated, 0, false);
        assertEquals(0b0, updated);
    }

    @Test
    void getBitRange_shouldExtractCorrectRange() {
        long value = 0b110011;
        long range = LongUtil.getBitRange(value, 0, 4);
        assertEquals(0b11, range);

        range = LongUtil.getBitRange(value, 4, 4);
        assertEquals(0b11, range);
    }

    @Test
    void setBitRange_shouldReplaceCorrectBits() {
        long value = 0;
        long updated = LongUtil.setBitRange(value, 0, 4, 0b1111);
        assertEquals(0b1111L, updated); // bits 0-3 set

        updated = LongUtil.setBitRange(updated, 4, 4, 0b1010);
        assertEquals(0b10101111L, updated); // bits 4-7 set
    }

    @Test
    void addToBitRange_shouldAddCorrectly() {
        long value = 0b0000L;
        long updated = LongUtil.setBitRange(value, 0, 4, 0b0010); // bits 0-3 = 2
        updated = LongUtil.addToBitRange(updated, 0, 4, 0b0011);   // add 3 → 5
        assertEquals(0b0101L, updated & 0b1111L); // mask to check only bits 0-3
    }


    @Test
    void integration_shouldMaintainConsistency() {
        long value = 0L;
        value = LongUtil.setBit(value, 1, true);
        value = LongUtil.setBit(value, 3, true);
        assertTrue(LongUtil.getBit(value, 1));
        assertTrue(LongUtil.getBit(value, 3));

        long range = LongUtil.getBitRange(value, 0, 4);
        assertEquals(0b1010L, range);
    }
}

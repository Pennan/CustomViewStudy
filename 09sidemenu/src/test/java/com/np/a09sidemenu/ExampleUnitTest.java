package com.np.a09sidemenu;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(0, 9 & 2);
        assertEquals(-1, -8 | 7);
        assertEquals(1, ~10 & 3);
        assertEquals(268435455, -4 >>> 4);
        assertEquals(16, 4 << 1 << 1);
        assertEquals(1, ~-5 >> 2);
        assertEquals(1, 2 & 4 | 9 >> 3);
        assertEquals(0, 8 & ~0xFF);
        assertEquals(0, 10 & 4);
        assertEquals(0, 010 & 4);
        assertEquals(0, 0x10 & 4);
        assertEquals(-3, 5 | ~3);
    }
}
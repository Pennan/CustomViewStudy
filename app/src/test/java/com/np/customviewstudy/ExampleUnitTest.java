package com.np.customviewstudy;

import android.graphics.Rect;
import android.graphics.RectF;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        RectF rectF = new RectF(12.4f, 20.8f, 100.3f, 202.9f);
        Rect rect = new Rect(12, 20, 100, 202);
        rectF.round(rect);
        assertEquals(20, rect.top);
    }
}
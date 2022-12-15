package com.example.a3laboratorinis;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void Test01delAlgorithm() {
        String bottomString = "50-2";
        String expectedString = "50-";
        String newBottomString = "";
        String stringAfterDel = DeleteAlgorithm.delAlgorithm(bottomString, newBottomString);

        assertEquals(expectedString, stringAfterDel);
    }

    @Test
    public void Test02delAlgorithm() {
        String bottomString = "";
        String expectedString = "0";
        String newBottomString = "";
        String stringAfterDel = DeleteAlgorithm.delAlgorithm(bottomString, newBottomString);

        assertEquals(expectedString, stringAfterDel);
    }

    @Test
    public void Test03delAlgorithm() {
        String bottomString = null;
        String expectedString = "0";
        String newBottomString = "";
        String stringAfterDel = DeleteAlgorithm.delAlgorithm(bottomString, newBottomString);

        assertEquals(expectedString, stringAfterDel);
    }
}
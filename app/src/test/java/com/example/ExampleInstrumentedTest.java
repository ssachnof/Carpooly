package com.example;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest extends TestCase {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        //Context appContext = InstrumentationRegistry.getTargetContext();
//
//        //assertEquals("com.example.carpooly", appContext.getPackageName());
//        assertEquals(1, 1);
//    }
    @Test
    public void test1_sample(){
        assertEquals(4, 2+2);
    }
}

package com.example;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.carpooly.Model.LoginModel;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginModelTest {

    @Test
    public void signInUser() {
    }

    @Ignore // (expected= Exception.class)
    public void checkLoginModelException() {
        LoginModel.getInstance();
    }

    @Test
    public void write() {
    }

    @Test
    public void goodLoginModelTest() {
        Context context = getContext();
        LoginModel l = LoginModel.getInstance("mammoth11@comcast.net", "test1234", context);
        LoginModel l2 = LoginModel.getInstance();
        assertNotNull(l);
        assertNotNull(l2);
        assertSame(l, l2);
    }

    @Test
    public void read() {
    }
}
package test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.carpooly.Model.RideModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RideModelTest {

    @Test
    public void setDriverDisplayName() {
    }

    @Test(expected = NullPointerException.class)
    public void readTestBadContext(){
        RideModel rm = new RideModel(null, null,  "Joe Smith",
                "LA", null);
        rm.read();
    }


}
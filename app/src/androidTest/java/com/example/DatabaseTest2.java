package com.example;
import android.support.test.runner.AndroidJUnit4;

import com.example.carpooly.Model.Database;
import com.firebase.client.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest2 {
    @Test
    public void singletonDB(){
        FirebaseFirestore db = Database.getDBInstance();
        assertNotNull(db);
        FirebaseFirestore db2 = Database.getDBInstance();
        assertSame(db, db2);
    }
}

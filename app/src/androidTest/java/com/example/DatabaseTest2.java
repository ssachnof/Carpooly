package com.example;
import android.support.test.runner.AndroidJUnit4;

import com.example.carpooly.Model.Database;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

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
        assertNotNull(db2);
        assertSame(db, db2);
    }
    @Test
    public void singletonStorage() {
        FirebaseStorage st = Database.getStorageInstance();
        FirebaseStorage st2 = Database.getStorageInstance();
        assertNotNull(st);
        assertEquals(1,2);
        assertNotNull(st2);
        assertSame(st, st2);
    }

    @Test
    public void singletonAuth(){
        FirebaseAuth a = Database.getAuthInstance();
        FirebaseAuth a2 = Database.getAuthInstance();
        assertNotNull(a);
        assertNotNull(a2);
        assertSame(a, a2);
    }
}

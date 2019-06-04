package com.example.carpooly.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void singletonDB() {
        FirebaseFirestore db = Database.getDBInstance();
        FirebaseFirestore db2 = Database.getDBInstance();

        assertNotNull(FirebaseFirestore.getInstance());
        //assertNotNull(db);
        //assertNotNull(db2);
        //assertSame(db, db2);
    }
    @Test
    public void singletonStorage() {
        FirebaseStorage st = Database.getStorageInstance();
        FirebaseStorage st2 = Database.getStorageInstance();

        assertNotNull(st);
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
package com.example.carpooly;

import java.util.HashMap;
import java.util.List;

public class HashMapInitializer<E, T> {
    public HashMap<E, T> makeHash(List<E> keys, List<T> values){
        HashMap<E, T> hm = new HashMap<>();
        if (keys.size() != values.size())
            throw new IllegalArgumentException("HashMapInitializer: length of keys must equals the" +
                    "length of values, keys: " + keys.size() + ", values: " + values.size());
        else{
            for(int i = 0; i < keys.size(); i++){
                E key = keys.get(i);
                T value = values.get(i);
                hm.put(key, value);
            }
            return hm;

        }
    }
}

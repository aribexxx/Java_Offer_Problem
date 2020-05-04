package Datastructure.array;

import java.util.Hashtable;

public class duplicateNum {
    //input  N numbers,all values are 0~N-1.
    public static void duplicateNum_HashTable(int[] arr) {
        Hashtable<Integer, Integer> store = new Hashtable<>();

        for (int i = 0; i < arr.length; i++) {
            if (store.contains(arr[i])) {
                System.out.println("Duplicate num find " + arr[i]);
            } else {
                store.put(i, arr[i]);
            }
        }

    }


}

package com.example.fingermastergame.ui.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageFingersUtils {

    public static <T> T[] arrayListToArray(List<?> list, T[] array) {
        return list.toArray(array);
    }

    public static <T> List<T> arrayToArrayList (T[] array){
        return new ArrayList<>(Arrays.asList(array));
    }

}

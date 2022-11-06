package com.saugat.walmartapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClass {

    public static int test(List<Integer> nums) {

        Map<Integer, Integer> checkMap = new HashMap<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            if (checkMap.containsKey(nums.get(i))) {
                checkMap.put(nums.get(i), checkMap.get(nums.get(i)) + 1);
            } else {
                checkMap.put(nums.get(i), 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : checkMap.entrySet()) {
            if (entry.getValue() == 1) {
                if (entry.getKey() > max) max = entry.getKey();
            }
        }

        return (max == Integer.MIN_VALUE) ? -1 : max;
    }
}

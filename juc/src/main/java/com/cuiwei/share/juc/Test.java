package com.cuiwei.share.juc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = 0; i < 1000000; i++) {
            map.put(String.valueOf(i), i);
            if (i % 10000 == 0) {
                long now = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.println(new Date() + " putSize=" + i + " memoryUse=" + (now - start) / 1000000 + "M");
            }
        }
    }

}

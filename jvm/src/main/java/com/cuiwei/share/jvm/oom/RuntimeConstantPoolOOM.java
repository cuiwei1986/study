package com.cuiwei.share.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量内存溢出 VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 * @author wei.cui
 * @version 1.0 Aug 16, 2017
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}

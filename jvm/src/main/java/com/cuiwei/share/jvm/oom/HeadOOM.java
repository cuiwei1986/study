package com.cuiwei.share.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟机栈溢出 VM args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author wei.cui
 * @version 1.0 Aug 16, 2017
 */
public class HeadOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}

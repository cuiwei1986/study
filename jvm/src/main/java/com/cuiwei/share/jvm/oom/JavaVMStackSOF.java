package com.cuiwei.share.jvm.oom;

/**
 * 虚拟机栈溢出 VM Args:-Xss128k
 * 
 * @author wei.cui
 * @version 1.0 Aug 16, 2017
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stackLength=" + oom.stackLength);
            throw e;
        }
    }

}

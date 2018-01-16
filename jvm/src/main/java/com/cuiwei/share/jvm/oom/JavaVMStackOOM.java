package com.cuiwei.share.jvm.oom;

/**
 * 创建线程导致内存溢出 VM Arags:-Xss2M
 * 
 * @author wei.cui
 * @version 1.0 Aug 16, 2017
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}

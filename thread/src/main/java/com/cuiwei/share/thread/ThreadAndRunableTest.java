package com.cuiwei.share.thread;

import org.junit.Test;

public class ThreadAndRunableTest {


    // Thread 和 Runnable 的不同点：
    // Thread是类，而Runnable是接口；Thread本身是实现了Runnable接口的类。我们知道“一个类只能有一个父类，但是却能实现多个接口”，因此Runnable具有更好的扩展性。
    // 此外，Runnable还可以用于“资源的共享”。即，多个线程都是基于某一个Runnable对象建立的，它们会共享Runnable对象上的资源。
    // 通常，建议通过“Runnable”实现多线程！

    class MyThread extends Thread {
        private int ticket = 10;

        public void run() {
            for (int i = 0; i < 20; i++) {
                if (this.ticket > 0) {
                    System.out.println(this.getName() + " 卖票：ticket" + this.ticket--);
                }
            }
        }
    }

    @Test
    public void testThread() {
        // 启动3个线程t1,t2,t3；每个线程各卖10张票！
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }

    // 结果说明：
    // (01) MyThread继承于Thread，它是自定义个线程。每个MyThread都会卖出10张票。
    // (02) 主线程main创建并启动3个MyThread子线程。每个子线程都各自卖出了10张票。

    class MyRunable implements Runnable {
        private int ticket = 10;

        public void run() {
            for (int i = 0; i < 20; i++) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
                }
            }
        }
    }

    @Test
    public void testRunable() {
        MyRunable mt = new MyRunable();
        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
    }

    // 结果说明：
    // (01) 和上面“MyThread继承于Thread”不同；这里的MyThread实现了Thread接口。
    // (02)
    // 主线程main创建并启动3个子线程，而且这3个子线程都是基于“mt这个Runnable对象”而创建的。运行结果是这3个子线程一共卖出了10张票。这说明它们是共享了MyThread接口的。
}

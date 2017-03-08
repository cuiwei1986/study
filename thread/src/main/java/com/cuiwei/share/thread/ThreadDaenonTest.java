package com.cuiwei.share.thread;

public class ThreadDaenonTest {

    // java 中有两种线程：用户线程和守护线程。可以通过isDaemon()方法来区别它们：如果返回false，则说明该线程是“用户线程”；否则就是“守护线程”。
    // 用户线程一般用户执行用户级任务，而守护线程也就是“后台线程”，一般用来执行后台任务。需要注意的是：Java虚拟机在“用户线程”都结束后会后退出。
    // 当Java虚拟机启动时，通常有一个单一的非守护线程（该线程通过是通过main()方法启动）。JVM会一直运行直到下面的任意一个条件发生，JVM就会终止运行：
    // (01) 调用了exit()方法，并且exit()有权限被正常执行。
    // (02) 所有的“非守护线程”都死了(即JVM中仅仅只有“守护线程”)。

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(3);
                    System.out.println(this.getName() + "(isDaemon=" + this.isDaemon() + ")" + ", loop " + i);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    static class MyDaemon extends Thread {
        public MyDaemon(String name) {
            super(name);
        }

        public void run() {
            try {
                for (int i = 0; i < 10000; i++) {
                    Thread.sleep(1);
                    System.out.println(this.getName() + "(isDaemon=" + this.isDaemon() + ")" + ", loop " + i);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "(isDaemon=" + Thread.currentThread().isDaemon() + ")");

        Thread t1 = new MyThread("t1"); // 新建t1
        Thread t2 = new MyDaemon("t2"); // 新建t2
        t2.setDaemon(true); // 设置t2为守护线程
        t1.start(); // 启动t1
        t2.start(); // 启动t2
    }
}

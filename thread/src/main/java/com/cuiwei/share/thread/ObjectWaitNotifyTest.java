package com.cuiwei.share.thread;

public class ObjectWaitNotifyTest {

    // 在Object.java中，定义了wait(),notify()和notifyAll()等接口，必须要与synchronized(Obj)一起使用。
    // wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。
    // 而notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
    // notify(), wait()依赖于“同步锁”，而“同步锁”是对象锁持有，并且每个对象有且仅有一个！这就是为什么notify(), wait()等函数定义在Object类
    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " call notify()");
                // 唤醒当前对象的wait线程
                notify();
            }
        }
    }


    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName() + " wait()");

                /*
                 * jdk的解释中，说wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
                 * 虽然t1.wait()是通过“线程t1”调用的wait()方法，但是调用t1.wait()的地方是在“主线程main”中。
                 * 而主线程必须是“当前线程”，也就是运行状态，才可以执行t1.wait()。所以，此时的“当前线程”是“主线程main”！
                 * 因此，t1.wait()是让“主线程”等待，而不是“线程t1”！
                 */
                // 当前线程(main线程)等待
                t1.wait();

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.cuiwei.share.thread;

public class ThreadSleepTest {

    // sleep()定义在Thread类内，当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。
    // sleep()会指定休眠时间，线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。

    
    // sleep() 与 wait()的比较
    // wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，也会释放同步锁。而sleep()的作用是也是让当前线程由“运行状态”进入到“休眠(阻塞)状态”。
    // wait()会释放对象的同步锁，而sleep()则不会释放锁
    // 演示如下:sleep()不会释放锁
    static class SleepTest implements Runnable {
        public void run() {
            try {
                synchronized (this) {
                    for (int i = 0; i < 10; i++) {
                        System.out.printf("%s: %d\n", Thread.currentThread().getName(), i);
                        // i能被4整除时，休眠100毫秒
                        if (i % 4 == 0)
                            Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        SleepTest st = new SleepTest();
        Thread t1 = new Thread(st, "t1");
        Thread t2 = new Thread(st, "t2");
        t1.start();
        t2.start();
    }
}

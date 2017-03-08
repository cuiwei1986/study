package com.cuiwei.share.thread;

public class ThreadYieldTest {

    // yield()定义在Thread内，作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！

    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        @Override
        public synchronized void run() {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                // i整除4时，调用yield
                if (i % 4 == 0)
                    Thread.yield();
            }
        }


    }

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }

    // sleep()定义在Thread类内，当前线程休眠，即当前线程会从“运行状态”进入到“休眠(阻塞)状态”。
    // sleep()会指定休眠时间，线程休眠的时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，从而等待cpu的调度执行。

    static class ThreadB extends Thread {
        public ThreadB(String name) {
            super(name);
        }

        public synchronized void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("%s: %d\n", this.getName(), i);
                    // i能被4整除时，休眠100毫秒
                    if (i % 4 == 0)
                        Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            ThreadB t1 = new ThreadB("t1");
            ThreadB t2 = new ThreadB("t2");
            t1.start();
            t2.start();
        }
    }


}

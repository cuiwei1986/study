package com.cuiwei.share.thread;

public class ThreadInterruptTest {

    // interrupt()在Thread类中,作用是中断本线程

    // interrupted() 和 isInterrupted()都能够用于检测对象的“中断标记”。
    // 区别是，interrupted()除了返回中断标记之外，它还会清除中断标记(即将中断标记设为false)；而isInterrupted()仅仅返回中断标记。

    public static void main(String[] args) {
        try {
            Thread t1 = new MyThread("t1"); // 新建“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is new.");

            t1.start(); // 启动“线程t1”
            System.out.println(t1.getName() + " (" + t1.getState() + ") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            t1.interrupt();
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(300);
            System.out.println(t1.getName() + " (" + t1.getState() + ") is interrupted now.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (!isInterrupted()) {
                    Thread.sleep(100); // 休眠100ms
                    i++;
                    System.out.println(Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(
                        Thread.currentThread().getName() + " (" + this.getState() + ") catch InterruptedException.");
            }
        }
    }

}



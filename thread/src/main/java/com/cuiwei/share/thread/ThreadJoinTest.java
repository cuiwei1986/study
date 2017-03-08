package com.cuiwei.share.thread;

public class ThreadJoinTest {

    // join()在Thread.java中,作用：让“主线程”等待“子线程”结束之后才能继续运行.
    // join()必须在子线程start()之后才能起作用
    public static void main(String[] args) {
        try {
            ThreadA t1 = new ThreadA("t1"); // 新建“线程t1”
            t1.start(); // 启动“线程t1”
            t1.join(); // 将“线程t1”加入到“主线程main”中，并且“主线程main()会等待它的完成”
            System.out.printf("%s finish\n", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.printf("%s start\n", this.getName());

            // 延时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finish\n", this.getName());
        }
    }
}

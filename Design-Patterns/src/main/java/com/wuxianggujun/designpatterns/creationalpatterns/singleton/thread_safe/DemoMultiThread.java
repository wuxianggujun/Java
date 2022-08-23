package com.wuxianggujun.designpatterns.creationalpatterns.singleton.thread_safe;

public class DemoMultiThread {
    public static void main(String[] args) {
        String tip = """
                If you see the same value, then singleton was reused (yay!)
                If you see different values, then 2 singletons were created (booo!!)
                RESULT:
                """;
        System.out.println(tip);
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }


    static class ThreadFoo implements Runnable {

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println("singleton = " + singleton.value);
        }
    }

    static class ThreadBar implements Runnable {

        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println("singleton = " + singleton.value);
        }
    }

}

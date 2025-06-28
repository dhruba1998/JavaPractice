package main.multithreading;

public class Main {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(3);
        System.out.println("First created thread is " + Thread.currentThread().getName());
        Thread producerThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is calling shared resource");
            for (int i = 1; i <= 10; i++) {
                sharedResource.addItem(i);
            }
        });
        Thread consumerThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is calling shared resource");
            for (int i = 1; i <= 10; i++) {
                int val = sharedResource.consumeItem();
                System.out.println("Consumer consumed "+ val);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}

package main.multithreading;

import java.util.ArrayDeque;

public class SharedResource {

    ArrayDeque<Integer> arrayDeque;
    int queueSize;

    SharedResource(int queueSize){
        this.arrayDeque = new ArrayDeque<>(queueSize);
        this.queueSize = queueSize;
    }

    public synchronized void addItem(int val){
        while (arrayDeque.size() == queueSize){
            try{
                System.out.println("Queue is full, "+ Thread.currentThread().getName() + " is waiting now");
                wait();
            } catch(InterruptedException e) {
                // handle exception
            }
        }
        arrayDeque.add(val);
        System.out.println(val + " added to queue by "+ Thread.currentThread().getName());
        notifyAll();
    }

    public synchronized int consumeItem(){
        while (arrayDeque.isEmpty()){
            try{
                System.out.println("Queue is empty, "+ Thread.currentThread().getName() + " is waiting now");
                wait();
            } catch(InterruptedException e) {
                // handle exception
            }
        }
        int val = arrayDeque.pollFirst();
        System.out.println(val + " is returned by "+ Thread.currentThread().getName());
        notifyAll();
        return val;
    }

}

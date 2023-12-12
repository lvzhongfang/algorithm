package com.tc.algorithm.data.structure;

/**
 * desc 队列
 *
 * @author lvzf
 * @date 2023/12/12
 */
public class Queue {

    private static int QUEUE_MAX_LENGTH = 10;

    private static int [] array = new int [Queue.QUEUE_MAX_LENGTH];

    private int head = 0;

    private int size = 0;

    private int currentIndex = 0;

    /**
     * Insert an element into the queue. Return true if the operation is successful.
     * @param x 待插入的数据
     * @return true-插入成功；false-插入失败
     */
    public boolean enQueue (int x) {
        if (size < Queue.QUEUE_MAX_LENGTH) {
            array[currentIndex] = x;
            currentIndex = (currentIndex + 1) % Queue.QUEUE_MAX_LENGTH;
            size++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete an element from the queue. Return true if the operation is successful.
     * @return true-删除成功；false-删除失败
     */
    public boolean deQueue() {
        if (size > 0) {
            head = (head + 1) % Queue.QUEUE_MAX_LENGTH;
            size--;
            return true;
        } else {
            return false;
        }
    }

    public int front () throws Exception {
        if (size > 0) {
            return array[head];
        } else {
            throw new Exception("Queue is empty.");
        }
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public static void main(String[] args) throws Exception {
        Queue queue = new Queue();

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        queue.enQueue(7);
        queue.enQueue(8);
        queue.enQueue(9);
        queue.enQueue(10);
        boolean b = queue.enQueue(11);
        System.out.println(b);
        queue.deQueue();
        queue.deQueue();
        b = queue.enQueue(11);
        b = queue.enQueue(12);
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
        queue.deQueue();
        System.out.println(queue.front());
    }
}

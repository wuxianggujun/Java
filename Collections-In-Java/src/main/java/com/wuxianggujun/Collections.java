package com.wuxianggujun;

import java.util.*;

public class Collections {

    public static void main(String[] args) {
        ArrayListExample.main(args);
        LinkedListExample.main(args);
        VectorExample.main(args);
        StackExample.main(args);
        HashSetExample.main(args);
        TreeSetExample.main(args);
        PriorityQueueExample.main(args);
        DequeExample.main(args);
    }

    class ArrayListExample {

        /**
         * ArrayList
         * 它是一个动态数组，这意味着它的大小可以根据需要减少或增加，因此，它提供了灵活性。
         * 你可以使用其索引号，随机访问数组列表中的元素。
         *
         * @param args
         */
        public static void main(String[] args) {

            ArrayList<String> list = new ArrayList<>();
            list.add("我草");
            list.add("奇怪");
            list.add("离谱");
            //打印列表中所有的元素
            System.out.println(list);

            //通过索引获取list列表中的第一个元素
            System.out.println(list.get(0));

            //根据元素索引号删除元素
            list.remove(0);
            System.out.println(list);

            //返回false，因为该元素在列表中不存在
            System.out.println(list.remove("我草"));
            //清空列表中所有的元素
            list.clear();
        }

    }


    class LinkedListExample {
        /**
         * 链表
         * 它使用链表数据结构来存储信息，它还可以存储重复的信息
         *
         * @param args
         */
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(-2);
            list.add(100);
            list.add(0);
            list.add(50);

            //打印所有元素
            System.out.println(list);

            list.remove(2);
            System.out.println("list = " + list);
        }
    }

    class VectorExample {
        /**
         * Vector
         * 它类似ArrayList，将元素存储在动态数组中。ArrayList与Vector之间的两个
         * 主要区别是 Vector是同步，并且有许多Collections框架中不包含的方法
         *
         * @param args
         */
        public static void main(String[] args) {
            Vector<Float> vector = new Vector<>();
            vector.add(5.4f);
            vector.add(-4.5f);
            vector.add(9.55f);
            //在数组列表使用addElement添加元素
            vector.addElement(6.5f);
            vector.addElement(15.76f);

            System.out.println("vector = " + vector);

            vector.remove(5.4f);
            System.out.println("vector = " + vector);

        }

    }

    class StackExample {
        /**
         * Stack 堆
         * 它实现了基于后进先出的（LIFO）概念的堆栈数据结构，它是Vector的子类
         */
        public static void main(String[] args) {
            Stack<String> stack = new Stack<>();
            stack.push("JAVA");
            stack.push("PHP");
            stack.push("C++");

            System.out.println("stack = " + stack);

            //从堆栈中移除元素
            stack.pop();
            System.out.println("stack = " + stack);
        }
    }

    class HashSetExample {
        /**
         * Set Interface
         * 它是唯一元素的集合，即不存储重复元素。插入顺序不保留。
         * 实现Set 接口的类是 HashSet，LinkedHashSet和TreeSet
         * <p>
         * HashSet
         * 它使用哈希表通过哈希技术存储元素，它实现Set接口
         */
        public static void main(String[] args) {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(5);
            hashSet.add(16);
            hashSet.add(8);
            hashSet.add(-3);
            hashSet.add(16);

            System.out.println("hashSet = " + hashSet);

            //删除元素
            hashSet.remove(-3);
            System.out.println("hashSet = " + hashSet);
        }
    }

    class TreeSetExample {

        /**
         * TreeSet
         * 它使用 红黑树来存储元素并实现Set接口，
         * 元素以升序存储在TreeSet中，Java  TreeSet类的访问和检索时间非常快
         */
        public static void main(String[] args) {
            TreeSet<String> treeSet = new TreeSet<>();
            treeSet.add("Html");
            treeSet.add("php");
            treeSet.add("python");
            //再一次添加
            treeSet.add("Html");

            System.out.println("treeSet = " + treeSet);

            treeSet.remove("php");
            System.out.println("treeSet = " + treeSet);
        }
    }

    class PriorityQueueExample {
        /**
         * Queue Interface
         * 它实现了队列数据接口，该结构基于FIFO（先进先出）的概念，这意味着首先插入的元素将先被删除。
         * PriorityQueue Deque和ArrayQueue是实现Queue接口的类
         * <p>
         * PriorityQueue
         * 每个元素都被赋予了一些优先级，并根据该优先级处理元素
         */
        public static void main(String[] args) {
            PriorityQueue<String> queue = new PriorityQueue<>();
            queue.add("html");
            queue.add("css");
            queue.add("javascript");
            queue.add("php");
            queue.add("python");

            System.out.println("queue = " + queue);

            System.out.println("head : " + queue.element());
            System.out.println("head : " + queue.peek());
            
            queue.remove();
            queue.poll();
            System.out.println("queue = " + queue);
        }
    }
    
    class DequeExample{
        /**
         * Deque Interface
         * 双端队列称为 Deque，它允许从两端执行插入和删除。实现Deque接口的是ArrayDeque
         */
        public static void main(String[] args) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            deque.add(1);
            deque.add(-4);
            deque.add(5);
            System.out.println("deque = " + deque);
        }
    }
}

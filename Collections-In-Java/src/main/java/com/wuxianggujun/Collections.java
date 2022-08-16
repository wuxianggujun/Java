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
         * ����һ����̬���飬����ζ�����Ĵ�С���Ը�����Ҫ���ٻ����ӣ���ˣ����ṩ������ԡ�
         * �����ʹ���������ţ�������������б��е�Ԫ�ء�
         *
         * @param args
         */
        public static void main(String[] args) {

            ArrayList<String> list = new ArrayList<>();
            list.add("�Ҳ�");
            list.add("���");
            list.add("����");
            //��ӡ�б������е�Ԫ��
            System.out.println(list);

            //ͨ��������ȡlist�б��еĵ�һ��Ԫ��
            System.out.println(list.get(0));

            //����Ԫ��������ɾ��Ԫ��
            list.remove(0);
            System.out.println(list);

            //����false����Ϊ��Ԫ�����б��в�����
            System.out.println(list.remove("�Ҳ�"));
            //����б������е�Ԫ��
            list.clear();
        }

    }


    class LinkedListExample {
        /**
         * ����
         * ��ʹ���������ݽṹ���洢��Ϣ���������Դ洢�ظ�����Ϣ
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

            //��ӡ����Ԫ��
            System.out.println(list);

            list.remove(2);
            System.out.println("list = " + list);
        }
    }

    class VectorExample {
        /**
         * Vector
         * ������ArrayList����Ԫ�ش洢�ڶ�̬�����С�ArrayList��Vector֮�������
         * ��Ҫ������ Vector��ͬ�������������Collections����в������ķ���
         *
         * @param args
         */
        public static void main(String[] args) {
            Vector<Float> vector = new Vector<>();
            vector.add(5.4f);
            vector.add(-4.5f);
            vector.add(9.55f);
            //�������б�ʹ��addElement���Ԫ��
            vector.addElement(6.5f);
            vector.addElement(15.76f);

            System.out.println("vector = " + vector);

            vector.remove(5.4f);
            System.out.println("vector = " + vector);

        }

    }

    class StackExample {
        /**
         * Stack ��
         * ��ʵ���˻��ں���ȳ��ģ�LIFO������Ķ�ջ���ݽṹ������Vector������
         */
        public static void main(String[] args) {
            Stack<String> stack = new Stack<>();
            stack.push("JAVA");
            stack.push("PHP");
            stack.push("C++");

            System.out.println("stack = " + stack);

            //�Ӷ�ջ���Ƴ�Ԫ��
            stack.pop();
            System.out.println("stack = " + stack);
        }
    }

    class HashSetExample {
        /**
         * Set Interface
         * ����ΨһԪ�صļ��ϣ������洢�ظ�Ԫ�ء�����˳�򲻱�����
         * ʵ��Set �ӿڵ����� HashSet��LinkedHashSet��TreeSet
         * <p>
         * HashSet
         * ��ʹ�ù�ϣ��ͨ����ϣ�����洢Ԫ�أ���ʵ��Set�ӿ�
         */
        public static void main(String[] args) {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(5);
            hashSet.add(16);
            hashSet.add(8);
            hashSet.add(-3);
            hashSet.add(16);

            System.out.println("hashSet = " + hashSet);

            //ɾ��Ԫ��
            hashSet.remove(-3);
            System.out.println("hashSet = " + hashSet);
        }
    }

    class TreeSetExample {

        /**
         * TreeSet
         * ��ʹ�� ��������洢Ԫ�ز�ʵ��Set�ӿڣ�
         * Ԫ��������洢��TreeSet�У�Java  TreeSet��ķ��ʺͼ���ʱ��ǳ���
         */
        public static void main(String[] args) {
            TreeSet<String> treeSet = new TreeSet<>();
            treeSet.add("Html");
            treeSet.add("php");
            treeSet.add("python");
            //��һ�����
            treeSet.add("Html");

            System.out.println("treeSet = " + treeSet);

            treeSet.remove("php");
            System.out.println("treeSet = " + treeSet);
        }
    }

    class PriorityQueueExample {
        /**
         * Queue Interface
         * ��ʵ���˶������ݽӿڣ��ýṹ����FIFO���Ƚ��ȳ����ĸ������ζ�����Ȳ����Ԫ�ؽ��ȱ�ɾ����
         * PriorityQueue Deque��ArrayQueue��ʵ��Queue�ӿڵ���
         * <p>
         * PriorityQueue
         * ÿ��Ԫ�ض���������һЩ���ȼ��������ݸ����ȼ�����Ԫ��
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
         * ˫�˶��г�Ϊ Deque�������������ִ�в����ɾ����ʵ��Deque�ӿڵ���ArrayDeque
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

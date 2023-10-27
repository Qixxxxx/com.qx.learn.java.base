package com.qx.learn.day04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * List接口继承Collection接口，List是有序的，有一些根据索引的操作方法：
 * void add(Object o): 在list末端添加素o
 * void add(int index, Object o):在 index位置插入ele元素
 * boolean addAll(Collection else):list末端将else中的所有元素添加进来
 * boolean addAll( int index, Collection else):从 index位置开始将else中的所有元素添加进来
 * Object get(int index):获取指定 index位置的元素
 * int indexOf(Object o):返回o在集合中首次出现的位置
 * int lastIndexOf(Object o):返回obj在当前集合中末次出现的位置
 * object remove( int index):移除指定 index位置的元素，并返回此元素
 * Object set(int index, Object o):设置指定 Index位置的元素为o
 * List sublist( int fromIndex, int toIndex):返回从 fromIndex 到 toIndex位置的子集合 (集合的切片)
 *
 * LinkedList新增方法：LinkedList是带有头尾节点的双向链表
 * void addFirst(Object obj)   添加首节点
 * void addLast(Object obj)    添加末尾节点
 * Object getFirst()           查看首节点
 * Object getLast()            查看末尾节点
 * Object removeFirst()        删除首节点
 * Object removeLast()         删除末尾节点
 *
 *
 * 对于数组，随机元素访问的时间复杂度是 O(1)，元素插入操作是 O(n)
 * 对于链表，随机元素访问的时间复杂度是 O(n)，元素插入操作是 O(1)
 *
 * 对于ArrayList和LinkedList性能，我们进行下列比较，发现 ArrayList无论是随机访问，还是随机插入，所需的时间都很短，遥遥领先于LinkedList
 * 对于LinkedList，插入需要知道插入的位置节点，先通过循环获取到节点的再执行插入操作。前者也是有开销的，不可能只考虑插入操作本身的代价
 * 所以，对于插入操作，LinkedList 的时间复杂度其实也是O(n)。在各种常用场景下，LinkedList 几乎都不能在性能上胜出 ArrayList
 *
 * ArrayList在大部分时候的性能都明显要强于LinkedList。即使是对于增删少查询多这一场景
 * LinkedList只有在头部插入元素的时候，性能会明显优于ArrayList
 */
public class ListTest {

    /**
     *  随机对一个1000000长度的list进行1000次随机位置get操作
     */
    @Test
    public void test1() {
        long startTime = System.currentTimeMillis();
        linkedListGet(1000000, 1000);
        long endTime = System.currentTimeMillis();

        long startTime1 = System.currentTimeMillis();
        arrayListGet(1000000, 1000);
        long endTime1 = System.currentTimeMillis();

        System.out.println("arraylist get spend: " + (endTime1 - startTime1));  // out: 27ms
        System.out.println("linkedList get spend: " + (endTime - startTime));   // out: 813ms

    }

    /**
     *  随机对一个1000000长度的list进行1000次随机位置add操作
     */
    @Test
    public void test2() {
        long startTime = System.currentTimeMillis();
        linkedListAdd(1000000, 1000);
        long endTime = System.currentTimeMillis();

        long startTime1 = System.currentTimeMillis();
        arrayListAdd(1000000, 1000);
        long endTime1 = System.currentTimeMillis();

        System.out.println("arraylist add spend: " + (endTime1 - startTime1));  // out: 131ms
        System.out.println("linkedList add spend: " + (endTime - startTime));   // out: 799ms

    }

    /**
     *  随机对一个1000000长度的list进行1000次随机位置addFirst操作
     */
    @Test
    public void test3() {
        long startTime = System.currentTimeMillis();
        linkedListAddFirst(1000000, 1000);
        long endTime = System.currentTimeMillis();

        long startTime1 = System.currentTimeMillis();
        arrayListAddFirst(1000000, 1000);
        long endTime1 = System.currentTimeMillis();

        System.out.println("arraylist addFirst spend: " + (endTime1 - startTime1));  // out: 249ms
        System.out.println("linkedList addFirst spend: " + (endTime - startTime));   // out: 63ms

    }

    private static void linkedListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    private static void arrayListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    private static void linkedListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }

    private static void arrayListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }

    private static void linkedListAddFirst(int elementCount, int loopCount) {
        LinkedList<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.addFirst(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    private static void arrayListAddFirst(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(0, ThreadLocalRandom.current().nextInt(elementCount)));
    }
}

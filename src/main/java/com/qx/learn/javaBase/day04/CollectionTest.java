package com.qx.learn.javaBase.day04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * Collection是Java提供的一个接口，方便处理 数目不固定的一组数据，List接口和Set接口继承了该接口
 * 实际使用时，与泛型连用
 * 常用方法：
 *      1.add(Object obj) 添加
 *        addAll(Collection c)将集合c中的元素添加进来
 *      2.size() 获取有效元素的个数
 *      3.clear() 清空集合
 *      4.isEmpty() 查看是否为空
 *      5. contains(Object obj) 是否包含指定元素
 *         containsAll(Collection c) 是否包含指定集合
 *      6.remove(Object obj) 删除
 *        removeAll(Collection c) 差集
 *      7. retainAll((Collection c)) 交集
 *      8. equals(Object obj) 逐元素判断是否相等
 *      9. Object[] toArray() 转成对象数组
 *      10. hashCode() 获取集合对象的哈希值
 *      11. iterator() 用于集合的遍历，返回迭代器对象
 *
 *      对于集合的遍历有三种方式：普通for循环，迭代器，增强for循环（对于数组而言，增强for循环底层就是for循环，对于集合，底层为迭代器）。通常：
 *          如果需要操作索引，使用普通for循环。
 *          如果在遍历的过程中需要删除元素，请使用迭代器。
 *          如果仅仅想遍历，那么使用增强for。
 *
 */
public class CollectionTest {

    @Test
    public void CollectionMethodTest() {
        Collection<Integer> c1 = new ArrayList<>();
        Collection<Integer> c = Arrays.asList(2,3,4);
        /**
         * 添加元素：
         * boolean add(E e)  添加一个元素在尾端
         * boolean addAll(Collection<? extends E> c) 将另一个Collection中的元素添加进来(并集)
         */
        c1.add(1);
        c1.addAll(c);
        /**
         * int size()     获得集合的长度
         */
        c1.size();
        /**
         * void clear()   清空集合
         */
        c1.clear();
        /**
         * boolean remove(E e)   删除元素
         * boolean removeAll(Collection<?> c) 取差集
         */
        c1.remove(1);
        c1.removeAll(c);
        /**
         * boolean isEmpty()    查看集合是否为空
         */
        c1.isEmpty();
        /**
         * boolean contains(Object obj) 是否包含指定元素
         * boolean containsAll(Collection c) 是否包含指定集合
         */
        c1.contains(1);
        c1.containsAll(c);
        /**
         * boolean retainAll(Collection<?> c) 是否含有另一个Collection的元素(交集)
         */
        c1.retainAll(c);
        /**
         * boolean equals(Object o)     判断相等
         */
        c1.equals(2);
        /**
         * int hashCode()    获取对象的哈希值
         */
        c1.hashCode();
        /**
         * 集合元素的遍历：使用Iterator(迭代器)接口
         *        boolean hasNext()     判断是否还有下一个元素
         *        E next()              获得元素
         *        void remove()         删除元素
         */
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if("1".equals(obj)) {
                iterator.remove();
            }
        }
    }
}

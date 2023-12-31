package com.qx.learn.javaBase.day04;

/**
 * Set存储无序的、不可重复的数据: 对于大规模数据存储时，难免存在重复元素，用来过滤掉重复元素
 * 1.无序性：不等于随机性。在底层数组中不是按照数组索引的顺序添加，而是按照数据的哈希值决定的
 * <p>
 * 2.不可重复性: 让equals()不能返回True。放入hashset中的元素对应的类需要重写equals和hashCode方法，以保证相同数值的对象为同一哈希值
 * <p>
 * 3.hashset底层为拉链散列表，底层是HashMap，在HashSet中，元素都存到HashMap键值对的Key上面，而Value时有一个统一的值private static final Object PRESENT = new Object()
 * <p>
 * 4.LinkedHashSet为HashSet的子类，多了双向指针，
 * <p>
 * 5.TreeSet底层为红黑树,元素需要是同类对象，需要排序。对应的类需要实现自然排序或者定制排序
 */

public class SetTest {
}

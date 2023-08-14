package com.qx.learn.day04;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *      Map:双列数据，存储key-value对的数据
 *          HashMap：作为Map的主要实现类；线程不安全的，效率高；能存储null值
 *              LinkedHashMap: 在HashMap底层结构添加双向指针，对于频繁的遍历操作，该类的执行效率比HashMap更高
 *          Hashtable：古老的Map实现类；线程安全的，效率低；不能存储null值
 *              Properties：常用来处理配置文件；key和value都是String类型
 *          TreeMap：可进行排序，实现排序遍历(按key来自然排序或者定制排序)；底层为红黑树
 *
 *      HashMap底层结构： 数组+链表   （jdk7以及之前）
 *                       数组+链表+红黑发树（jdk8）
 *      HashMap的底层实现原理：
 *          jdk1.7：
 *              HashMap map = new HashMap();  在实例化之后，底层创建了size=16的一维数组Entry[] table
 *              map.put(key1,value1):
 *                  1.调用kery1所在类的hashCode()计算key1哈希值，通过散射函数计算出Entry数组中的存放位置
 *                  2.如果此位置上的数据为空，此时添加成功
 *                  3.若此位置上的数据不为空，则比较key1和已经存在的一个或多个数据的哈希值：
 *                      如果key1的哈希值和已存在的数据的哈希值都不同，此时添加成功
 *                      如果(key1,value1)的key1哈希值和已经存在的key2的哈希值相同，继续比较，调用key1.equals(key2)
 *                          如果equals()返回false：添加成功
 *                          如果equals()返回true：使用value1替换value2
 *              jdk1.7对于添加，使用头插法
 *         jdk1.8:
 *              HashMap map = new HashMap(); 在实例化之后，底层没有创建一个长度为16的数组，底层的数组是Node[] 而不是Entry[]
 *              首次调用put()方法时，底层创建长度为16的数组
 *              当数组的某一个索引位置上元素以链表形式存在的数据个数>8 且 当前数组的长度 > 64时，此索引位置上的链表存储改为红黑树存储
 *
 *  Map有个内部类：Entry<K,V>是map的键值对元素
 *  Entry常用方法：
 *      K getKey();    获取key
 *      V getValue();  获取value
 *      V setValue(V value);  存储value
 *  Map常用方法：
 *        int size()    获得map的长度
 *        boolean isEmpty()   判空
 *        boolean containsKey(Object key)   是否包含key
 *        boolean containsValue(Object value)   是否包含value
 *        V get(Object key)          根据key获得value
 *        V put(K key, V value)      存储键值对
 *        V remove(Object key)       删除指定的key
 *        boolean remove(Object key, Object value)      按键值对进行删除，如果不存在该键值对，不进行删除
 *        void putAll(Map<? extends K, ? extends V> m)    将另一个map的键值对存储
 *        void clear()             清空map
 *        Set<K> keySet()          获得key的set集合
 *        Collection<V> values()   获得value的集合
 *        Set<Map.Entry<K, V>> entrySet()   获得Entry的set集合
 *        boolean equals(Object o)          是否相等
 *        V getOrDefault(Object key, V defaultValue)    根据key获得value，如果value为空，则返回默认值
 *        void forEach(BiConsumer<? super K, ? super V> action)   遍历并根据action完成操作
 *        V replace(K key, V value)          替换
 *        boolean replace(K key, V oldValue, V newValue)  使用新值替换旧值
 *        void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)    根据function替换map中的值
 *        V putIfAbsent(K key, V value)      新存储键值对，如果key已存在，则不进行put
 *
 */
public class MapTest {


}

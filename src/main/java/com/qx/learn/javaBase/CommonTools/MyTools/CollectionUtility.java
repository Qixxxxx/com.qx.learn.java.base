package com.qx.learn.javaBase.CommonTools.MyTools;

import java.util.*;

/**
 * 集合相关工具类
 * <p>
 * 此工具方法针对Collection及其实现类封装的工具。
 */
public class CollectionUtility {

    private CollectionUtility() {
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合是否不为空
     *
     * @param collection 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 新建一个HashSet
     *
     * @param <T>        集合元素类型
     * @param collection 集合
     * @return HashSet对象
     */
    public static <T> HashSet<T> newHashSet(Collection<T> collection) {
        return newHashSet(false, collection);
    }

    /**
     * 新建一个HashSet
     *
     * @param <T>        集合元素类型
     * @param isSorted   是否有序，有序返回 {LinkedHashSet}，否则返回{HashSet}
     * @param collection 集合，用于初始化Set
     * @return HashSet对象
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
        return isSorted ? new LinkedHashSet<>(collection) : new HashSet<>(collection);
    }

    /**
     * 两个集合的并集<br>
     * 针对一个集合中存在多个相同元素的情况，计算两个集合中此元素的个数，保留最多的个数<br>
     * 例如：集合1：[a, b, c, c, c]，集合2：[a, b, c, c]<br>
     * 结果：[a, b, c, c, c]，此结果中只保留了三个c
     *
     * @param <T>   集合元素类型
     * @param coll1 集合1
     * @param coll2 集合2
     * @return 并集的集合，返回 {ArrayList}
     */
    public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2) {
        if (isEmpty(coll1) && isEmpty(coll2)) {
            return new ArrayList<>();
        }
        if (isEmpty(coll1)) {
            return new ArrayList<>(coll2);
        } else if (isEmpty(coll2)) {
            return new ArrayList<>(coll1);
        }

        final ArrayList<T> list = new ArrayList<>(Math.max(coll1.size(), coll2.size()));
        final Map<T, Integer> map1 = countMap(coll1.iterator());
        final Map<T, Integer> map2 = countMap(coll2.iterator());
        final Set<T> elts = newHashSet(coll2);
        elts.addAll(coll1);
        int m;
        for (T t : elts) {
            m = Math.max(map1.get(t), map2.get(t));
            for (int i = 0; i < m; i++) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 多个集合的并集<br>
     * 针对一个集合中存在多个相同元素的情况，计算两个集合中此元素的个数，保留最多的个数<br>
     * 例如：集合1：[a, b, c, c, c]，集合2：[a, b, c, c]<br>
     * 结果：[a, b, c, c, c]，此结果中只保留了三个c
     *
     * @param <T>        集合元素类型
     * @param coll1      集合1
     * @param coll2      集合2
     * @param otherColls 其它集合
     * @return 并集的集合，返回 {ArrayList}
     */
    @SafeVarargs
    public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
        Collection<T> union = union(coll1, coll2);
        if (null != otherColls) {
            for (Collection<T> coll : otherColls) {
                if (isEmpty(coll)) {
                    continue;
                }
                union = union(union, coll);
            }
        }
        return union;
    }


    /**
     * 根据集合返回一个元素计数的 {Map}<br>
     * 所谓元素计数就是假如这个集合中某个元素出现了n次，那将这个元素做为key，n做为value<br>
     * 例如：[a,b,c,c,c] 得到：<br>
     * a: 1<br>
     * b: 1<br>
     * c: 3<br>
     *
     * @param <T>  集合元素类型
     * @param iter {Iterator}，如果为null返回一个空的Map
     * @return {Map}
     */
    private static <T> Map<T, Integer> countMap(Iterator<T> iter) {
        final HashMap<T, Integer> countMap = new HashMap<>();
        if (null != iter) {
            T t;
            while (iter.hasNext()) {
                t = iter.next();
                countMap.put(t, countMap.getOrDefault(t, 0) + 1);
            }
        }
        return countMap;
    }

}

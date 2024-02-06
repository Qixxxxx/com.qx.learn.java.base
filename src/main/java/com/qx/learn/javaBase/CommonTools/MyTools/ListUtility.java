package com.qx.learn.javaBase.CommonTools.MyTools;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用于List的工具类，提供校验和一些常用方法
 */
public final class ListUtility {

    private ListUtility(){}

    /**
     * 校验list是否有值
     */
    public static <T> boolean any(List<T> list){
        return list != null && list.size() > 0;
    }

    /**
     * 校验list是否为空
     */
    public static <T> boolean isNullOrEmpty(List<T> list){
        return !any(list);
    }

    /**
     * 按序合并多个list，返回合并后的结果
     * @param args : 任意个list
     */
    public static <T> List<T> join(List<? extends T> ...args) {
        if (args == null || args.length == 0) {
            return null;
        }
        return Stream.of(args)
                .filter(ListUtility::any)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * 间隔划分list
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size");
        }
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            result.add(take(list, i, size));
        }
        return result;
    }

    /**
     * 跳跃按序取数
     */
    public static <T> List<T> take(List<T> source, int skipCount, int takeCount) {
        if (isNullOrEmpty(source)) {
            throw new IllegalArgumentException("source");
        }

        List<T> result = skip(source, skipCount);
        result = take(result, takeCount);

        return result;
    }

    /**
     * 按序取数，如果count超过list的长度，就取整个list
     */
    public static <T> List<T> take(List<T> list, int count) {
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }
        if (count < 0) {
            throw new IllegalArgumentException("count");
        }

        List<T> result = new ArrayList<T>();
        if (count == 0) {
            return result;
        }
        for (T item : list) {
            result.add(item);
            if (result.size() == count) {
                break;
            }
        }
        return result;
    }

    /**
     * 获取一个跳过count个值的list
     */
    public static <T> List<T> skip(List<T> list, int count) {
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }
        if (count < 0) {
            throw new IllegalArgumentException("count");
        }

        List<T> result = new ArrayList<T>();

        for (int i = count; i < list.size(); i++) {
            result.add(list.get(i));
        }

        return result;
    }

    /**
     * 获取非空数组第一个值
     */
    public static <T> T first(List<T> list){
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }
        return list.get(0);
    }

    /**
     * 获取数组第一个值,允许数组为空数组
     */
    public static <T> T firstOrNull(List<T> list){
        if (list == null) {
            throw new IllegalArgumentException("list");
        }
        return list.get(0);
    }

    /**
     * 获取非空数组最后一个值
     */
    public static <T> T last(List<T> list){
        if (isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }

        return list.get(list.size() - 1);
    }

    /**
     * 获取数组最后一个值
     */
    public static <T> T lastOrDefault(List<T> list){
        if (list == null) {
            throw new IllegalArgumentException("list");
        }
        if(list.size() == 0){
            return null;
        }

        return list.get(list.size() - 1);
    }

    /**
     * 比较两个list是否相等
     */
    public static <T> boolean isEquals(List<T> left, List<T> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.size() != right.size()) {
            return false;
        }

        List<T> brief = new ArrayList<>();
        brief.addAll(right);
        for (T item : left) {
            if (!brief.remove(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获得满足条件的第一个元素的索引值
     */
    public static <T> int findIndex(List<T> source, Predicate<T> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }

        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        for (int i = 0; i < source.size(); i++) {
            if (predicate.test(source.get(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 排除重复元素
     */
    public static <T> List<T> distinct(List<T> source) {
        if (isNullOrEmpty(source)) {
            throw new IllegalArgumentException("source");
        }
        return new ArrayList<>(new HashSet<>(source));
    }

    /**
     * 判断与两集合是否存在交集
     */
    public static <T> boolean whetherIntersect(List<T> list, List<T> otherList) {
        if (isNullOrEmpty(list) || isNullOrEmpty(otherList)) {
            throw new IllegalArgumentException("input");
        }
        return !Collections.disjoint(list, otherList);
    }
}

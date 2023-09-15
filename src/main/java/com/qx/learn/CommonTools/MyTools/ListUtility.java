package com.qx.learn.CommonTools.MyTools;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListUtility {

    private ListUtility(){}

    public static <T> List<T> join(List<? extends T> ...args) {
        if (args == null || args.length == 0) {
            return null;
        }
        return Stream.of(args)
                .filter(ListUtility::any)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (ListUtility.isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size");
        }
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            result.add(ListUtility.take(list, i, size));
        }
        return result;
    }

    public static <T> boolean any(List<T> list){
        return list != null && list.size() > 0;
    }

    public static <T> boolean isNullOrEmpty(List<T> list){
        return !any(list);
    }


    public static <T> T first(List<T> list){
        if (!any(list)) {
            throw new IllegalArgumentException("list");
        }

        return list.get(0);
    }

    public static <T> T firstOrDefault(List<T> list){
        if (list == null) {
            throw new IllegalArgumentException("list");
        }
        if(list.size() == 0){
            return null;
        }

        return list.get(0);
    }

    public static <T> T last(List<T> list){
        if (!any(list)) {
            throw new IllegalArgumentException("list");
        }

        return list.get(list.size() - 1);
    }

    public static <T> T lastOrDefault(List<T> list){
        if (list == null) {
            throw new IllegalArgumentException("list");
        }
        if(list.size() == 0){
            return null;
        }

        return list.get(list.size() - 1);
    }

    public static <T> List<T> skip(List<T> list, int count) {
        if (list == null) {
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

    public static <T> List<T> take(List<T> list, int count) {
        if (list == null) {
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

    public static <T> List<T> take(List<T> source, int skipCount, int takeCount) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("source");
        }

        List<T> result = ListUtility.skip(source, skipCount);
        result = ListUtility.take(result, takeCount);

        return result;
    }

    public static <T> ArrayList<T> where(List<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        ArrayList<T> result = new ArrayList<>();

        for (T item : source) {
            if (predicate.execute(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public static <T, TResult> ArrayList<TResult> select(List<T> source, Func1<T, TResult> selector) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (selector == null) {
            throw new IllegalArgumentException("selector");
        }

        ArrayList<TResult> result = new ArrayList<>();

        for (T item : source) {
            result.add(selector.execute(item));
        }

        return result;
    }

    public static <T, TResult> ArrayList<TResult> selectWhere(List<T> source, Func1<T, Boolean> predicate, Func1<T, TResult> selector) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }
        if (selector == null) {
            throw new IllegalArgumentException("selector");
        }

        return select(where(source, predicate), selector);
    }

    public static <T> T firstOrDefault(List<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            return source.isEmpty() ? null : source.get(0);
        }

        for (T item : source) {
            if (predicate.execute(item)) {
                return item;
            }
        }

        return null;
    }

    public static <T> T firstOrDefault(List<T> source, Func1<T, Boolean> predicate, T defaultValue) {
        if (source == null || source.isEmpty() || predicate == null) {
            return defaultValue;
        }
        for (T item : source) {
            if (predicate.execute(item)) {
                return item;
            }
        }
        return defaultValue;
    }

    public static <T> T lastOrDefault(List<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }

        if (predicate == null) {
            return source.isEmpty() ? null : source.get(source.size() - 1);
        }

        for (int i = source.size() - 1; i >= 0; i--) {
            if (predicate.execute(source.get(i))) {
                return source.get(i);
            }
        }

        return null;
    }


    public static <T> T lastOrDefault(List<T> source, Func1<T, Boolean> predicate, T defaultValue) {
        if (source == null || source.isEmpty() || predicate == null) {
            return defaultValue;
        }
        for (int i = source.size() - 1; i >= 0; i--) {
            if (predicate.execute(source.get(i))) {
                return source.get(i);
            }
        }
        return defaultValue;
    }

    public static <T> boolean any(List<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        for (T item : source) {
            if (predicate.execute(item)) {
                return true;
            }
        }

        return false;
    }

    public static <T> boolean all(List<T> source, final Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        return !any(
                source,
                new Func1<T, Boolean>() {
                    @Override
                    public Boolean execute(T item) {
                        return !predicate.execute(item);
                    }
                });
    }

    public static <T> boolean isEquals(List<T> left, List<T> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.equals(right)) {
            return true;
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

    public static <TKey extends Comparable<? super TKey>, TSource> List<TSource> orderBy(Collection<TSource> source, final Func1<TSource, TKey> keySelector) {
        if (source == null || source.size() == 0) {
            throw new IllegalArgumentException("source");
        }

        List<TSource> result = new ArrayList<>(source);
        Collections.sort(result, new Comparator<TSource>() {
            @Override
            public int compare(TSource o1, TSource o2) {
                TKey key1 = keySelector.execute(o1);
                TKey key2 = keySelector.execute(o2);

                if (key1 == key2) {
                    return 0;
                }
                if (key1 == null) {
                    return -1;
                }
                if (key2 == null) {
                    return 1;
                }
                return key1.compareTo(key2);
            }
        });

        return result;
    }

    public static <TKey extends Comparable<? super TKey>, TSource> List<TSource> orderByDescending(Collection<TSource> source, final Func1<TSource, TKey> keySelector) {
        if (source == null || source.size() == 0) {
            throw new IllegalArgumentException("source");
        }

        List<TSource> result = new ArrayList<>(source);
        Collections.sort(result, new Comparator<TSource>() {
            @Override
            public int compare(TSource o1, TSource o2) {
                TKey key1 = keySelector.execute(o1);
                TKey key2 = keySelector.execute(o2);

                if (key1 == key2) {
                    return 0;
                }
                if (key1 == null) {
                    return 1;
                }
                if (key2 == null) {
                    return -1;
                }
                return -key1.compareTo(key2);
            }
        });

        return result;
    }

    public static <T> List<T> except(List<T> source, List<T> excepts) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (excepts == null) {
            throw new IllegalArgumentException("excepts");
        }

        List<T> result = new ArrayList<>(source);
        result.removeAll(excepts);
        return result;
    }

    public static <T> int count(Collection<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }
        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        int count = 0;
        for (T item : source) {
            if (predicate.execute(item)) {
                count++;
            }
        }

        return count;
    }

    public static <T> int findIndex(List<T> source, Func1<T, Boolean> predicate) {
        if (source == null) {
            throw new IllegalArgumentException("source");
        }

        if (predicate == null) {
            throw new IllegalArgumentException("predicate");
        }

        for (int i = 0; i < source.size(); i++) {
            if (predicate.execute(source.get(i))) {
                return i;
            }
        }

        return -1;
    }

    public static <T> List<T> distinct(Collection<T> source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("source");
        }

        LinkedHashSet<T> hashSet = new LinkedHashSet<>(source);
        return new ArrayList<>(hashSet);
    }

    public static <T> List<T> populateList(int size, T value) {
        ArgumentValidatorUtility.isTrue(size > 0, "size");

        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(value);
        }
        return result;
    }
}

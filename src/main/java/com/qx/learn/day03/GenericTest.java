package com.qx.learn.day03;

/**
 *      集合容器在声明阶段不能确定这个容器到底存什么类型的对象，所以在JDK1.5之后，使用泛型来解决这个问题
 *      泛型（Generic）：将元素类型设计为一个参数，这个类型参数就是泛型
 *
 *      泛型的使用方式： 类名<T,K,V...> 可以有多个泛型
 *          Collection<T> c = new List<T>();    其中T只能是一个类 可以简写为   Collection<T> c = new List<>();
 *          Map<String, Integer> map = new HashMap<String, Integer>;
 *          ......
 *
 *      注意：使用泛型时，类型参数不允许为静态 (static)。 由于静态变量在对象之间共享，因此编译器无法确定要使用的类型
 *      常用的泛型标记符:
 *          E - Element (在集合中使用，因为集合中存放的是元素)
 *          T - Type（Java 类）
 *          K - Key（键）
 *          V - Value（值）
 *          N - Number（数值类型）
 *          ? - 表示不确定的 java 类型
 *      通配符： ?     是所有泛型的父类
 *      <? extend Number>表示 上限为Number
 *      <? super Number>表示 下限为Number
 */
public class GenericTest<T, K, V> {

    public T t;
    public K k;
    public V v;


}

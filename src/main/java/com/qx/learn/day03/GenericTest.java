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
 *      注意：在静态方法中不能使用类的泛型，静态属性也不能使用，会导致JVM无法完成初始化
 *
 *      通配符： ？     是所有泛型的父类
 *      <? extend Number>表示 上限为Number
 *      <? super Number>表示 下限为Number
 */
public class GenericTest {

}

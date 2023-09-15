package com.qx.learn.CommonTools.MyTools;

/**
 * 加不加@FunctionalInterface注解对于接口是不是函数式接口没有影响
 * 该注解只是提示编译器去检查是否符合函数式编程接口
 *
 */
@FunctionalInterface
public interface Func1<T, R> {
    R execute(T arg);
}
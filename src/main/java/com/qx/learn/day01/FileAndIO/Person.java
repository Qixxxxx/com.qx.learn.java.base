package com.qx.learn.day01.FileAndIO;

import java.io.Serializable;

/**
 *  Person类需要满足以下要求才能序列化：
 *      1.需要实现接口：Serializable
 *      2.当前类提供一个全局常量 serialVersionUID
 *      3.还需保证，该类的内部属性也是可序列化的
 * @author Qi
 * @create 2021-07-08 20:39
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 424324235235L;    // 序列版本号
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

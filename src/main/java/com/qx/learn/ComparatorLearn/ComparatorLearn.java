package com.qx.learn.ComparatorLearn;


import com.qx.learn.day01.FileAndIO.Person;
import org.junit.Test;

import java.util.*;

/**
 * Java中的对象，正常情况下，只能进行比较：==  或  != 。不能使用 > 或 < 的
 * 但是在开发场景中，我们需要对多个对象进行排序，就需要比较对象的大小。
 * 如何实现？使用两个接口中的任何一个：Comparable 或 Comparator
 * 1.自然排序：实现Comparable接口，在实现了Comparable接口的类中重写compareTo(T o)方法
 *  重写compareTo(obj)的规则：
 *     如果当前对象this大于形参对象obj，则返回正整数，
 *     如果当前对象this小于形参对象obj，则返回负整数，
 *     如果当前对象this等于形参对象obj，则返回零。
 *  像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式
 *  String实现Comparable接口，重写了compareTo方法如下：
 *  获取两个字符串的char数组，按短的字符串的长度进行遍历对比每个char的大小(char的大小由在Unicode字符集决定，例如A的Unicode编码数是65，B是66),如果char数组一致，那么比较字符串长度，长度短的在前面，输出负数
 *      public int compareTo(String anotherString) {
 *         int len1 = value.length;
 *         int len2 = anotherString.value.length;
 *         int lim = Math.min(len1, len2);
 *         char v1[] = value;
 *         char v2[] = anotherString.value;
 *         int k = 0;
 *         while (k < lim) {
 *             char c1 = v1[k];
 *             char c2 = v2[k];
 *             if (c1 != c2) {
 *                 return c1 - c2;
 *             }
 *             k++;
 *         }
 *         return len1 - len2;
 *     }
 *  2.定制排序：使用Comparator接口：被称为外部比较器，Comparable接口实现类的对象在任何位置都可以比较大小。Comparator接口属于临时性的比较。
 *      没实现Comparable接口而又不方便修改代码，或者实现了Comparable接口的排序规则不适合当前的操作，使用 Comparator 的对象来排序
 *
 */
public class ComparatorLearn {


    @Test
    public void ComparableTest() {
        Person p1 = new Person("zhangsan", 20);
        Person p2 = new Person("lisi", 18);
        Person p3 = new Person("wangwu", 25);

        Person[] persons = {p1, p2, p3};
        Arrays.sort(persons);
        System.out.println("排序后");
        for (Person person : persons) {
            System.out.println(person.name + ":" + person.age);
        }
    }

    // 两种方式定义Comparator比较器
    @Test
    public void ComparatorTest() {
        // 直接实例化实现类
        Comparator<Person> comparator1 = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        };
        // 使用lambda表达式
        Comparator<Person> comparator2 = (o1, o2) -> -(o1.age - o2.age);

        Person p1 = new Person("zhangsan", 20);
        Person p2 = new Person("lisi", 18);
        Person p3 = new Person("wangwu", 25);

        List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3));
        list.sort(comparator1);
        System.out.println("comparator1排序后");
        for (Person person : list) {
            System.out.println(person.name + ":" + person.age);
        }
        list.sort(comparator2);
        System.out.println("comparator2排序后");
        for (Person person : list) {
            System.out.println(person.name + ":" + person.age);
        }
    }

    // 通常使用Comparator比较器，常用在Stream.sorted、Collections.sort、List.sort和Arrays.sort方法
    @Test
    public void comparatorExample() {
        List<Person> list = getStudentList();
        System.out.println("排序前");
        for (Person person : list) {
            System.out.println(person.name + ":" + person.age);
        }
        Comparator<Person> comparator = (s1, s2) -> s1.age - s2.age;
        System.out.println("Stream.sorted排序");
        list.stream().sorted(comparator).forEach(s -> System.out.println(s));

        System.out.println("Collections.sort排序");
        Collections.sort(list, comparator);
        list.forEach(s -> System.out.println(s));

        System.out.println("List.sort排序");
        list.sort(comparator);
        list.forEach(s -> System.out.println(s));

        System.out.println("sort和Arrays.sort排序");
        Arrays.sort(list.toArray(new Person[0]), comparator);
        list.forEach(s -> System.out.println(s));
    }


    static class Person implements Comparable<Person>{

        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }
    }

    private static List<Person> getStudentList(){
        Person s1 = new Person("Ram", 18);
        Person s2 = new Person("Shyam",22);
        Person s3 = new Person("Mohan",19);
        return Arrays.asList(s1,s2,s3);
    }



}

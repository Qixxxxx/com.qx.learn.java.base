package com.qx.learn.javaBase.ComparatorLearn;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 在Java 8中，比较器接口常用方法：
 *  reversed:返回一个比较器，该比较器强制执行反向排序。
 *  reverseOrder:一个静态方法，返回比较器，对对象集合进行反向自然排序.需要类实现Comparable接口
 *  comparing：接受一个函数，该函数从给定的类型中提取一个可比较的排序键，并返回一个通过该排序键进行比较的比较器。有两种形式：
 *      static <T,U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T,? extends U> keyExtractor)
 *      static <T,U> Comparator<T> comparing(Function<? super T,? extends U> keyExtractor, Comparator<? super U> keyComparator)
 *  thenComparing：顾名思义，在排序后在此进行排序，可多次使用。比如当名字相同时，按年龄排序
 *      static <T,U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T,? extends U> keyExtractor)
 */
public class ComparatorMethodLearn {

    @Test
    public void reversedTest() {
        List<Student> list = getStudentList();
        Comparator<Student> comparator = (s1, s2) -> s1.getAge() - s2.getAge();
        list.stream().sorted(comparator.reversed()).forEach(s -> System.out.print(s.getAge() + " "));
        // 反向自然排序
        list.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s.getAge() + " "));

    }

    // comparing和thenComparing比较常用
    @Test
    public void comparingTest() {
        List<Student> list = getStudentList();
        // 按age排序
        Comparator<Student> comparator = Comparator.comparing(Student::getAge);
        list.stream().sorted(comparator.reversed()).forEach(s -> System.out.print(s.getAge() + " "));
        // 根据排序键比较器排序
        Comparator<Student> comparator1 = Comparator.comparing(Student::getAge, (age1, age2) -> -(age1 - age2));
        list.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s.getAge() + " "));

        Comparator<Student> comparator2 = Comparator.comparing(Student::getAge)
                .thenComparing(Student::getName);


    }


    static class Student implements Comparable<Student>{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + "-" + age;
        }

        @Override
        public int compareTo(Student s) {
            return name.compareTo(s.getName());
        }
    }

    public static List<Student> getStudentList() {
        Student s1 = new Student("Ram", 18);
        Student s2 = new Student("Shyam", 22);
        Student s3 = new Student("Mohan", 19);
        List<Student> list = Arrays.asList(s1, s2, s3);
        return list;
    }

}

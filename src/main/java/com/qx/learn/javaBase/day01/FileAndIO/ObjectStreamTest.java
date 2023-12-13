package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.*;

/**
 *  传输过程中经常需要序列化和反序列化，能够保证对象在传输过程中的完整性和可传递性
 *  对象流：处理流的一种，用于存储和读取基本数据类型或对象的处理流。（主要是对对象进行操作）
 *      序列化：用ObjectOutputStream类读取对象的机制
 *      反序列化：用ObjectInputStream类保存对象的机制
 *      注意：不能序列化static和transient修饰的成员变量
 *
 *  java对象要想可序列化，需要对象类实现Serializable接口
 *
 * @author Qi
 * @create 2021-07-08 18:00
 */
public class ObjectStreamTest {
    /*
    序列化过程:将内存中的java对象保存到磁盘或通过网络传输出去
        使用ObjectOutputStream实现
     */
    @Test
    public void test() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/object.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new String("我爱北京天安门"));       // 使用writeObject()方法
            oos.flush(); // 刷新操作
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    反序列化:将磁盘文件中的对象还原为内存中的一个java对象
        使用ObjectInputStream来实现
     */
    @Test
    public void test2() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/object.dat");
            ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();
            String str = (String) obj;
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    序列化一个Person类的对象
     */
    @Test
    public void personTest() {

        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("Person.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(new Person("Qixin", 18));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

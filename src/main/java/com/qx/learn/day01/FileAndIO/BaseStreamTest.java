package com.qx.learn.day01.FileAndIO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 *  流的分类：
 *      1.操作数据单位：字节流、 字符流
 *      2.数据的流向： 输入流、 输出流
 *      3.流的角色： 节点流、 处理流
 *
 *  流的体系结构：
 *      抽象基类:
 *        字节流（针对字节数据）：  InputStream     OutputStream   字符流（针对字符数据）：  Reader     Writer
 *      节点流（或文件流）:
 *          FileInputStream      FileOutputStream      FileReader      FileWriter
 *      缓冲流（处理流的一种）：
 *          BufferedInputStream     BufferedOutputStream    BufferedReader     BufferedWriter
 *
 *  Reader      Writer  是对于文本数据（.txt  .java .c .cpp）进行操作的，文本数据是由字符组成的
 *  对于非文本数据(.jpg .mp3 .mp4 .avi .doc .ppt 等)而言，使用字节流：InputStream      OutputStream进行操作
 */
public class BaseStreamTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");     // 在main方法中，相对路径为相较于当前工程
        System.out.println(file.getAbsolutePath());

        File file1 = new File("FileAndIO\\hello.txt");     // 此时的相对路径为相较于当前Module
        System.out.println(file1.getAbsolutePath());
    }

    /*
    将hello.txt文件内容读取程序中，并输出到控制台
    1.对文件的读，要保证该文件存在，不然报异常
    2.为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally进行处理

     */
//    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");
            System.out.println(file.getAbsolutePath());
            // 2.提供具体的流
            fr = new FileReader(file);
            // 3.数据的读入(不推荐这种写法)
            int data = fr.read();  // read()：返回读入的一个字符，该字符存储以ascii形式。若达到文件末尾，则返回-1。
            while(data != -1){
                System.out.print((char) data);
                data = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.流的关闭
            if (fr != null) {
                try {
                    fr.close();     // 需要手动关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用read（）的重载方法，推荐这种写法
//    @Test
    public void testFileReader1() {
        FileReader fr = null;       // 抛异常
        try {
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");   // 相较于当前的Module
            // 2.提供具体的流
            fr = new FileReader(file);
            // 3.数据的读取
            char[] cbuf = new char[5];     // 按字符数组进行读取
            int len;      // fr.read(char[] cbuf)返回读取数组的长度，到达末尾返回-1
            while ((len = fr.read(cbuf)) != -1){

//          方式一：
//              for (int i = 0; i < len; i++) {
//                  System.out.print(cbuf[i]);
//              }
//          方式二：
                String str = new String(cbuf, 0, len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                // 4.流的关闭
                try {
                    fr.close();     // 需要手动关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
    从内存中写入数据到硬盘的文件中。

    输出操作，对应的File可以不存在,会自动创建此文件。
        File不存在，在输出的过程中，会自动创建此文件。
        File存在，可以通过构造器传入“append”参数，false表示不追加，直接替换。true表示追加在已有字符后面

     */
//    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            // 1.提供File类的对象，指明写入到的文件
            File file = new File("hello1.txt");

            // 2.提供FileWriter的对象，用于数据的写入
            fw = new FileWriter(file, false);       // true表示追加在已有字符后面, false表示不追加

            // 3.写入的操作
            fw.write("I have a dream!\n");
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw != null){
                // 4.流资源的关闭
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    实现文本的复制
     */
//    @Test
    public void testFileReaderWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1.创建File类的对象，指明读和写的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("Hello2.txt");

            // 2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3.数据的读和写操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                // 每次写入len个字符
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流资源
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

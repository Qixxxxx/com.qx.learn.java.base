package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 转换流：处理流的一种。  提供字节流与字符流之间的转换
 * InputStreamReader: 将一个字节的输入流转换为字符的输入流
 * OutputStreamWriter：将一个字符输出转换为字节的输出流
 * <p>
 * 解码： 字节、字节数组 -->   字符数组、字符串
 * 编码： 字符数组、字符串 -->  字节、字节数组
 * <p>
 * 标准的输入、输出流：
 * System.in: 默认从键盘输入
 * System.out: 默认从控制台输出
 * System类的setIn和setOut方式重新制定输入和输出的流
 */
public class TransformStreamTest {
    /**
     * 将字节流转换为字符流读取文本文件，输入到控制台
     */
    @Test
    public void test1() {
        InputStreamReader isr = null; // 指明字符集，取决于文件保存时使用的字符集
        try {
            FileInputStream fis = new FileInputStream("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\dbcp.txt"); // 流的构造器可以直接写路径，会自动new一个File对象
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

            char[] cbuf = new char[10];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /*
    使用转换流改变文本文件的字符集存储: UTF-8转换为GBK
     */
    @Test
    public void test2() {

        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            File srcFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\dbcp.txt");
            File destFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\dbcp_gbk.txt");

            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(fos, "gbk");

            char[] cbuf = new char[1024];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 从键盘输入字符串，要求将读取到的整行字符串转换为大写输出，然后继续进行输入操作，直到出现"e"或“exit”
     * System.in --> 转换流 --> BufferedReader.readLine()
     */
    @Test
    public void systemTest() {

        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("请输入字符串");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }
                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

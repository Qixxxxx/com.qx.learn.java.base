package com.qx.learn.day01.FileAndIO;

import org.junit.Test;

import java.io.*;

/**
 * 节点流是直接与文件接触，而处理流（又称包装流）是与传输通道（程序与文件之间）接触
 * 缓冲流是处理流的一种：内部提供一个缓冲区，以提高流的读取、写入的速度。让流处理数据更高效
 * BufferedInputStream     BufferedOutputStream
 * BufferedReader     BufferedWriter
 */
public class BufferedTest {
    /**
     * 实现非文本的复制
     */
    @Test
    public void Test1() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1.造文件
            File srcFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\picture.jpg");
            File destFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\copyPicture.jpg");

            // 2.造流
            // 节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            // 缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3.读取和写出
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.资源关闭:先关闭外层的流，再关闭内层的流（关闭外层流的时候，内层流随之关闭）
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
   实现文本的复制
    */
    @Test
    public void Test2() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 1.造文件
            File srcFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\dbcp.txt");
            File destFile = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\dbcp1.txt");

            // 2.造流
            // 节点流
            FileReader fr = new FileReader(srcFile);
            FileWriter fw = new FileWriter(destFile);
            // 缓冲流
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);

            // 3.读取和写出
            // 方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//                bw.write(cbuf, 0, len);
//            }
            // 方式二：使用readLine()整行读取
            String data;
            while ((data = br.readLine()) != null) {
                bw.write(data + "\n");     // data里面不包含换行符，需要手动添加
                // 或者 bw.write(data);
                // bw.newLine();   提供换行操作
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.资源关闭:先关闭外层的流，再关闭内层的流（关闭外层流的时候，内层流随之关闭）
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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

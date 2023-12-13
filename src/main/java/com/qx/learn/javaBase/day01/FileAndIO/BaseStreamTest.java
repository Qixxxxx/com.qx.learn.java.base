package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.*;

/**
 * 流的分类：
 * 1.操作数据单位：字节流、 字符流
 * 2.数据的流向： 输入流、 输出流
 * 3.流的角色： 节点流、 处理流
 * <p>
 * 流的体系结构：
 * 抽象基类:
 * 字节流（针对字节数据）：  InputStream     OutputStream   字符流（针对字符数据）：  Reader     Writer
 * 节点流（或文件流）:
 * FileInputStream      FileOutputStream      FileReader      FileWriter
 * <p>
 * Reader      Writer  是对于文本数据（.txt  .java .c .cpp）进行操作的，文本数据是由字符组成的
 * 对于非文本数据(.jpg .mp3 .mp4 .avi .doc .ppt 等)而言，使用字节流：InputStream      OutputStream进行操作
 */
public class BaseStreamTest {
    /**
     * 将hello.txt文件内容读取程序中，并输出到控制台
     * 1.对文件的读，要保证该文件存在，不然报异常
     * 2.为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally进行处理
     */
    @Test
    public void test1() {
        FileReader fr = null;
        try {
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
            // 2.提供具体的流
            fr = new FileReader(file);
            // 3.数据的读入(不推荐这种写法)
            int data = fr.read();  // read()：返回读入的一个字符，该字符存储以ascii形式。若达到文件末尾，则返回-1。
            while (data != -1) {
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
    @Test
    public void test2() {
        FileReader fr = null;       // 抛异常
        try {
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");   // 相较于当前的Module
            // 2.提供具体的流
            fr = new FileReader(file);
            // 3.数据的读取
            char[] cbuf = new char[5];     // 按字符数组进行读取
            int len;      // fr.read(char[] cbuf)返回读取数组的长度，到达末尾返回-1
            while ((len = fr.read(cbuf)) != -1) {

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
            if (fr != null) {
                // 4.流的关闭
                try {
                    fr.close();     // 需要手动关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 从内存中写入数据到硬盘的文件中。
     * <p>
     * 输出操作，对应的File可以不存在,会自动创建此文件。
     * File不存在，在输出的过程中，会自动创建此文件。
     * File存在，可以通过构造器传入“append”参数，false表示不追加，直接替换。true表示追加在已有字符后面
     */
    @Test
    public void test3() {
        FileWriter fw = null;
        try {
            // 1.提供File类的对象，指明写入到的文件
            File file = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hi.txt");

            // 2.提供FileWriter的对象，用于数据的写入
            fw = new FileWriter(file, false);       // true表示追加在已有字符后面, false表示不追加

            // 3.写入的操作
            fw.write("I have a dream!\n");
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                // 4.流资源的关闭
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 实现文本的复制
     */
    @Test
    public void test4() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1.创建File类的对象，指明读和写的文件
            File srcFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
            File destFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hii.txt");

            // 2.创建输入流和输出流的对象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3.数据的读和写操作
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
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

    /**
     * 使用字节流读取文本，可能出现乱码
     */
    @Test
    public void test5() {
        FileInputStream fis = null;
        try {
            File file = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];           // InputStream是以字节来读取数据的
            int len;
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);     // 在UTF-8中，一个汉字用三个字节来存储，可能会中断截取，导致乱码。所以说字节流不适合处理文本
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件字节流不适合处理文本，适合处理其他数据，实现对图片的复制操作 .jpg文件
     */
    @Test
    public void test6() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/picture.jpg");
            File destFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/copyPicture.jpg");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 进行读写操作
            byte[] buffer = new byte[1024];   // 1024长度比较适中
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    @Test
    public void testCopyFile() {

        long start = System.currentTimeMillis();

        String srcPath = "D:/BaiduNetdiskDownload/剑雨.mp4";    // 复制一个mp4文件
        String destPath = "C:/Users/11435/Desktop/剑雨.mp4";
        copyFile(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为： " + (end - start) + "ms");
    }

    /**
     * 使用字节流实现一个复制文件的方法
     */
    private void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 进行读写操作
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

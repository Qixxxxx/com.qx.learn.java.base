package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * File类：文件和文件目录路径的抽象表示形式，与平台无关。File能新建、删除、重命名文件和目录，但是File类不能访问文件内容本身（需要流来操作）
 * <p>
 * File类的使用：一个File对象可能并不真实存在，File类不涉及到写入或读取文件内容的操作，如果需要进行这些操作，必须使用IO流来完成
 * 构造器：创建File类的实例
 * File(String filePath)
 * File(String parentPath, String childPath)
 * File(File parentFile, String childPath)
 * <p>
 * File类的判断功能
 * public boolean isDirectory（）:判断是否是文件目录
 * public boolean isFile（）:判断是否是文件
 * public boolean exists（）:判断是否存在
 * public boolean canRead（）:判断是否可读
 * public boolean canWrite（）:判断是否可写
 * public boolean isHidden（）:判断是否隐藏
 * <p>
 * File类的获取功能
 * public String getAbsolutePath():获取绝对路径
 * public String getPath():获取路径
 * public String getName():获取名称
 * public String getParent():获取上层文件目录路径。若无，返回null
 * public long length():获取文件长度(即：字节数)。不能获取目录的长度。
 * public long lastModified():获取最后一次的修改时间，毫秒值
 * 适用于文件目录：
 * public String[] list():获取指定目录下的所有文件或者文件目录的名称数组
 * public File[] listFiles():获取指定目录下的所有文件或者文件目录的File数组
 * <p>
 * File类的重命名功能：本质上是将文件剪切到指定位置后，重命名
 * public boolean renameTo(File dest): 重命名为指定文件路径
 * <p>
 * File类的创建功能：如果创建文件或文件目录时，没有写盘符路径，则默认创建在项目的路径之下
 * public boolean createNewFile(): 创建文件。若文件存在则不创建，返回false
 * public boolean mkdir(): 创建文件目录。若文件目录存在或此文件目录的上层目录不存在则不创建，返回false
 * public boolean mkdirs(): 创建文件目录。若文件目录的上层目录不存在则一起创建创建
 * <p>
 * File类的删除功能：java中的删除不走回收站。若要删除一个文件目录，那么该文件目录内不能包含文件或文件目录
 * public boolean delete()：删除文件或文件目录
 */
public class FileTest {

    @Test
    public void test1() {
        // 使用路径构造
        File file1 = new File("E:/Java/Java Learn");
        // 使用父路径+子路径构造
        File file2 = new File("E:/Java/Java Learn", "FileAndIO");
        // 使用File+子路径构造
        File file3 = new File(file2, "hi.txt");
        System.out.println(file1); // out:E:\Java\Java Learn
        System.out.println(file2); // out:E:\Java\Java Learn\FileAndIO
        System.out.println(file3); // out:E:\Java\Java Learn\FileAndIO\hi.txt
    }

    @Test
    public void test2() {
        // 真实存在的文件目录
        File file1 = new File("E:/Java");
        // 不存在的文件目录
        File file2 = new File("E:/Qx");
        // 真实存在的文件
        File file3 = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
        // 不存在的文件
        File file4 = new File("hello.txt");

        System.out.println(file1.exists());    // out:true
        System.out.println(file2.exists());    // out:false
        System.out.println(file3.exists());    // out:true
        System.out.println(file4.exists());    // out:false
        System.out.println();
        // 不真实存在的文件或者目录输出均为false
        System.out.println(file1.isFile());          // out:false
        System.out.println(file1.isDirectory());     // out:true
        System.out.println(file3.isFile());          // out:true
        System.out.println(file3.isDirectory());    // out:false
    }

    @Test
    public void test3() {
        File file = new File("hello.txt");
        File file1 = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
        System.out.println(file);                     // out: hello.txt
        // 查看文件根目录
        System.out.println(file.getAbsoluteFile());   // out: E:\Java\Java Code\com.qx.learn.java.base\hello.txt
        System.out.println(file.getPath());           // out: hello.txt
        System.out.println(file.getName());           // out: hello.txt
        System.out.println(file.getParent());         // out: null    file是一个相对路径，所以其上级文件目录为null
        System.out.println(file.length());           // out:0      file不是真实存在的，因此输出为0
        System.out.println(file.lastModified());     // out:0      file不是真实存在的，因此输出为0

        System.out.println();

        System.out.println(file1);                     // out: E:\Java\Java Code\com.qx.learn.java.base\src\main\resources\hello.txt
        System.out.println(file1.getAbsoluteFile());   // out: E:\Java\Java Code\com.qx.learn.java.base\src\main\resources\hello.txt
        System.out.println(file1.getPath());           // out: E:\Java\Java Code\com.qx.learn.java.base\src\main\resources\hello.txt
        System.out.println(file1.getName());           // out: hello.txt
        System.out.println(file1.getParent());         // out: E:\Java\Java Code\com.qx.learn.java.base\src\main\resources
        System.out.println(file1.length());           // out:55   该文件大小为55字节
        System.out.println(file1.lastModified());     // out:1625752541774

    }

    @Test
    public void test4() {
        File file = new File("E:/Java");
        // list里面包含该目录下所有的文件目录名称
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();

        // files里面包含该目录下所有的File集合
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
    }

    @Test
    public void test5() {
        File file1 = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt");
        File file2 = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hi.txt");

        System.out.println(file1.renameTo(file2));  // 如果需要rename，则需要file1是存在的，file2是不存在的

    }

    /**
     * File类的创建功能：如果创建文件或文件目录时，没有写盘符路径，则默认创建在项目的路径之下
     * public boolean createNewFile(): 创建文件。若文件存在则不创建，返回false
     * public boolean mkdir(): 创建文件目录。若文件目录存在或此文件目录的上层目录不存在则不创建，返回false
     * public boolean mkdirs(): 创建文件目录。若文件目录的上层目录不存在则一起创建创建
     * <p>
     * File类的删除功能：java中的删除不走回收站。若要删除一个文件目录，那么该文件目录内不能包含文件或文件目录
     * public boolean delete()：删除文件或文件目录
     */

    @Test
    public void test6() throws IOException {
        File file1 = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hii.txt");
        if (!file1.exists()) {
            if (file1.createNewFile()) {
                System.out.println("文件创建成功！");
            } else {
                System.out.println("文件创建失败");
            }
        } else {
            if (file1.delete()) {
                System.out.println("删除成功");
            }
        }
    }
}

package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 在java开发过程中，经常需要读写文件，写对文件的相对路径显得尤为重要。
 */
public class PathTest {
    /**
     *
     *  / 代表的路径是根目录；
     *  . 代表的路径是当前Java项目的根目录
     *  ./ 代表的路径是当前Java项目的根目录
     *  ../ 代表上级目录
     */
    @Test
    public void test() throws IOException {
        File file = new File("/");
        System.out.println("/ 代表的绝对路径为：" + file.getAbsolutePath()); // out:  / 代表的绝对路径为：D:\

        File file1 = new File(".");
        System.out.println(". 代表的绝对路径为" + file1.getAbsolutePath()); // out:  . 代表的绝对路径为D:\Users\xin_qi\Desktop\learn\com.qx.learn.java.base\.

        File file2 = new File("./");
        System.out.println(". 代表的绝对路径为" + file2.getAbsolutePath()); // out:  ./ 代表的绝对路径为D:\Users\xin_qi\Desktop\learn\com.qx.learn.java.base\.

        // 项目根目录下级：
        File file3 = new File("test1.txt");
        System.out.println("file2的绝对路径为：" + file3.getAbsolutePath()); // out:  file3的绝对路径为：D:\Users\xin_qi\Desktop\learn\com.qx.learn.java.base\test1.txt

        // ../ 代表上级目录
        File file4 = new File("../../test1.txt");
        System.out.println("file4的绝对路径为：" + file4.getAbsolutePath()); // out:  file4的绝对路径为：D:\Users\xin_qi\Desktop\learn\com.qx.learn.java.base\test1.txt
        System.out.println("file4的上级目录：" + file4.getParent()); // out:  file4的上级目录：..\..

    }
}

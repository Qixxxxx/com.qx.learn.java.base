package com.qx.learn.javaBase.day01.FileAndIO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 *  RandomAccessFile类是一个专门读写文件的类，封装了基本的IO流，在读写文件内容方面比常规IO流更方便、更灵活。但也仅限于读写文件
 *
 *  RandomAccessFile的使用
 *      1.RandomAccessFile直接继承于java.long.Object类，实现了DataInput和DataOutput接口
 *      2.RandomAccessFile即可以作为一个输入流，又可以作为一个输出流
 *        RandomAccessFile的构造器：RandomAccessFile(File file, mode str)  有下列四种mode
 *          "r":以只读方式打开
 *          "rw":打开以便读取和写入
 *          "rwd":打开以便读取和写入；同步文件内容的更新
 *          "rws":打开以便读取和写入；同步文件内容和元数据的更新
 *          如果模式为只读r。则不会创建文件，而是会去读取一个已经存在的文件。如果读取的文件不存在则会出现异常。
 *          如果模式为rw读写。如果文件不存在则会去创建文件，如果存在则不会创建
 *
 *      3.使用指针对文件进行插入操作
 *          应用：实现一个多线程断点下载功能
 *              下载前都会建立两个临时文件，一个是与被下载文件大小相同的空文件，另一个是记录文件指针的位置文件
 *              每次暂停的时候，都会保存上一次的指针，然后断点下载的时候，会继续从上次的地方下载，从而实现断点下载或上传的功能
 */
public class RandomAccessFileTest {

    @Test
    public void test1() {

        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            File srcFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/picture.jpg");
            File destFile = new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/picture1.jpg");

            raf1 = new RandomAccessFile(srcFile, "r");
            raf2 = new RandomAccessFile(destFile, "rw");

            byte[] buffer = new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    使用指针进行插入操作
    long getFilePointer(): 获取文件记录指针的当前位置
    void seek(long pos): 将文件记录指针定位到pos位置
     */
    @Test
    public void test2() {
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile(new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt"), "rw");

            raf1.seek(3);  // 将指针定位到3的位置
            // 保存指针3后面的所有数据到StringBuilder中
            StringBuilder str = new StringBuilder((int)new File("E:/Java/Java Code/com.qx.learn.java.base/src/main/resources/hello.txt").length()); // 不能超过文件长度
            byte[] buffer = new byte[20];
            int len;
            while ((len = raf1.read(buffer)) != -1){
                str.append(new String(buffer, 0, len));
            }
            raf1.seek(3);  // 指针会到文件末尾，又将指针调回来
            raf1.write("xyz".getBytes());  // 写入xyz
            // 将StringBuilder中的数据写入到文件中
            raf1.write(str.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

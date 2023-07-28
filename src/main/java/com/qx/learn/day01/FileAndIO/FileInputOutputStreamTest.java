package com.qx.learn.day01.FileAndIO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  Reader      Writer  是对于文本数据（.txt  .java .c .cpp）进行操作的，文本数据是由字符组成的
 *  对于非文本数据(.jpg .mp3 .mp4 .avi .doc .ppt 等)而言，使用字节流：InputStream      OutputStream进行操作
 *
 */
public class FileInputOutputStreamTest {
    /*
    使用字节流读取文本，可能出现乱码
     */
//    @Test
    public void testFileInputStream(){
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];           // InputStream是以字节来读取数据的
            int len;
            while((len = fis.read(buffer)) != -1){
                String str = new String(buffer, 0, len);
                System.out.print(str);     // 在UTF-8中，一个汉字用三个字节来存储，可能会中断截取，导致乱码。所以说字节流不适合处理文本
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    实现对图片的复制操作 .jpg文件
     */
//    @Test
    public void testFileInputOutputStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File("一寸照片.jpg");
            File destFile = new File("祁欣的一寸照片.jpg");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 进行读写操作
            byte[] buffer = new byte[1024];   // 1024长度比较适中
            int len;
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0 ,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    @Test
    public void testCopyFile(){

        long start = System.currentTimeMillis();

        String srcPath = "D:\\BaiduNetdiskDownload\\剑雨.mp4";    // 复制一个mp4文件
        String destPath = "C:\\Users\\11435\\Desktop\\剑雨.mp4";
        copyFile(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("复制操作话费的时间为： " + (end - start) + "ms");
    }

    /*
    使用字节流实现一个复制文件的方法
     */
    public void copyFile(String srcPath, String destPath){
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
            while((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0 ,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

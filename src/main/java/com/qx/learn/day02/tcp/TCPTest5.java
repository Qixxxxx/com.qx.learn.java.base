package com.qx.learn.day02.tcp;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  例题4：上传一个资源到服务器
 *  例题5：从服务器下载资源到客户端本地
 *
 * @author Qi
 * @version 1.0
 * @create 2021-09-29 11:13
 */
public class TCPTest5 {
    /*
    1. 发送请求：要下载的资源
    2. 等到服务器的应答
    3. 输入流读取资源
    4，输出流保存到本地
     */
    @Test
    public void client(){
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedOutputStream bos = null;

        try {
            // 1. 创建socket对象
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
            // 2. 输出流发送请求
            os = socket.getOutputStream();
            os.write("我想要下载祁欣的一寸照片".getBytes());
            // 3. 关闭数据的输出,表示上面的数据以及传输完成
            socket.shutdownOutput();
            // 4. 使用输入流获取服务端传过来的资源
            is = socket.getInputStream();
            // 5. 保存到本地
            File file = new File("祁欣的一寸照片.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer1 = new byte[1024];
            int len1;
            while ((len1 = is.read(buffer1)) != -1){
                bos.write(buffer1, 0, len1);
            }
            System.out.println("图片下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /*
    1. 根据客户的请求（文件资源的名称），找到服务器本地的资源
    2. 使用字节流读取资源到内存中
    3. 使用输出流输出
     */
    @Test
    public void serve(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;

        try {
            // 1.创建服务器Socket
            ss = new ServerSocket(8888);
            // 2.接收客户端的Socket
            socket = ss.accept();
            // 3. 接收客户的请求
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            // 4. 搜索资源
            System.out.println("正在搜索祁欣的一寸照片");
            System.out.println("搜索完成，照片位于 C:\\Users\\11435\\Desktop\\找工作\\简历\\一寸照片.jpg");
            // 5. 读取文件资源
            File file = new File("C:\\Users\\11435\\Desktop\\找工作\\简历\\一寸照片.jpg");
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            // 6. 输出
            os = socket.getOutputStream();
            bos = new BufferedOutputStream(os);
            byte[] buffer1 = new byte[1024];
            int len1;
            while((len1 = bis.read(buffer1)) != -1){
                bos.write(buffer1, 0, len1);
            }
            System.out.println("图片已传");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

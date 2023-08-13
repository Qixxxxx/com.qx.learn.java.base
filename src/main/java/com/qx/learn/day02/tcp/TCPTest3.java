package com.qx.learn.day02.tcp;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户发送一张图给服务端，服务端将文件保存在本地。
 * 并且返回“发送成功”给客户端，然后关闭相应的连接
 */
public class TCPTest3 {
    /*
    传一张图给服务器，并保存到本地
     */
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {
            // 1.创建Socket对象
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            // 2.输出流
            os = socket.getOutputStream();
            // 3.缓冲流读取文件
            File file = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\picture.jpg");
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            // 4.写入文件
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                os.write(buffer, 0, len);
            }
            // 5.关闭数据的输出,表示上面的数据以及传输完成
            socket.shutdownOutput();

            // 6.接收服务器的报文
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer1 = new byte[1024];
            int len1;
            while ((len1 = is.read(buffer1)) != -1){
                baos.write(buffer1, 0, len1);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的确认");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7.关闭资源
            if (baos != null){
                try {
                    baos.close();
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
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        BufferedOutputStream bos = null;
        OutputStream os = null;

        try {
            // 1.创建服务器Socket
            ss = new ServerSocket(9090);
            // 2.接收客户端的Socket
            socket = ss.accept();
            // 3.创建输入流
            is = socket.getInputStream();
            // 4.缓冲流保存文件
            File file = new File("E:\\Java\\Java Code\\com.qx.learn.java.base\\src\\main\\resources\\picture1.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
            System.out.println("图片传输完成");
            // 5.服务器给予客户端反馈
            os = socket.getOutputStream();
            os.write("你好，客户端，照片我已经收到".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            if(os != null){
                try {
                    os.close();
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
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


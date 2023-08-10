package com.qx.learn.day02.tcp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 客户端发送：hello,server 并接收到服务器端回发的 hello,client再退出
 * 服务器端接收到客户端的信息后发送 hello,client再退出
 *
 * @author Qi
 * @version 1.0
 * @create 2021-09-23 16:34
 */
public class TCPTest2 {

    @Test
    public void client(){
        Socket socket = null;               // 套接字
        InputStream is = null;              // 输入流
        OutputStream os = null;             // 输出流
        ByteArrayOutputStream baos = null;

        try {
            // 1.创建Socket对象，指明服务器的ip地址和端口号
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8899);
            // 2.使用输出流发送请求给服务器端
            os = socket.getOutputStream();
            os.write("hello, server".getBytes());
            // 3.关闭数据的输出,表示上面的数据以及传输完成
            socket.shutdownOutput();
            // 4.使用输入流获取服务端的响应
            is = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer, 0 ,len);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的响应");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
        OutputStream os = null;
        ByteArrayOutputStream baos = null;

        try {
            // 1.创建服务器端Socket
            ss = new ServerSocket(8899);
            // 2.接收客户端的Socket
            socket = ss.accept();
            // 3.创建输入流用以读取客户端的数据
            is = socket.getInputStream();
            // 4.创建一个字节数组缓冲区，用于存放数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            // 5.读取客户端传输的数据
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的数据");
            // 6.服务器给予客户端响应
            os = socket.getOutputStream();
            os.write("hello,client".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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

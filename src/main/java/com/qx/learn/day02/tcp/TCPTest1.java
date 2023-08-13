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
 *  实现TCP字节流编程：
 *  网络编程中都是使用Socket(套接字)进行编程，网络通信实际上就是Socket间的通信，要求通信的两端都要有Socket
 *  客户端发送：  你好，我是客户端
 *  服务器端接收，并打印信息表明收到了信息
 *
 */
public class TCPTest1{

    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        try {
            // 1.创建Socket对象，指明服务器端的ip地址和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);   // socket由IP地址和端口组成
            // 2.使用socket.getOutputStream方法获取一个输出流
            os = socket.getOutputStream();
            // 3.使用输出流进行写
            os.write("你好，我是客户端".getBytes());        // str.getBytes() 将字符串转换为字节数组
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // socket也需要关闭
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void server() {

        ServerSocket ss = null;    // 服务器的socket
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;   // 一个动态的字节数组缓冲区
        try {
            // 1.指明服务器的socket  ServerSocket的端口
            ss = new ServerSocket(8899);
            // 2.接收客户端的socket，使用ServerSocket的方法 accept()
            socket = ss.accept();
            // 3.创建输入流用以读取客户端的数据，socket.getInputStream方法
            is = socket.getInputStream();
            // 4.创建一个字节数组缓冲区，用于存放数据
            baos = new ByteArrayOutputStream();  // ByteArrayOutputStream是一个动态缓存的字节数组
            byte[] buffer = new byte[1024];
            int len;
            // 5.读取客户端传输的数据
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            if (baos != null){
                try {
                    baos.close();
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


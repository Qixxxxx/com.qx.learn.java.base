package com.qx.learn.day02.tcp;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 例题3：使用字符流完成例题2的操作
 *
 * @author Qi
 * @create 2021-07-16 16:36
 */
public class TCPTest3 {

    @Test
    public void client(){
        Socket socket = null;               // 套接字
        InputStream is = null;              // 输入流
        OutputStream os = null;             // 输出流
        CharArrayWriter caw = null;
        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            // 1.创建Socket对象，指明服务器的ip地址和端口号
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8899);
            // 2.使用输出流发送请求给服务器端
            os = socket.getOutputStream();
            // 3.根据转换流将字节流转为字符流
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("hello, server");
            // 字节流在操作时本身不会用到缓冲区（内存），是文件本身直接操作的，而字符流在操作时使用了缓冲区
            bw.flush();                    // 需要使用flush清空，才能结束，不然会一直等待
            // 4.关闭数据的输出,表示上面的数据以及传输完成
            socket.shutdownOutput();
            // 5.使用输入流获取服务端的响应
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            caw = new CharArrayWriter();
            char[] buffer = new char[1024];
            int len;
            while((len = br.read(buffer)) != -1){
                caw.write(buffer, 0 ,len);
            }
            System.out.println(caw.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的响应");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (caw != null){
                    caw.close();
                }
            }
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
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

    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        CharArrayWriter caw = null;
        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            // 1.创建服务器端Socket
            ss = new ServerSocket(8899);
            // 2.接收客户端的Socket
            socket = ss.accept();
            // 3.创建输入流用以读取客户端的数据
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            // 4.创建一个字符数组缓冲区，用于存放数据
            caw = new CharArrayWriter();
            char[] buffer = new char[1024];
            int len;
            // 5.读取客户端传输的数据
            while ((len = br.read(buffer)) != -1){
                caw.write(buffer, 0, len);
            }
            System.out.println(caw.toString());
            System.out.println("收到了来自于" + socket.getInetAddress().getHostAddress() + "的数据");
            // 6.服务器给予客户端响应
            os = socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("hello,client");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (caw != null){
                caw.close();
            }
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
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

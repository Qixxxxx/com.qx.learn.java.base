package com.qx.learn.javaBase.day02;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP协议的网络编程：
 *  UDP数据报通过数据套接字DatagramSocket发送和接收，系统不保证安全准确送到，只保证发出
 *  DatagramPacket对象封装了UDP数据报
 */
public class UDPTest {
    @Test
    public void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方法发送的数据";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();

        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet,9090);

        socket.send(packet);

        socket.close();
    }

    @Test
    public void receiver() throws IOException {

        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        socket.close();


    }

}

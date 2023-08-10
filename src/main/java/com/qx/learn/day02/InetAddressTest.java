package com.qx.learn.day02;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *  ip地址：用于唯一标识网络中的每台计算机/主机
 *  ip地址的表示形式：点分十进制 xxx.xxx.xxx.xxx每一个十进制数范围为0-255(一个字节)
 *  IP分类：IPv4和IPv6；
 *  在Java中使用InetAddress类代表IP地址
 *  域名：将ip地址转化为域名更好记      比如：www.baidu.com  www.mi.com
 *  端口号：用于标识计算机上某个特定的应用程序(进程)
 *  本地地址：127.0.0.1     对应：  localhost
 *
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {

        // 获取本机的ip
        InetAddress localHost = InetAddress.getLocalHost();      // InetAddress类
        System.out.println(localHost);                           // out: DESKTOP-AQML5O1/2.0.0.1
        System.out.println(localHost.getHostAddress());          // out: 2.0.0.1
        System.out.println(localHost.getHostName());             // out: DESKTOP-AQML5O1

        // 根据主机名，获取InetAddress对象
        InetAddress inet = InetAddress.getByName("DESKTOP-AQML5O1"); // DNS解析主机名
        System.out.println(inet);                           // out: DESKTOP-AQML5O1/2.0.0.1
        System.out.println(inet.getHostAddress());          // out: 2.0.0.1
        System.out.println(inet.getHostName());             // out: DESKTOP-AQML5O1


        // 根据域名，获取InetAddress对象
        InetAddress inet1 = InetAddress.getByName("baidu.com"); // DNS解析域名，得到IP地址
        System.out.println(inet1);
        System.out.println(inet1.getHostAddress());
        System.out.println(inet1.getHostName());

        // 根据域名，获取InetAddress对象
        InetAddress inet2 = InetAddress.getByName("www.baidu.com"); // DNS解析百度搜索的主机名，得到IP地址
        System.out.println(inet2);
        System.out.println(inet2.getHostAddress());
        System.out.println(inet2.getHostName());

    }
}

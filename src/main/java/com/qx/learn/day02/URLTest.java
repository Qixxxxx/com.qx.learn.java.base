package com.qx.learn.day02;

/**
 *
 *  URL:统一资源定位符，表示Internet上某资源的地址，指明如何定位到该资源
 *      常用构造器：
 *          public URL(String spec) ：通过一个表示URL地址的字符串构造
 *                  URL url = new URL("http://www.baidu.com/download.html")
 *          public URL(URL context, String spec):通过一个基URL,和相对地址构造
 *                  URL url_1 = new URL(url, "download.html")
 *          public URL(Sting protocol, String host, String file)
 *                  URL url_2 = new URL("http", "www.baidu.com", "download.html")
 *          public URL(Sting protocol, String host, int port, String file)
 *                  URL url_3 = new URL("http", "www.baidu.com", 80,  "download.html")
 *
 *      常用方法：
 *          public String getProtocol()   获取协议名
 *          public String getHost()       获取主机名
 *          public String getPort()       获取端口号
 *          public String getPath()       获取文件路径
 *          public String getFile()       获取文件名
 *          public String getQuery()      获取查询名
 */
public class URLTest {



}

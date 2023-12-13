package com.qx.learn.javaBase.RegexpLearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexpTest {
    public static void main(String[] args) {

        // 假定编写了爬虫，从百度页面得到如下文本
        String context = "1995年，互联网的蓬勃发展给了Oak机会。业界为了使死板、单调的静态网页能" +
                "够“灵活”起来，急需一种软件技术来开发一种程序，这种程序可以通过网络传播并且能够跨平" +
                "台运行。于是，世界各大IT企业为此纷纷投入了大量的人力、物力和财力。这个时候" +
                "，Sun公司想起了那个被搁置起来很久的Oak，并且重新审视了那个用软件编写的试验" +
                "平台，由于它是按照嵌入式系统硬件平台体系结构进行编写的，所以非常小，特别适" +
                "用于网络上的传输系统，而Oak也是一种精简的语言，程序非常小，适合在网络上传输" +
                "。Sun公司首先推出了可以嵌入网页并且可以随同网页在网络上传输的Applet（Appl" +
                "et是一种将小程序嵌入到网页中进行执行的技术），并将Oak更名为Java（在申请注册" +
                "商标时，发现Oak已经被人使用了，再想了一系列名字之后，最终，使用了提议者在喝一" +
                "杯Java咖啡时无意提到的Java词语）。5月23日，Sun公司在Sun world会议上正式发" +
                "布Java和HotJava浏览器。IBM、Apple、DEC、Adobe、HP、Oracle、Netscape和微" +
                "软等各大公司都纷纷停止了自己的相关开发项目，竞相购买了Java使用许可证，并为自" +
                "己的产品开发了相应的Java平台。";

        // 提取文章中所有的英文单词
        // 1.传统方式：使用遍历方式，代码量大，效率低
        // 2.使用正则表达式（re）
            //2.1 先创建一个Pattern对象，可以理解成就是一个正则表达式对象
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
            //2.2 创建一个匹配器对象，matcher匹配器按照pattern的模式对文本进行匹配，找到就返回true，找不到则为false
        Matcher matcher = pattern.matcher(context);
            //2.3 开始循环匹配
        while (matcher.find()) {
            //2.4 匹配到的文本放到matcher.group(0)中
            System.out.println("找到 " + matcher.group(0));
        }

        // 提取文章中所有的数字：修改模式   Pattern pattern = Pattern.compile("[0-9]+");
        // 提取文章中所有的数字和英文单词： Pattern pattern = Pattern.compile("([0-9]+)|([a-zA-Z]+)");
        // 提取百度热搜标题，修改模式: Pattern pattern = Pattern.compile("文本中一致的内容不变，想要的内容(/S*)");
                                // ex: Pattern pattern= Pattern.compile("<a target="blank\"title=\"(/S*)\"")
        // 后匹配到的文本放到matcher.group(1)中  System.out.println("找到 " + matcher.group(1));


    }

}

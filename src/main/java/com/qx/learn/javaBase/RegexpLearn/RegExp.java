package com.qx.learn.javaBase.RegexpLearn;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  正则表达式语法就是对元字符进行使用，元字符从功能上大致分为：
 *  1.限定符
 *      * 零次或多次匹配前面的字符或子表达式     ex. (abc)* 包含任意个abc的字符串   abc,abcabc等
 *      + 一次或多次匹配前面的字符或子表达式     ex. m+(abc)* 至少一个m开头，后接任意个abc的字符串    m, mabc, mabcabc等
 *      ? 零次或一次匹配前面的字符或子表达式,当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，表示为非贪婪的匹配模式
 *          "非贪婪"模式匹配搜索到的、尽可能短的字符串例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"
 *          ex. m+abc?  至少一个m开头，后接ab或abc的字符串    mab, mabc, mmmmab, mmabc等
 *      {n} 正好匹配 n 次        ex.[abcd]{3} 由abcd中任意字母组成的长度为3的字符串  aaa,abc,dba等
 *      {n,} 至少匹配 n 次
 *      {n,m} 匹配至少 n 次，至多 m 次， Java匹配模式为贪婪匹配，尽可能匹配多的
 *  2.选择匹配符
 *      | 表示 或 的意思， ex.a|c   匹配a或c
 *  3.分组组合和反向引用符
 *      正常分组：
 *          (pattern) 非命名捕获。捕获匹配的子字符串，编号为0的第一个捕获由整个正则表达式模式匹配的文本，其他结果根据左括号的顺序从1开始编号
 *          (?<name>pattern)/(?'name'pattern) 命名捕获。用于name的字符串不能包含任何标点符号，并且不能以数字开头
 *      特别分组：
 *          (?:pattern) 匹配pattern但不捕获该匹配的子表达式，针对|模式
 *          (?=pattern) 匹配指定pattern的字符串
 *          (?!pattern) 不匹配指定pattern的字符串
 *
 *  4.特殊字符(除换行符和制表符外，其余不常用)
 *      \n 换行符
 *      \t 制表符
 *      \xn
 *      \num
 *      \nm
 *      ...
 *
 *  5.字符匹配符
 *      [] 可接受的字符列表      ex. [abc] a、b、c中任意字符都可以匹配
 *      [^] 不接受的字符列表     ex. [^abc] 除a、b、c之外任意字符，包括数字和特殊符号
 *      - 连字符                ex. A-Z任意单个大写字母
 *      . 匹配除\n以外的任何字符      ex. a..b 以a开头b结尾，中间包括2个任意字符的长度为4的字符串     例如aaab, a35b, a$%b等
 *      /d 匹配单个数字字符，相当于[0-9]  ex./d{3}(/d)?  3个或者4个数字的字符串        例如123，5345等
 *      /D 匹配单个非数字字符，相当于[^0-9]    ex. /D(/d)*  以单个非数字字符开头，后接任意个数字的字符串   例如a, A324, a2等
 *      /w 匹配单个数字、大小写字母字符，相当于[0-9a-zA-Z]   ex. /d{3}/w{4}  3个数字开头，接4个数字或大小写字母的字符串 1234567,123abcd等
 *      /W 匹配单个非数字、大小写字母字符，相当于[^0-9a-zA-Z] ex. /W+/d{2} 至少1个非数字字母字符开头，2个数字字符的字符串 #29，#@￥%&12等
 *      /s 匹配任何空白字符(空格，制表符\t)
 *      /S 匹配任何非空白字符
 *      Java中正则表达式默认区分字母大小写，实现不区分大小写：(?i)或在模式pattern中选择
 *          (?i)abc         abc都不区分大小写
 *          a(?i)bc         bc不区分大小写
 *          a((?i)b)c       b不区分大小写
 *          Pattern pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE); Pattern.CASE_INSENSITIVE表示忽略大小写

 *  6.定位符
 *      ^ 指定起始字符    ex. ^[0-9]+[a-z]*  起始位置至少1个数字，后接任意个小写字母的字符串   例如123，6aadfafd, 31231245141等
 *      $ 指定结束字符    ex. ^[0-9]/-[a-z]+$ 以1个数字开头，接-，至少1个小写字母结尾的字符串   例如3-a，4-dfdasfad等
 *      /b 匹配目标字符串的边界    ex. han/b  空格前或者字符串末尾有han   /bhan 开头有han
 *      /B 匹配目标字符串的非边界  ex. han/B  与/b相反
 *  7.转义符：/  需要用到转义符的字符有：  . + * () [] {} ^ \ / $ ? -
 *
 *  ^ 匹配输入字符串开始的位置
 *  $ 匹配输入字符串结尾的位置
 */
public class RegExp {
    @Test
    public void test1() {
        String content = "1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，Sun公司发布了" +
                "第二代Java平台(简称为Java2)的3个版本：J2ME(Java2 Micro Edition,Java2平台的微型" +
                "版），应用于移动、无线及有限资源的环境；J2SE(Java2 Standard Edition,Java2平台的" +
                "标准版），应用于桌面环境；J2EE(Java2 Enterprise Edition,Java2平台的企业版)，应" +
                "用3443于基于Java的应用服务器。Java2平台的发布，是Java发展过程中最重要的一个" +
                "里程碑，标志着3ava的应用开始普及9889";

        // 目标：匹配所有四个数字
        String regStr = "/d/d/d/d";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // 转义符的使用
    public void test2() {
        String content = "abc$(abc(123(";

        // 目标：匹配$或(
        String regStr = "/$|/(";    // 如果直接 $ ( 将会报错
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // 贪婪匹配：Java匹配会尽可能匹配更多的，以多的先输出
    public void test3() {
        // String content = "1111aaaaaaa";  // 只会匹配到aaaaa后续不足以构成3个a
        String content = "1111aaaaaaaaa";  // 匹配到aaaaa和aaaa
        String regStr = "a{3,5}";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // 非贪婪匹配：使用?
    public void test9() {
        String content = "1111aaaa";
        // String regStr = "1+";  // 匹配到1111
        String regStr = "1+?";  // 使用?将其变非贪婪匹配  匹配到四个1
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // 边界定位
    public void test4() {
        String content = "hanshuanpingsphan nnhan";
        // String regStr = "han/b";          // 找到末尾一个han和空格前一个han
        //String regStr = "/bhan";             // 找到开头一个han
        String regStr = "han/B";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // 非命名分组
    // matcher.group(0) 得到匹配到的字符串
    // matcher.group(1) 得到匹配到的字符串的第一个分组
    // matcher.group(2) 得到匹配到的字符串的第二个分组
    // ...以此类推
    public void test5() {
        String content = "hanshuanping s7789 nn1189han";
        String regStr = "(/d/d)(/d/d)";   // 分两组
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
            System.out.println("找到 " + matcher.group(1));
            System.out.println("找到 " + matcher.group(2));
        }
    }

    @Test
    // 命名分组：可以给分组取名
    // matcher.group(0) 得到匹配到的字符串
    // matcher.group("name") 得到匹配到的字符串的第一个分组
    // matcher.group("name") 得到匹配到的字符串的第二个分组
    // ...以此类推
    public void test6() {
        String content = "hanshuanping s7789 nn1189han";
        String regStr = "(?<g1>/d/d)(?<s1>/d/d)";   // 分两组
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
            System.out.println("找到 " + matcher.group(1));
            System.out.println("第一分组 " + matcher.group("g1"));
            System.out.println("找到 " + matcher.group(2));
            System.out.println("第二分组 " + matcher.group("s1"));
        }
    }

    @Test
    // (?:pattern)  注意：这个括号不代表分组 没有matcher.group(1)...
    public void test7() {
        String content = "hello韩顺平教育 jack韩顺平老师 韩顺平同学hello";
        // String regStr = "韩顺平教育|韩顺平老师|韩顺平同学";  正常用|
        String regStr = "韩顺平(?:教育|老师|同学)";  // 使用(?:pattern)
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

    @Test
    // (?=pattern)和(?！pattern)  注意：这个括号不代表分组 没有matcher.group(1)...
    public void test8() {
        String content = "Windows3.1Windows98Windows2000Windows";
        // String regStr = "Windows(?=98|2000)";  // 使用(?=pattern) 匹配到的是第二第三个Windows
        String regStr = "Windows(?!98|2000)";  // 使用(?!pattern) 匹配到的是第一第四个Windows
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到 " + matcher.group(0));
        }
    }

}

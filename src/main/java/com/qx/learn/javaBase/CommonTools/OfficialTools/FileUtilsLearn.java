package com.qx.learn.javaBase.CommonTools.OfficialTools;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * 文件IO是我们日常项目中经常使用到的基础API，常见的IO读写操作基础类字节流InputStream与OutputStream、字符流Reader与Writer
 * Apache开源项目封装的文件操作工具类FileUtils，提供的API涵盖了读取文件、拷贝文件、拷贝目录及文件、删除目录及文件、清除目录等
 */
public class FileUtilsLearn {

    /**
     * 常用的读取文件api
     */
    @Test
    public void readTest() throws IOException {
        // 指定文件路径
        File file = FileUtils.getFile("D:/test/test1/123.txt");
        // 指定目录下的文件路径
        File file1 = FileUtils.getFile(new File("D://test/test1"), "123", "456.txt");
        System.out.println("file的绝对路径为：" + file.getAbsolutePath()); // out:  file的绝对路径为：D:\test\test1\123.txt
        System.out.println("file1的绝对路径为：" + file1.getAbsolutePath()); // out:  file1的绝对路径为：D:\test\test1\123\456.txt

        // 获取输入输出流
        FileInputStream fis = FileUtils.openInputStream(file);
        FileOutputStream fos = FileUtils.openOutputStream(file);
        fos = FileUtils.openOutputStream(file1, true); // append为true时，不进行覆盖操作，而是添加到末尾

        // 将文件内容转为字节数组
        byte[] bytes = FileUtils.readFileToByteArray(file);

        // 将文件内容转为字符串(需要指定字符集CharSet)
        String fileString = FileUtils.readFileToString(file, "UTF-8");
        FileUtils.readFileToString(file, Charset.defaultCharset());

        // 读取目标文件每一行数据，返回字符串集合
        List<String> stringList = FileUtils.readLines(file, "UTF-8");
        FileUtils.readLines(file, Charset.defaultCharset());

    }

    /**
     * 常用的拷贝文件api
     */
    @Test
    public void copyTest() throws IOException {
        File srcFile = FileUtils.getFile("D:/test/test1/123.txt");
        File destFile = FileUtils.getFile("D:/test/test1/4.txt");
        FileUtils.copyFile(srcFile, destFile);
        FileUtils.copyFile(srcFile, destFile, true); // preserveFileDate 为true时改变日期，false时不改变日期
        FileUtils.copyFile(srcFile, Files.newOutputStream(destFile.toPath())); // 拷贝文件到输出流
        FileUtils.copyInputStreamToFile(FileUtils.openInputStream(srcFile), destFile); // 拷贝输入流的内容到指定文件中

        File srcDirectory = FileUtils.getFile("D:/test/test1");
        File destDirectory = FileUtils.getFile("D:/test/test2");
        FileUtils.copyDirectoryToDirectory(srcDirectory, destDirectory);   // 拷贝指定目录下所有文件到另一目录下
        FileUtils.copyFileToDirectory(srcFile, destDirectory);     // 拷贝文件到指定目录中，该目录下没有则新建 D:/test/test2/123.txt
        FileUtils.copyFileToDirectory(srcFile, destDirectory, false);
        FileUtils.copyToDirectory(srcDirectory, destDirectory); // srcDirectory可以为具体文件，也可以为目录，实际上调用copyDirectoryToDirectory和copyFileToDirectory

    }

    /**
     * 常用的删除文件api
     */
    @Test
    public void deleteTest() throws IOException {
        File file = FileUtils.getFile("D:/test/test1/123.txt");
        FileUtils.delete(file);                         // 删除单个文件
        File fileDirectory = FileUtils.getFile("D:/test/test1");
        FileUtils.deleteDirectory(fileDirectory);         // 删除整个目录,目录本身也被删除
        FileUtils.forceDelete(fileDirectory);         // 强制删除，如果是目录则目录本身也被删除
        FileUtils.deleteQuietly(new File("D://test/test1"));     // 如果是目录则目录本身也被删除；不会抛出异常,安静删除
        FileUtils.cleanDirectory(new File("D://test/test1"));  // 清除目录中的内容,不会删除该目录, 遍历目录中的文件，如果是目录则递归删除；如果是文件则强制删除，删除失败（文件不存在或无法删除）都会抛出异常
        FileUtils.directoryContains(fileDirectory, file);  // 确定child元素是否存在于directory目录中，child可以是文件或文件夹


    }
}

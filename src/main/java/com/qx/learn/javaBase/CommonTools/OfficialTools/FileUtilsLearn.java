package com.qx.learn.javaBase.CommonTools.OfficialTools;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * 文件IO是我们日常项目中经常使用到的基础API，常见的IO读写操作基础类字节流InputStream与OutputStream、字符流Reader与Writer
 * Apache开源项目封装的文件操作工具类FileUtils，提供的API涵盖了读取文件、拷贝文件、拷贝目录及文件、删除目录及文件、清除目录等
 */
public class FileUtilsLearn {

    /**
     * 常用的读取文件api
     */
    @Test
    public void readTest() {
        File file = FileUtils.getFile("D:/test/test1/123.txt");
        File file1 = FileUtils.getFile(new File("D://test/test1"), "123.txt", "456.txt");
    }
}

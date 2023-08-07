package com.qx.learn.day01.NIO;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author QiXin
 * @version v0.0
 * @Time 2022-08-08-8:39
 *
 * Java NIO(是从Java1.4版本开始引入的一套新的IO API,可以替代标准的Java IO API
 * NIO与原来的IO有同样的作用和目的，但是使用的方式完全不同，NIO的三大核心：Channel Buffer Selector
 * 传统IO基于字节流和字符流进行操作，而NIO基于Channel和Buffer(缓冲区)进行操作
 * NIO：数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。Selector(选择区)用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个线程可以监听多个数据通道
 *
 * Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，它不能前后移动流中的数据。
 * NIO将数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。这就增加了处理过程中的灵活性。但是，还需要检查是否该缓冲区中包含所有需要处理的数据。而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据
 *
 * IO的各种流是阻塞的，这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入
 * NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变得可以读取之前，该线程可以继续做其他的事情。
 * 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道
 *
 * Channel：和IO中的Stream差不多等级，Stream是单向的，Channel是双向的
 * 譬如：InputStream, OutputStream.而Channel是双向的，既可以用来进行读操作，又可以用来进行写操作
 * 主要实现有：FileChannel DatagramChannel SocketChannel ServerSocketChannel 分别可以对应文件IO、UDP和TCP（Server和Client）
 *
 * Buffer：NIO中的关键Buffer实现有：ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer
 * 分别对应基本数据类型: byte, char, double, float, int, long, short，还有其余的Buffer
 *
 * Selector
 * Selector运行单线程处理多个Channel，如果你的应用打开了多个通道，但每个连接的流量都很低，使用Selector就会很方便。
 * 要使用Selector, 得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪
 *
 */
public class NioTest {
    /*
    bio使用FileInputStream读取文件内容，注意使用io读写文件通常使用字节流
    因为字符只有在内存中才会形成，而硬盘上保存文件或进行传输是，都是字节组成的二进制对象
     */
    @Test
    public void method1(){
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream("src/bio.txt"));
            byte [] buf = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(buf)) != -1)
            {
                for(int i=0; i<bytesRead; i++)
                    System.out.print((char)buf[i]);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /*
    使用Buffer主要是操作缓冲区的指针，通过Position Limit 来控制如何读写
    flip()、clear()、compact()就是进行指针操作的方法
     */
    public static void method2(){
        RandomAccessFile aFile = null;
        try{
            aFile = new RandomAccessFile("src/nio.txt","rw");
            FileChannel fileChannel = aFile.getChannel();    // 文件通道
            ByteBuffer buf = ByteBuffer.allocate(1024);      // 分配缓存空间
            while(fileChannel.read(buf) != -1)         // channel.read(buf) 写入数据到缓存区  or buf.put(字节或字节数组)
            {
                buf.flip();     // flip():将缓存区的写入模式转换为读取模式，并移动Position到开始位置，Limit移动到末尾
                while(buf.hasRemaining())      // 判断当前位置和limit位置之间是否有元素
                {
                    System.out.print((char)buf.get());  // buf.get() 从缓存区中读取数据
                }
                buf.compact();  // 调用clear()或compact()将缓存区切换为写入模式，这两种方法对指针的操作不一致
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}


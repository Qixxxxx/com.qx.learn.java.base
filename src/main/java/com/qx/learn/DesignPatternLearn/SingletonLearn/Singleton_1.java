package com.qx.learn.DesignPatternLearn.SingletonLearn;


/**
 * 单例模式：系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能
 * 使用场景：
 * 1.需要频繁的进行创建和销毁的对象；
 * 2.创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
 * 3.工具类对象；
 * 4.频繁访问数据库或文件的对象
 * <p>
 * 单例推荐写法：双重检查、饿汉式、静态内部类
 */

/**
 * 饿汉式,故名思议，很饿，所以在类加载的时候就直接创建类的实例
 * 优点：线程安全
 * 缺点：内存浪费，类加载之后就被创建了实例
 */
public class Singleton_1 {

    private static final Singleton_1 INSTANCE = new Singleton_1();

    private Singleton_1() {
    }

    public static Singleton_1 getInstance() {

        return INSTANCE;
    }
}


/**
 * 饿汉式（静态代码块创建）
 */

class Singleton_2 {

    private static final Singleton_2 INSTANCE;

    private Singleton_2() {
    }

    public Singleton_2 getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE = new Singleton_2();
    }
}


/**
 * 懒汉式，线程不安全，不可用
 * 这种写法起到了Lazy Loading的效果，但是只能在单线程下使用
 * 如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例
 */
class Singleton_3 {

    private static Singleton_3 INSTANCE;

    private Singleton_3() {
    }

    public static Singleton_3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton_3();
        }
        return INSTANCE;
    }

}

/**
 * 懒汉式，线程安全，不推荐用
 * 使用  synchronized 加锁
 * 缺点：效率太低了
 */
class Singleton_4 {

    private static Singleton_4 INSTANCE;

    private Singleton_4() {
    }

    public static synchronized Singleton_4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton_4();
        }
        return INSTANCE;
    }

}

/**
 * 双重检查：推荐使用
 * Double-Check概念对于多线程开发者来说不会陌生，
 * 如代码中所示，我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了
 * 这样，实例化代码只用执行一次，后面再次访问时，判断if (singleton == null)，直接return实例化对象
 *
 */
class Singleton_5 {

    private static volatile Singleton_5 INSTANCE;    // volatile保证可见性

    private Singleton_5() {
    }

    public static Singleton_5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton_5.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton_5();
                }
            }
        }
        return INSTANCE;
    }

}

/**
 * 静态内部类，推荐使用
 * 跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程
 * 类的静态属性只会在第一次加载类的时候初始化，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的
 */
class Singleton_6 {

    private Singleton_6() {
    }

    private static class SingletonInstance {
        private static final Singleton_6 INSTANCE = new Singleton_6();
    }

    public static Singleton_6 getInstance() {
        return SingletonInstance.INSTANCE;
    }

}
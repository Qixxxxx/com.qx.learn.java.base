package com.qx.learn.day03;

/**
 *
 *  枚举类的应用：
 *          类的对象只有有限个，确定的
 *          定义一组常量
 *
 *  JDK1.5之前需要自定义枚举类，JDK1.5之后使用可使用enum关键字定义枚举类
 *
 *  枚举类的属性：
 *          枚举类对象的属性不允许被改动，所以使用  private final修饰
 *          被private final修饰的属性需要在构造器中为其赋值
 *          若枚举类显示的定义了带参构造器，则在列举枚举值时，必须传入对应的参数
 *
 *  枚举类的创建：
 *          枚举类的构造器是私有化的，所有对象均在类内创建完成，且对象声明为 public static final
 *          枚举类的实例变量均声明为 private final
 *
 *  使用Enum创建枚举类：
 *      enum创建的默认继承了Enum类，不能继承别的类
 *      所有实例对象必须在一开头列出，且使用逗号(,)分割 分号(;)结尾
 *
 *      常用方法： values()：返回枚举类型的对象数组，可以用来遍历所有的枚举值
 *                toString(): enum重写了toString方法
 * @author Qi
 * @version 1.0
 * @create 2021-07-22 10:00
 */

public class EnumTest {
    public static void main(String[] args) {
        // 使用枚举类
        Season spring = Season.SPRING;
        SeasonEnum spring1 = SeasonEnum.SPRING;
        System.out.println(spring.toString());
        System.out.println(spring1.toString());
        // 使用values遍历枚举数组
        SeasonEnum[] s = spring1.values();
        for (SeasonEnum seasonEnum : s){
            System.out.println(seasonEnum);
        }
    }
}

// 自定义枚举类
class Season{

    private final String SEAONNAME;
    private final String SEASONDESC;

    private Season(String seasonName, String seasonDesc) {  // 枚举类的构造器为私有的
        this.SEAONNAME = seasonName;
        this.SEASONDESC = seasonDesc;
    }

    // 所有对象必须在类内进行实例化，且声明为 public static final
    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "白雪皑皑");
}


// 使用enum定义枚举类
enum SeasonEnum{
    // 所有实例对象在一开头写出
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "白雪皑皑");
    // private final修饰属性
    private final String seasonName;
    private final String seasonDesc;
    // 私有构造器
    private SeasonEnum(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName(){
        return seasonName;
    }

    public String getSeasonDesc(){
        return seasonDesc;
    }
}
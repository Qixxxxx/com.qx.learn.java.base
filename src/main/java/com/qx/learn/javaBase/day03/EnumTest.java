package com.qx.learn.javaBase.day03;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类的应用：
 * 类的对象只有有限个，确定的
 * 定义一组常量
 * <p>
 * 枚举类的属性：
 * 枚举类对象的属性不允许被改动，所以使用  private final修饰
 * 被private final修饰的属性需要在构造器中为其赋值
 * 若枚举类显示的定义了带参构造器，则在列举枚举值时，必须传入对应的参数
 * <p>
 * 使用Enum创建枚举类：
 * enum创建的默认继承了Enum类，不能继承别的类
 * 所有实例对象必须在一开头列出，且使用逗号(,)分割 分号(;)结尾
 * <p>
 * 常用方法： values()：返回枚举类型的对象数组，可以用来遍历所有的枚举值
 * toString(): enum重写了toString方法
 *
 * @author Qi
 * @version 1.0
 * @create 2021-07-22 10:00
 */
public enum EnumTest {
    // 所有实例对象在一开头写出
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "白雪皑皑");

    // private final修饰属性
    private final String seasonName;
    private final String seasonDesc;

    EnumTest(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public static EnumTest fromName(String seasonName) {
        return enumTestMap.get(seasonName);
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    private static final Map<String, EnumTest> enumTestMap;

    static {
        enumTestMap = new HashMap<>();
        for (EnumTest enumTest : EnumTest.values()) {
            enumTestMap.put(enumTest.seasonName, enumTest);
        }
    }
}

package com.qx.learn.day03;

/**
 *
 *  注解(Annotation)就是代码中的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相应的处理。
 *      元注解： 用于修饰其他注解定义：
 *          @ Retention  指定注解的生命周期，包含RetentionPolicy类型的成员变量，有如下三个值
 *              RetentionPolicy.SOURCE      在源文件中有效，编译器直接丢弃
 *              RetentionPolicy.CLASS       在class文件中有效，运行Java程序是，JVM不会保留注解    默认值
 *              RetentionPolicy.RUNTIME     运行时有效，JVM保留注解，程序可通过反射获取该注解
 *
 *          @ Target     指定被修饰的注解能用于修饰哪些程序元素
 *              CONSTRUCTOR         描述构造器
 *              FIELD               描述域（成员变量）
 *              LOCAL_VARIABLE      描述局部变量
 *              METHOD              描述方法
 *              PACKAGE             描述包
 *              PARAMETER           描述参数
 *              TYPE                描述类、接口(包括注解类型)或enum声明
 *
 *          @ Documented    指定被修饰的Annotation类将被javadoc工具提取成文档
 *              定义为Documented的注解必须设置Retention值为RUNTIME
 *
 *          @ Inherited     指明被修饰的Annotation类将具有继承性
 *
 *
 *      生成文档相关的注解：
 *          @ author 标明开发该类模块的作者，多个作者之间使用逗号（，）分割
 *          @ version标明该类模块的版本
 *          @ see参考转向，也就是相关主题
 *          @ since从哪个版本开始增加的
 *          @ param对方法中某参数的说明，如果没有参数就不能写
 *          @ return对方法返回值的说明，如果方法的返回值类型是void就不能写
 *          @ exception对方法可能抛出的异常进行说明，如果方法没有用 throws显式抛出的异常就不能写
 *          其中：
 *              @ param @ return和 @ exception这三个标记都是只用于方法的。
 *              @ param的格式要求：@ param 形参名 形参类型 形参说明
 *              @ return的格式要求：@ return 返回值类型 返回值说明
 *              @ exception的格式要求：@ exception 异常类型 异常说明
 *              @ param和 @ exception可以并列多个
 *
 *      编译时格式检查(JDK内置的三个基本注解)：
 *          @ Override:限定重写父类方法，该注解只能用于方法
 *          @ Deprecated:用于表示所修饰的元素(类，方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 *          @ SuppressWarnings:抑制编译器警告,可以根据需求抑制警告
 *              使用：@ SuppressWarnings({"all", "unused", ...})
 */
public class AnnotationTest {
}

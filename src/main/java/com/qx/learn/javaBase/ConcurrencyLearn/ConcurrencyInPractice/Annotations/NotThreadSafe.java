package com.qx.learn.javaBase.ConcurrencyLearn.ConcurrencyInPractice.Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface NotThreadSafe {

}

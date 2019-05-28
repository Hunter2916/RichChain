package com.maijia.rc.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * .PerActivity注解代替上面例子中的Singleton注解
 * 使用这个的目的:在同一个作用范围内,Provide方法提供的依赖对象就会变成单例,也就是说依赖需
 * 求方不管依赖几次Provide方法提供的依赖对象,Dagger2都只会调用一次这个方法
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}

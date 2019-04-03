package ru.mvvm.ui

import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes

/**
 * Аннотация для указания лэйаута экрана или фрагмента
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Layout(@LayoutRes val id: Int, @MenuRes val menu: Int = 0)
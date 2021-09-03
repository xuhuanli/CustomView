package com.gome.customview.myviews

import android.content.Context
import android.util.TypedValue

/**
 *  @author xuhuanli2017@gmail.com
 *
 */

fun sp2px(context: Context, sp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), context.resources.displayMetrics).toInt()
}

fun dp2px(context: Context, dp: Int): Int {
    return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
}
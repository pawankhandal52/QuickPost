package com.pawan.quickpost.utils.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Pawan Kumar Sharma on 16-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */

fun View.getParentActivity():AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper){
        if(context is AppCompatActivity){
            return context
        }
        context = context.baseContext

    }
    return null
}
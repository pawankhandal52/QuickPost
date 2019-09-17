package com.pawan.quickpost.base

import androidx.lifecycle.ViewModel
import com.pawan.quickpost.injection.component.DaggerViewModelInjector
import com.pawan.quickpost.injection.component.ViewModelInjector
import com.pawan.quickpost.injection.module.NetworkModule
import com.pawan.quickpost.ui.PostListViewModel
import com.pawan.quickpost.ui.post.PostViewModel

/**
 * Created by Pawan Kumar Sharma on 14-Sep-19.
 * Fleeca India Pvt Ltd
 * Android Developer
 * android_developer1@fleeca.in
 * +917737947610
 */
abstract class BaseViewModel:ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
        }
    }
}
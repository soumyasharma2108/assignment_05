package com.neelasurya.myapplication.base

import androidx.lifecycle.ViewModel
import com.neelasurya.myapplication.injection.component.DaggerViewModelInjector
import com.neelasurya.myapplication.injection.component.ViewModelInjector
import com.neelasurya.myapplication.injection.module.NetworkModule
import com.neelasurya.myapplication.feature.post.PostListViewModel
import com.neelasurya.myapplication.feature.post.PostViewModel


abstract class BaseViewModel: ViewModel(){
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
package com.simpleapp.dependency

import com.simpleapp.viewmodel.LoginViewModel
import org.koin.dsl.module


object AppModule {
    val viewModelModule = module {
        single { LoginViewModel() }
    }

    val listModule = listOf(viewModelModule)
}
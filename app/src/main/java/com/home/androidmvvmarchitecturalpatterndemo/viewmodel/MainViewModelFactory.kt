package com.home.androidmvvmarchitecturalpatterndemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.androidmvvmarchitecturalpatterndemo.model.repositories.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
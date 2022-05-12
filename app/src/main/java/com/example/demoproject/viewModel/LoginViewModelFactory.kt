package com.example.demoproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs

class LoginViewModelFactory(
    private val repository: Repository,
    private val sharedPrefs: SharedPrefs
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, sharedPrefs) as T
    }
}
package com.example.demoproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.model.Qoute
import com.example.demoproject.model.User
import com.example.demoproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class WelcomeViewModel(private val repository: Repository):ViewModel() {

    fun getQuote(){

    }
}
package com.example.demoproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.model.User
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: Repository, private val sharedPrefs: SharedPrefs) :
    ViewModel() {
    val userResponse: MutableLiveData<User> = MutableLiveData()
    lateinit var accessToken: String
    lateinit var clientId: String
    fun loginUser(user: User) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val call: Call<User> = repository.loginUser(user)
                call.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.body() != null) {
                            userResponse.postValue(response.body())
                            response.headers().apply {
                                accessToken = this["Access-Token"].toString()
                                clientId = this["client"].toString()
                            }
                            sharedPrefs.apply {
                                token = accessToken
                                client = clientId
                            }
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("Exception", "Data not found")
                    }
                })
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }
}
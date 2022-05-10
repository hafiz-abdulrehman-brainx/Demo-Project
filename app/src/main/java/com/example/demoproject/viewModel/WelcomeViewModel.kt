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
    val userResponse :MutableLiveData<User> = MutableLiveData()

    fun loginUser( user: User){
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                val user = User(email = "hafiz_training@mailinator.com",password = "123456")
                val call : Call<User> =   repository.loginUser(user)
                call.enqueue(object:Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response?.body() !=null){
                            userResponse.postValue(response.body())
                        }

                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                       Log.d("Exception","Data not found")
                    }

                })
            }
            catch (e:Exception)
            {
                Log.d("Exception", e.toString())
            }
        }
    }
    fun getQuote(){

    }
}
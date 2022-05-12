package com.example.demoproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.model.Quotes
import com.example.demoproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeViewModel(private val repository: Repository) : ViewModel() {
    val quotesResponse: MutableLiveData<Quotes> = MutableLiveData()
    fun getQuotes(
        contentType: String,
        accessToken: String,
        client: String,
        uid: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getQuotes(contentType, accessToken, client, uid)
                    .enqueue(object : Callback<Quotes> {
                        override fun onResponse(
                            call: Call<Quotes>,
                            response: Response<Quotes>
                        ) {
                            Log.d("response", response.toString())
                            quotesResponse.postValue(response.body())
                        }

                        override fun onFailure(call: Call<Quotes>, t: Throwable) {
                            Log.d("response", t.message.toString())
                        }
                    })
            } catch (e: Exception) {
                Log.d("Exception2", e.toString())
            }
        }
    }
}
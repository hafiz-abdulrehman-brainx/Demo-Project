package com.example.demoproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.model.TrainingCategories
import com.example.demoproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrainingActivityViewModel(private val repository: Repository) : ViewModel() {
    val trainingResponse: MutableLiveData<TrainingCategories> = MutableLiveData()
    fun getTrainingCategories(
        contentType: String,
        accessToken: String,
        client: String,
        uid: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val call: Call<TrainingCategories> =
                    repository.getTrainingCategories(contentType, accessToken, client, uid)
                call.enqueue(object : Callback<TrainingCategories> {

                    override fun onResponse(
                        call: Call<TrainingCategories>,
                        response: Response<TrainingCategories>
                    ) {
                        if (response?.body() != null) {
                            trainingResponse.postValue(response.body())
                            Log.d("response", response.toString())
                        }
                    }

                    override fun onFailure(call: Call<TrainingCategories>, t: Throwable) {
                        Log.d("response", t.message.toString())
                    }
                })
            } catch (e: Exception) {
                Log.d("Exception2", e.toString())
            }
        }
    }
}
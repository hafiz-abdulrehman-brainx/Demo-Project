package com.example.demoproject.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.adapters.TrainingRecyclerAdapter
import com.example.demoproject.databinding.ActivityTrainingBinding
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import com.example.demoproject.viewModel.TrainingActivityViewModel
import com.example.demoproject.viewModel.TrainingViewModelFactory

class TrainingActivity : AppCompatActivity() {
    lateinit var binding: ActivityTrainingBinding
    lateinit var trainingNames: List<String>
    lateinit var trainingViewModel: TrainingActivityViewModel
    lateinit var sharedPrefs: SharedPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = SharedPrefs(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        initializeViewModel()
        initializeTrainingNames()

        val token = sharedPrefs.token.toString()
        val client = sharedPrefs.client.toString()
        Log.d("sharedPrefs","token: $token client: $client")
        trainingViewModel.getTrainingCategories(
            "application/json",
            token,
            client,
            "stage_agent@mailinator.com")
        trainingViewModel.trainingResponse.observe(this, Observer { response->
            trainingNames = mutableListOf(response.toString())
            Log.d("training response",response[0].title)
        })


        binding.Rv2.apply {
            adapter = TrainingRecyclerAdapter(trainingNames)
            layoutManager =
                GridLayoutManager(this@TrainingActivity, 2, GridLayoutManager.VERTICAL, false)
        }


    }

    private fun initializeViewModel() {
        val repository = Repository()
        val trainingViewModel1: TrainingActivityViewModel by viewModels {
            TrainingViewModelFactory(
                repository
            )
        }
        trainingViewModel = trainingViewModel1

    }

    private fun initializeTrainingNames() {
        trainingNames = mutableListOf(
            "Business Planning",
            "Marketing Properties",
            "Personal Marketing",
            "Prospecting Listings",
            "Technology & Tools",
            "Contacts & Forms"
        )


    }
}
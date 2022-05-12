package com.example.demoproject.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.adapters.TrainingRecyclerAdapter
import com.example.demoproject.databinding.ActivityTrainingBinding
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import com.example.demoproject.viewModel.TrainingActivityViewModel
import com.example.demoproject.viewModel.TrainingViewModelFactory

class TrainingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainingBinding
    private var trainingNames = mutableListOf<String>()
    private lateinit var trainingViewModel: TrainingActivityViewModel
    private lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = SharedPrefs(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeViewModel()

        val token = sharedPrefs.token.toString()
        val client = sharedPrefs.client.toString()

        trainingViewModel.getTrainingCategories(
            "application/json",
            token,
            client,
            "stage_agent@mailinator.com"
        )
        trainingViewModel.trainingResponse.observe(this) { response ->
            trainingNames.addAll(response.map { it.title })
            binding.Rv2.apply {
                adapter = TrainingRecyclerAdapter(trainingNames)
                layoutManager =
                    GridLayoutManager(this@TrainingActivity, 2, GridLayoutManager.VERTICAL, false)
            }
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
}

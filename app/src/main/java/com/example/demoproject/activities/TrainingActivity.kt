package com.example.demoproject.activities

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.R
import com.example.demoproject.adapters.TrainingRecyclerAdapter
import com.example.demoproject.databinding.ActivityTrainingBinding
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import com.example.demoproject.viewModel.TrainingActivityViewModel
import com.example.demoproject.viewModel.TrainingViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrainingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainingBinding
    private var trainingNames = mutableListOf<String>()
    private lateinit var trainingViewModel: TrainingActivityViewModel
    private lateinit var sharedPrefs: SharedPrefs
    private lateinit var trainingAdapter:TrainingRecyclerAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
            setHomeAsUpIndicator(com.google.android.material.R.drawable.material_ic_keyboard_arrow_left_black_24dp)
        }
        sharedPrefs = SharedPrefs(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeViewModel()

        val token = sharedPrefs.token.toString()
        val client = sharedPrefs.client.toString()

        binding.Rv2.apply {
            trainingAdapter=TrainingRecyclerAdapter(trainingNames)
            adapter = trainingAdapter
            layoutManager =
                GridLayoutManager(this@TrainingActivity, 2, GridLayoutManager.VERTICAL, false)
        }
        trainingViewModel.getTrainingCategories(
            "application/json",
            token,
            client,
            "stage_agent@mailinator.com"
        )
        setTrainingObserver()
    }
    private fun setTrainingObserver() {
        trainingViewModel.trainingResponse.observe(this) { response ->
            trainingNames.addAll(response.map { it.title })
            lifecycleScope.launch(Dispatchers.Main) {
               trainingAdapter.notifyDataSetChanged()
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

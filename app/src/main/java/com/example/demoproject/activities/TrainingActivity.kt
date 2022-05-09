package com.example.demoproject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.adapters.TrainingRecyclerAdapter
import com.example.demoproject.databinding.ActivityTrainingBinding

class TrainingActivity : AppCompatActivity() {
    lateinit var binding: ActivityTrainingBinding
    lateinit var trainingNames:List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        initializeTrainingNames()
        binding.Rv2.apply {
            adapter = TrainingRecyclerAdapter(trainingNames)
            layoutManager = GridLayoutManager(this@TrainingActivity,2,GridLayoutManager.VERTICAL,false)
        }


    }

    private fun initializeTrainingNames() {
        trainingNames = mutableListOf(
            "Business Planning",
            "Marketing Properties",
            "Personal Marketing",
            "Propecting Listings",
            "Technology & Tools",
            "Contacts & Forms"
        )
    }
}
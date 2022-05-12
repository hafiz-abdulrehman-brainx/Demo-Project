package com.example.demoproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.databinding.TrainingItemLayoutBinding

class TrainingRecyclerAdapter(private val trainingNames: List<String>) :
    RecyclerView.Adapter<TrainingRecyclerAdapter.MyTrainingViewHolder>() {

    private lateinit var binding: TrainingItemLayoutBinding

    inner class MyTrainingViewHolder(
        val binding: TrainingItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTrainingViewHolder {
        binding =
            TrainingItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyTrainingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyTrainingViewHolder, position: Int) {
        with(holder.binding) {
            TvCard1.text = trainingNames[position]
        }
    }

    override fun getItemCount(): Int {
        return trainingNames.size
    }
}
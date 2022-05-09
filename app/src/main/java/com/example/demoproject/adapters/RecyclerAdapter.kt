package com.example.demoproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.databinding.ItemLayoutBinding

class RecyclerAdapter(private val titles:List<String>, private val images:List<Int>):RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    private lateinit var _myListener: onItemClickListener
    private lateinit var binding: ItemLayoutBinding
    interface onItemClickListener{
        fun onItemClick(position:Int)
    }
    fun setOnClickListener(myListener: onItemClickListener){
        _myListener = myListener
    }
    inner class MyViewHolder(val binding: ItemLayoutBinding,listener: onItemClickListener):RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding, _myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding){
            TvCard.text = titles[position]
            IvCard.setImageResource(images[position])

        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}
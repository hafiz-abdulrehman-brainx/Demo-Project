package com.example.demoproject
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var images:List<Int>
    lateinit var titles:List<String>
    lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAssets()
        val adapter = RecyclerAdapter( titles,images )
        binding.Rv.adapter = adapter
        binding.Rv.apply {
            layoutManager =GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
        }
        adapter.setOnClickListener(object :RecyclerAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext().applicationContext,"item clicked :${titles[position].toString()}", Toast.LENGTH_SHORT).show()
                if(position ==4)
                {
                    val intent = Intent(requireContext().applicationContext,TrainingActivity::class.java).run{
                        startActivity(this)
                    }
                }
            }
        })
    }

    private fun setAssets() {
        titles = mutableListOf(
            "Do Today",
            "Activities & Tips",
            "Track it",
            "Events",
            "Training",
            "Say & Share"
        )
        images = mutableListOf(
            R.drawable.todo,
            R.drawable.tips,
            R.drawable.progress,
            R.drawable.events,
            R.drawable.training,
            R.drawable.comment
        )
    }
}
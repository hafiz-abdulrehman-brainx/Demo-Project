package com.example.demoproject.fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.R
import com.example.demoproject.adapters.RecyclerAdapter
import com.example.demoproject.activities.TrainingActivity
import com.example.demoproject.databinding.FragmentHomeBinding
import com.example.demoproject.viewModel.WelcomeViewModel
import androidx.fragment.app.viewModels
import com.example.demoproject.model.User
import com.example.demoproject.repository.Repository
import com.example.demoproject.viewModel.WelcomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var images:List<Int>
    lateinit var titles:List<String>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var welcomeViewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        adapter.setOnClickListener(object : RecyclerAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext().applicationContext,"item clicked :${titles[position]}", Toast.LENGTH_SHORT).show()
                if(position ==4)
                {
                    Intent(requireContext().applicationContext, TrainingActivity::class.java).run{
                        startActivity(this)
                    }
                }
            }
        })

        createViewModel()

        val bundle = arguments
        Log.d("bundle object", bundle.toString())
        val username = bundle?.getString("username") ?: "hafiz_training@mailinator.com"
        val password = bundle?.getString("password") ?: "123456"
        val user = User(email = username!!,password = password)
        welcomeViewModel.loginUser(user)
        welcomeViewModel.userResponse.observe(viewLifecycleOwner) { response ->
        binding.welcomeTxt.append(response.name.toString())
        }
    }
    private fun createViewModel() {
        val repository = Repository()
        val welcomeViewModel1 : WelcomeViewModel by viewModels{ WelcomeViewModelFactory(repository) }
        welcomeViewModel = welcomeViewModel1

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
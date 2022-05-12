package com.example.demoproject.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demoproject.MainActivity
import com.example.demoproject.R
import com.example.demoproject.activities.TrainingActivity
import com.example.demoproject.adapters.RecyclerAdapter
import com.example.demoproject.databinding.FragmentHomeBinding
import com.example.demoproject.model.User
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import com.example.demoproject.viewModel.LoginViewModel
import com.example.demoproject.viewModel.LoginViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var images: List<Int>
    private lateinit var titles: List<String>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPrefs: SharedPrefs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = SharedPrefs(requireActivity())
        setAssets()
        initializeViewModel(sharedPrefs)
        recyclerFunction()
        val username = sharedPrefs.username
        val password = sharedPrefs.password
        val user = User(email = username!!, password = password)
        loginViewModel.loginUser(user)
        loginViewModel.userResponse.observe(viewLifecycleOwner) { response ->
            binding.welcomeTxt.text = "WELCOME ${response.name.toString()}"
        }

        binding.btnLogout.setOnClickListener {
            if (sharedPrefs.login) {
                sharedPrefs.editor.apply {
                    clear()
                    apply()
                }
                Intent(requireContext().applicationContext, MainActivity::class.java).run {
                    startActivity(this)
                }
            }
        }

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
            R.drawable.event,
            R.drawable.training,
            R.drawable.comment
        )
    }

    private fun initializeViewModel(sharedPrefs: SharedPrefs) {
        val repository = Repository()
        val loginViewModel1: LoginViewModel by viewModels {
            LoginViewModelFactory(
                repository,
                sharedPrefs
            )
        }
        loginViewModel = loginViewModel1

    }

    private fun recyclerFunction() {
        val adapter = RecyclerAdapter(titles, images)
        binding.Rv.adapter = adapter
        binding.Rv.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
        adapter.setOnClickListener(object : RecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "item clicked :${titles[position]}",
                    Toast.LENGTH_SHORT
                ).show()
                if (position == 4) {
                    Intent(requireContext().applicationContext, TrainingActivity::class.java).run {
                        startActivity(this)
                    }
                }
            }
        })
    }

}
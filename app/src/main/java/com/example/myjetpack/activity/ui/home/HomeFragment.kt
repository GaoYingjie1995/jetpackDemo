package com.example.myjetpack.activity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myjetpack.R
import com.example.myjetpack.base.BaseVBindingFragment
import com.example.myjetpack.databinding.FragmentHomeBinding


class HomeFragment : BaseVBindingFragment<FragmentHomeBinding>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = vBinding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        textView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHomeFragment22("leopold");
            findNavController().navigate(R.id.action_homeFragment_to_homeFragment22, bundleOf("name" to "leo"))
            findNavController().navigate(action)
        }
    }



    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }
}
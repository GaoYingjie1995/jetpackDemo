package com.example.myjetpack.activity.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpack.base.BaseVBindingFragment
import com.example.myjetpack.databinding.FragmentHome2Binding
import com.example.myjetpack.databinding.FragmentHomeBinding


class HomeFragment2 : BaseVBindingFragment<FragmentHome2Binding>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = vBinding.textHome
        homeViewModel.text2.observe(viewLifecycleOwner) {
            textView.text = it
        }
        homeViewModel.text2.value = arguments?.getString("name")

        Log.e("HomeFragment2", "onViewCreated: name = " +arguments?.getString("name"))
    }



    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHome2Binding {
        return FragmentHome2Binding.inflate(inflater,container,false)
    }
}
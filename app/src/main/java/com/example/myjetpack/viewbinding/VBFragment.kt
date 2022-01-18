package com.example.myjetpack.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myjetpack.base.BaseVBindingFragment
import com.example.myjetpack.databinding.FragmentViewBindingBinding

class VBFragment : BaseVBindingFragment<FragmentViewBindingBinding>() {



    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewBindingBinding {
        return FragmentViewBindingBinding.inflate(inflater,container,false)
    }
}
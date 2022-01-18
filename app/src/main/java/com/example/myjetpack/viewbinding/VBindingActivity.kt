package com.example.myjetpack.viewbinding

import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityVbindingBinding


class VBindingActivity : BaseVBindingActivity<ActivityVbindingBinding>() {
    override fun getViewBinding(): ActivityVbindingBinding {
        return ActivityVbindingBinding.inflate(layoutInflater)
    }


}
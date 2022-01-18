package com.example.myjetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import com.example.myjetpack.R
import com.example.myjetpack.base.BaseVBindingActivity
import com.example.myjetpack.databinding.ActivityLiveDataBinding
import com.example.myjetpack.viewmodel.MyViewModel

class LiveDataActivity : BaseVBindingActivity<ActivityLiveDataBinding>() {
    //    private val livaDataVm  by lazy { ViewModelProvider(this).get(LiveDataVM::class.java) }
    private val liveDataVm  by viewModels<LiveDataVM>()


    val myViewModel1 by viewModels<MyViewModel>() //activity-ktx 扩展库提供
    //    val myViewModel2  = ViewModelProvider(this).get(MyViewModel::class.java);
    val myViewModel3  by ViewModelLazy(MyViewModel::class,{viewModelStore},{defaultViewModelProviderFactory})
//    val myViewModel4  = ViewModelProvider(viewModelStore,defaultViewModelProviderFactory).get(MyViewModel::class.java);



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVBinding.btAdd.setOnClickListener {
            liveDataVm.add()
        }
        mVBinding.btReduce.setOnClickListener {
            liveDataVm.reduce()
        }
        liveDataVm.count.observe(this, Observer {
            mVBinding.textView.text = it?.toString()
        })

        liveDataVm.mediatorMerger.addSource(liveDataVm.data1, Observer {
            liveDataVm.mediatorMerger.value = it;
        })
        liveDataVm.mediatorMerger.addSource(liveDataVm.data2, Observer {
            liveDataVm.mediatorMerger.value = it;
        })
        liveDataVm.mediatorMerger.observe(this, Observer {
            mVBinding.textView2.text = it.name
        })
        mVBinding.btMerge1.setOnClickListener {
            liveDataVm.data1.value = UserInfo("曹甜庭",18)
        }
        mVBinding.btMerge2.setOnClickListener {
            liveDataVm.data2.value = UserInfo("高英杰",20)
        }

    }

    override fun getViewBinding(): ActivityLiveDataBinding {
        return ActivityLiveDataBinding.inflate(layoutInflater)
    }
}
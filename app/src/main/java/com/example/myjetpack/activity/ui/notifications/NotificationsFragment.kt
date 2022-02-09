package com.example.myjetpack.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myjetpack.base.BaseVBindingFragment
import com.example.myjetpack.databinding.FragmentNotificationsBinding


class NotificationsFragment : BaseVBindingFragment<FragmentNotificationsBinding>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)


        val textView: TextView = vBinding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationsBinding {
        return FragmentNotificationsBinding.inflate(inflater,container,false)
    }
}
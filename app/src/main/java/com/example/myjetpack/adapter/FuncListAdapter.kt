package com.example.myjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myjetpack.R
import com.example.myjetpack.bean.FuncBean

class FuncListAdapter(private val funcList:MutableList<FuncBean>) : RecyclerView.Adapter< FuncListAdapter.ViewHolder>() {
    private lateinit var clickListener: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_func_list,parent,false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: FuncListAdapter.ViewHolder, position: Int) {
        holder.textView.text = funcList[position].funcName;
    }

    override fun getItemCount(): Int {
        return funcList.size;
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.tv_func_name)
            textView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }

    public fun setOnItemClick(clickListener:(position:Int)->Unit){
        this.clickListener = clickListener
    }

}
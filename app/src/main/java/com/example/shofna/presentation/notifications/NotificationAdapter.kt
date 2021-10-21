package com.example.shofna.presentation.notifications


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.R

import com.example.shofna.databinding.NotificationAdapterBinding
import com.example.shofna.model.Data
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel


class NotificationAdapter (var viewModel: MainViewModel, var context :Context?, var data:List<Data>) : RecyclerView.Adapter<CustomViewHolders>() {
    override fun getItemCount(): Int {

        return  data.size

    }

    override fun onBindViewHolder(p0: CustomViewHolders, p1: Int) {
        p0.bind(context,data.get(p1),viewModel,p1)


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolders {

        val  binding: NotificationAdapterBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.notification_adapter,p0,false)

        return  CustomViewHolders(binding)
    }


}
class CustomViewHolders (
    public val binding: NotificationAdapterBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(context: Context?, data: Data, viewModels: MainViewModel, int: Int ) {

        binding.listener = ClickHandler()
        binding.viewModel = viewModels
        binding.data = data
        binding.context = context as MainActivity?
    }

}
package com.example.shofna.presentation.homefragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.R
import com.example.shofna.databinding.MainAdapterBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.model.Item

import com.example.shofna.presentaion.homefragment.viewmodel.BindableString
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel


class Main_Adapter(
    var viewModel: MainViewModel, var context: Context?, var data: List<Item>
) : RecyclerView.Adapter<CustomViewHolder>() {

    var preferenceHelper : PreferenceHelper ?=null

    var row_index : Int ? = 0

    override fun getItemCount(): Int {
        return data.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(context, data.get(p1),viewModel)

        p0.binding.name.setOnClickListener{
//        homeFragment!!.index = p1

            viewModel.SwtichingCategories(p1)


            preferenceHelper?.setUsername(data.get(p1).name)
            // notifyItemChanged(p1);

            row_index = p1


            notifyDataSetChanged();

        }


        if(row_index==p1){
            p0.binding.name.setTextColor(Color.parseColor("#131313"));

        }
        else
        {
            p0.binding.name.setTextColor(Color.parseColor("#cfcfcf"));
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding: MainAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.main_adapter, p0, false
        )

        return CustomViewHolder(binding)

    }


}


class CustomViewHolder(
     val binding: MainAdapterBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(context: Context?, data: Item,viewModel: MainViewModel) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.bindingadater = BindableString()
        binding.viewModel = viewModel
    }


}

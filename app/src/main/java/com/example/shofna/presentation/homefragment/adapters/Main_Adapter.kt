package com.example.shofna.presentation.homefragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.R
import com.example.shofna.databinding.MainItemsAdapterBinding
import com.example.shofna.helper.Warning_MotionToast
import com.example.shofna.model.Item

import java.util.ArrayList
import com.example.shofna.presentaion.homefragment.viewmodel.BindableString
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel


class Main_Adapter(
    var viewModel: MainViewModel, var context: Context?, var data: List<Item>
) : RecyclerView.Adapter<CustomViewHolder>() {


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
            // notifyItemChanged(p1);

            row_index = p1


            notifyDataSetChanged();

        }


        if(row_index==p1){
            p0.binding.name.setBackgroundColor(Color.parseColor("#c00a27"));
            p0.binding.name.setTextColor(Color.parseColor("#ffffff"));

        }
        else
        {
            p0.binding.name.setBackgroundColor(Color.parseColor("#FFE6EAED"));
            p0.binding.name.setTextColor(Color.parseColor("#000000"));
        }

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding: MainItemsAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.main_items_adapter, p0, false
        )

        return CustomViewHolder(binding)

    }


}


class CustomViewHolder(
     val binding: MainItemsAdapterBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(context: Context?, data: Item,viewModel: MainViewModel) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.bindingadater = BindableString()
        binding.viewModel = viewModel
    }


}

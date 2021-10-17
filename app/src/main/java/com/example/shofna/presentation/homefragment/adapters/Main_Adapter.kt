package com.example.shofna.presentation.homefragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.R
import com.example.shofna.databinding.MainAdapterBinding
import com.example.shofna.model.Item

import com.example.shofna.presentaion.homefragment.viewmodel.BindableString
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.webview.WebViewActivity


class Main_Adapter(
    var viewModel: MainViewModel, var context: Context?, var data: List<Item>
) : RecyclerView.Adapter<CustomViewHolder>() {


    var row_index : Int ? = null
    var observe_index : Int ? = 0
    override fun getItemCount(): Int {
        return data.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(context, data.get(p1),viewModel)

//        viewModel.ItemIndex.observe(context as MainActivity, Observer {
//            observe_index = it
//        })
//
//        if (observe_index != null){
//          viewModel.SwtichingCategories(observe_index!!)
//          row_index = observe_index
//
//      }else{
//            viewModel.SwtichingCategories(0)
//            row_index = 0
//
//        }
//
//        p0.binding.name.setOnClickListener{
//
//            val url = data.get(p1).link
//            if(!url.isNullOrEmpty()) {
//
//
//                val intent = Intent(context, WebViewActivity::class.java)
//                intent.putExtra("link", url);
//
//                (context as MainActivity).startActivity(intent)
//
//            }else{
//
//
//
//                viewModel.SwtichingCategories(p1)
//
//                row_index = p1
//
//                notifyDataSetChanged();
//
//
//            }
//        }
//
//
//






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

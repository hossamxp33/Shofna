package com.example.shofna.presentation.departments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.R
import com.example.shofna.databinding.DepartmentAdapterBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.model.Item
import com.example.shofna.presentation.homefragment.HomeFragment

import com.example.shofna.presentaion.homefragment.viewmodel.BindableString
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.webview.WebViewActivity


class Departments_Adapter(
    var viewModel: MainViewModel, var context: Context?, var data: List<Item>
) : RecyclerView.Adapter<CustomViewHolder>() {



    override fun getItemCount(): Int {
        return data.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(context, data.get(p1),viewModel)

        p0.binding.name.setOnClickListener{

            val url = data.get(p1).link
            if(!url.isNullOrEmpty()) {


                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("link", url);

                (context as MainActivity).startActivity(intent)

            }else{

                val bundle = Bundle()
                val frag = HomeFragment()

                frag.arguments = bundle

                bundle.putInt("position", p1)

                ClickHandler().switchFragment(frag,context!!)


            // notifyItemChanged(p1);





            }
        }



    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding: DepartmentAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(p0.context),
            R.layout.department_adapter, p0, false
        )

        return CustomViewHolder(binding)

    }


}


class CustomViewHolder(
     val binding: DepartmentAdapterBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(context: Context?, data: Item,viewModel: MainViewModel) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.bindingadater = BindableString()
        binding.viewModel = viewModel
    }


}

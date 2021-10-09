package com.example.shofna.presentation.homefragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.R
import com.example.shofna.databinding.NewsAdapterBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.model.ItemX
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity


class NewsAdapter(var activity: FragmentActivity, var menu: List<ItemX>): RecyclerView.Adapter<NewsAdapter.CustomViewHolder>() {

        private val menusData: List<ItemX> = menu


        override fun getItemCount(): Int {
            return menusData.size
        }
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
            val binding: NewsAdapterBinding
                    = DataBindingUtil.inflate (LayoutInflater.from(activity), R.layout.news_adapter,p0,false)
//


            return CustomViewHolder(binding)
        }

        override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
            p0.bind(p1,activity,menu.get(p1))


        }




        class CustomViewHolder(
            private val binding: NewsAdapterBinding

        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position:Int, context: Context?, data: ItemX) {
                binding.listener = ClickHandler()
                binding.data = data
                binding.context = context as MainActivity
                //   binding.datamodel = datamodel
                binding.index = position

            }

        }
}
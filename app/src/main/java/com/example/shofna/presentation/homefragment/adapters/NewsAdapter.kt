package com.example.shofna.presentation.homefragment.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.R
import com.example.shofna.databinding.NewsAdapterBinding
import com.example.shofna.model.ItemX
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.newsdetailsactivity.News_Details_Activity


import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.shofna.presentation.webview.WebViewActivity


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

             p0.binding.cardLayout.setOnClickListener {
                  val url = menusData.get(p1).link
                 if(!url.isNullOrEmpty()){


                     val intent = Intent(this.activity, WebViewActivity::class.java)
                     intent.putExtra("link", url);

                     (this.activity as MainActivity).startActivity(intent)

                 }else {

                     val intent = Intent(this.activity, News_Details_Activity::class.java)

                     intent.putExtra("data", menu.get(p1))
                     intent.putExtra("id", menu.get(p1).id)

                     val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                         activity,
                         p0.binding.appCompatImageView, "news_image"
                     )

                     (activity).startActivityForResult(intent, 1000, options.toBundle());
                 }
             }

        }




        class CustomViewHolder(
             val binding: NewsAdapterBinding

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
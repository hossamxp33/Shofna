package com.example.shofna.presentation.newsdetailsactivity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shofna.R
import com.example.shofna.databinding.NewsAdapterBinding
import com.example.shofna.databinding.RelatedNewsAdapterBinding
import com.example.shofna.model.ItemX
import com.example.shofna.model.Related
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.adapters.NewsAdapter
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.newsdetailsactivity.News_Details_Activity
import kotlinx.android.synthetic.main.news_details_fragment.*

class Related_News_Adapter(var viewModel: MainViewModel, var activity: AppCompatActivity, var menu: List<Related>): RecyclerView.Adapter<Related_News_Adapter.CustomViewHolder>() {

    private val menusData: List<Related> = menu

    override fun getItemCount(): Int {
        return menusData.size
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val binding: RelatedNewsAdapterBinding
                = DataBindingUtil.inflate (LayoutInflater.from(activity), R.layout.related_news_adapter,p0,false)
//


        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {

        p0.bind(p1,activity,menu.get(p1),viewModel)

        p0.binding.cardView.setOnClickListener {
            activity.progress.visibility = View.VISIBLE

            viewModel.GetNewsDetails(menu[p1].id)

            //notifyDataSetChanged();
        }

    }




    class CustomViewHolder(
        val binding: RelatedNewsAdapterBinding

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position:Int, context: Context?, data: Related,viewModel:MainViewModel) {
            binding.listener = ClickHandler()
            binding.data = data
            binding.context = context as News_Details_Activity
            //   binding.datamodel = datamodel

        }

    }
}

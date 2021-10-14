package com.example.shofna.presentation.newsdetailsactivity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shofna.R
import com.example.shofna.databinding.NewsDetailsFragmentBinding
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.helper.setDatetext
import com.example.shofna.model.ItemX
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.newsdetailsactivity.adapter.Related_News_Adapter
import kotlinx.android.synthetic.main.news_details_fragment.*

class News_Details_Activity : AppCompatActivity() {

    var binding: NewsDetailsFragmentBinding? = null
    lateinit var MainAdapter: Related_News_Adapter

    lateinit var viewModel: MainViewModel

    var itemX: ItemX? = null

    var item_id: Int? = null

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_details_fragment)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.news_details_fragment)

        binding!!.myviewModel = viewModel

        itemX = intent.getParcelableExtra("data")

        item_id = itemX?.id

        viewModel.GetNewsDetails(item_id!!)

        try {


            viewModel.DetailsDataLD?.observe(this, Observer {

                ResourceUtil().loudImage(this, news_details_image, it.item.photo)


                news_title.text = it.item.name

                setDatetext(created, it.item.created)

                MainAdapter = Related_News_Adapter(viewModel, this, it.related)
                related_news_recycle.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                related_news_recycle.adapter = MainAdapter;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    description!!.movementMethod = ScrollingMovementMethod()
                    description!!.text =
                        Html.fromHtml(it.item.description, Html.FROM_HTML_MODE_COMPACT)
                }


                progress.visibility = View.GONE
            })




            show!!.setOnClickListener {
                show!!.visibility = View.INVISIBLE
                hide!!.visibility = View.VISIBLE
                description!!.maxLines = Int.MAX_VALUE
            }
            hide!!.setOnClickListener {
                hide!!.visibility = View.INVISIBLE
                show!!.visibility = View.VISIBLE
                description!!.maxLines = 2
            }

        } catch (e: Exception) {
        }


    }


}







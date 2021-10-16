package com.example.shofna.presentation.homefragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shofna.R
import com.example.shofna.databinding.MainFragmentBinding

import com.example.shofna.model.ItemX
import com.example.shofna.model.MainView
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.Permissions
import com.example.shofna.presentation.homefragment.adapters.NewsAdapter
import com.example.shofna.presentation.homefragment.adapters.SliderAdapter
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.bottom_nav_content.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.pager_layout.view.*

import kotlinx.android.synthetic.main.recycles_layout.view.*


open class HomeFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    lateinit var MainAdapter: Main_Adapter

    var index: Int = 0
    var datArray = ArrayList<ItemX>()
    var adapter: NewsAdapter? = null
    var MainData: MainView? = null
    var position = 0

    companion object {

        const val TAG = "HomeFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: MainFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment, container, false
        )
        val context = context as MainActivity
        view.viewmodel = viewModel

        ////

        viewModel.GetMainData(context)


        val mypager = view.pagerlayout.pager

        viewModel.MainDataLD?.observe(viewLifecycleOwner, Observer {

            datArray.clear()

            MainAdapter = Main_Adapter(viewModel, context, it.items)
            view.pagerlayout.departments.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            view.pagerlayout.departments.adapter = MainAdapter;


            ///////////// view pager slider

            var indicator = view.pagerlayout.indicator

            mypager.adapter = it?.let { it1 -> SliderAdapter(requireActivity(), it1.sliders) }

            it.sliders.let { it1 -> Permissions().init(it1.size, context, this) }

            indicator.setViewPager(mypager)

            view.progress.visibility = View.GONE

            stoploading()

            MainData = it

            datArray.addAll(it.items.get(index).items)

            adapter = NewsAdapter(context as FragmentActivity, datArray)
            view.pagerlayout.news_recycle.layoutManager = LinearLayoutManager(activity)
            view.pagerlayout.news_recycle?.adapter = adapter


        })



        viewModel.loadingLivedat.observe(viewLifecycleOwner,
            Observer { loading -> shimmer_view_container1.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        SwitchingCategories()
        if (arguments != null) {

            position = arguments?.getInt("position")!!


            Handler().postDelayed(
                { view.pagerlayout.departments.scrollToPosition(position) }, 1000
            )
        }

        return view.root
    }

     fun ItemSelected() {
       ( context as MainActivity).bottom_nav_bar.setItemSelected(R.id.home)
    }
    fun SwitchingCategories() {
        viewModel.ItemIndex.observe(requireActivity(), androidx.lifecycle.Observer {
            try {
                datArray.clear()

                datArray.addAll(MainData!!.items.get(it).items)

                adapter!!.notifyDataSetChanged()

            } catch (e: Exception) {

            }


        })
    }


    override fun onResume() {
        super.onResume()
        shimmer_view_container1.startShimmerAnimation()
        shimmer_view_container3.startShimmerAnimation()

    }


    override fun onPause() {
        shimmer_view_container1.stopShimmerAnimation()
        shimmer_view_container3.stopShimmerAnimation()

        super.onPause()
    }

    fun stoploading() {
        shimmer_view_container1?.setVisibility(View.GONE)
        shimmer_view_container3?.setVisibility(View.GONE)

        shimmer_view_container1?.stopShimmerAnimation()
        shimmer_view_container3?.stopShimmerAnimation()
    }


}
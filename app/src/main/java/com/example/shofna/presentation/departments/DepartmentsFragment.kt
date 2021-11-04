package com.example.shofna.presentaion.homefragment

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shofna.R
import com.example.shofna.databinding.DepratmentFragmentBinding
import com.example.shofna.databinding.MainFragmentBinding
import com.example.shofna.helper.Warning_MotionToast
import com.example.shofna.model.Item
import com.example.shofna.model.ItemX
import com.example.shofna.model.MainView
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.Permissions
import com.example.shofna.presentation.departments.Departments_Adapter
import com.example.shofna.presentation.homefragment.Main_Adapter
import com.example.shofna.presentation.homefragment.adapters.NewsAdapter
import com.example.shofna.presentation.homefragment.adapters.SliderAdapter
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.pager_layout.*
import kotlinx.android.synthetic.main.pager_layout.view.*
import kotlinx.android.synthetic.main.recycles_layout.*
import kotlinx.android.synthetic.main.recycles_layout.view.*
import org.jetbrains.anko.support.v4.find


 class DepartmentsFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java) }

    lateinit var MainAdapter: Departments_Adapter

    var index : Int =  0
    var datArray = ArrayList<ItemX>()
    var adapter: NewsAdapter? = null
    var MainData : MainView? = null

    private var curremtlang ///true if arabic
            = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: DepratmentFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.depratment_fragment, container, false)



        val context = context as MainActivity
         view.viewmodel = viewModel

        viewModel.GetMainData(context)

        viewModel.MainDataLD?.observe(viewLifecycleOwner, Observer {

            datArray.clear()

            MainAdapter = Departments_Adapter(viewModel, context, it.items)
            view.departments.layoutManager = LinearLayoutManager(requireActivity())
            view.departments.adapter = MainAdapter;


           view.progress.visibility = View.GONE


            MainData = it

            datArray.addAll(it.items.get(index).items!!)




        })



        viewModel.loadingLivedat.observe(viewLifecycleOwner,
            Observer { loading -> shimmer_view_container1.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        SwitchingCategories()

        return view.root
    }

    fun SwitchingCategories(){
        viewModel.ItemIndex.observe(requireActivity(),androidx.lifecycle.Observer {
            try {
                datArray.clear()

                datArray.addAll(MainData!!.items.get(it).items!!)

                adapter!!.notifyDataSetChanged()

            }
            catch (e:Exception){

            }



        })
    }






}
package com.example.shofna.presentation.menufragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.convertTo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.databinding.ContactUsFragmentBinding
import com.example.shofna.databinding.MenuFragmentBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.registerActivity.RegisterActivity

class ContactUsFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: ContactUsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.contact_us_fragment, container, false
        )

        view.listener = ClickHandler()
        view.context = context as MainActivity


        viewModel.GetMainData(requireContext())
        viewModel.MainDataLD?.observe(requireActivity(), Observer {

                view.data = it.config[1]
        })


        return view.root

    }
}
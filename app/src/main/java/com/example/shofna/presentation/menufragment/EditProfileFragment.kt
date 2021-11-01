package com.example.shofna.presentation.menufragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.databinding.EditUserFragmentBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.registerActivity.RegisterActivity
import kotlinx.android.synthetic.main.edit_user_fragment.view.*

class EditProfileFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: EditUserFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.edit_user_fragment, container, false
        )

        var userid = PreferenceHelper.getUserId()

        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"



        viewModel.getUserData(userid)

        viewModel.RegisterDataLD?.observe(requireActivity(), Observer {

            view.username.setText(it.data?.username)
            view.mobile.setText(it.data?.mobile)
            view.email.setText(it.data?.email)


        })




        view.edit.setOnClickListener {
            view.edit.text = "أنتظر.."
            view.edit.isEnabled = false

            if (view.username.text.toString() == "" && view.mobile.text.toString() == "" && view.email.text.toString() == "") {
                Toast.makeText(requireContext(),"الرجاء أكمل البيانات",Toast.LENGTH_SHORT).show()
                view.edit.text = getText(R.string.dialog_ok)
                view.edit.isEnabled = true
            } else
                if(!view.email.text.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())){
                Toast.makeText(requireContext(), "valid email address", Toast.LENGTH_SHORT).show()
                    view.edit.text = getText(R.string.dialog_ok)
                    view.edit.isEnabled = true
            }
            else {

                viewModel.editUserData(userid,view.username.text.toString(),view.mobile.text.toString(),view.email.text.toString())

            }
        }

        viewModel.EditDataLD?.observe(requireActivity() , Observer {
            if (it.success == false){


                Toast.makeText(context , "خطأ حاول مرة أخري", Toast.LENGTH_SHORT).show()

                view.edit.text = getText(R.string.dialog_ok)

                view.edit.isEnabled = true

            }else {

                PreferenceHelper.setUsername("")
                PreferenceHelper.setToken("",context)
                PreferenceHelper.setUserId(0)

                val intent = Intent(requireActivity(), RegisterActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }

        })

        return view.root

    }
}
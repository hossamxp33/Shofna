package com.example.shofna.presentation.menufragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.convertTo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.databinding.ChangePwBinding
import com.example.shofna.databinding.MenuFragmentBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.registerActivity.RegisterActivity

class EditPasswordFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val view: ChangePwBinding = DataBindingUtil.inflate(inflater, R.layout.change_pw, container, false)

        view.click = ClickHandler()
        view.context = context as MainActivity

        view.changepwbtn.setOnClickListener {
            view.changepwbtn.text = "أنتظر.."
            view.changepwbtn.isEnabled = false
            viewModel.ChangePassword(view.currentPw.text.toString())
        }

        viewModel.EditDataLD?.observe(requireActivity() , Observer {
            if (!it.success){
                Toast.makeText(context , "خطأ حاول مرة أخري", Toast.LENGTH_SHORT).show()

                view.changepwbtn.text = getText(R.string.dialog_ok)

                view.changepwbtn.isEnabled = true

            }else {
                Toast.makeText(context , "تم تغيير كلمة المرور", Toast.LENGTH_SHORT).show()

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
package com.simpleapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.simpleapp.R
import com.simpleapp.activity.LoginActivity
import com.simpleapp.databinding.LayoutProfileBinding
import com.simpleapp.model.User
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.layout_profile.*
import org.jetbrains.anko.startActivity

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var user = Hawk.get("user", User()).apply {
            name = "Eva Dwi Rochmawati"
            gender = "Female"
            phone = "+6281287048963"
        }
        val binding = DataBindingUtil.inflate<LayoutProfileBinding>(inflater, R.layout.layout_profile, container, false)
        binding.apply {
            model = user
            lifecycleOwner = this@ProfileFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){
        txt_logout?.setOnClickListener {
            Hawk.delete("user")
            activity?.startActivity<LoginActivity>()
        }
    }
}
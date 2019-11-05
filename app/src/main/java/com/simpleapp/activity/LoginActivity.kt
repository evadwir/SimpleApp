package com.simpleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.simpleapp.R
import com.simpleapp.databinding.ActivityLoginBinding
import com.simpleapp.dependency.AppModule.listModule
import com.simpleapp.model.User
import com.simpleapp.viewmodel.LoginViewModel
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class LoginActivity : AppCompatActivity() {

    private val loginViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin { modules(listModule) }
        Hawk.init(this).build()
        if(Hawk.get("user", null) != null){
            startActivity<MainActivity>()
        }
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,
            R.layout.activity_login
        )
        binding.apply {
            vm = loginViewModel
            lifecycleOwner = this@LoginActivity
        }
        initView()
    }

    fun initView(){
        btn_login?.setOnClickListener {
            var user = User().apply {
                email = loginViewModel.email.value ?: ""
                password = loginViewModel.password.value ?: ""
            }
            Hawk.put("user", user)
            startActivity<MainActivity>()
        }
    }
}

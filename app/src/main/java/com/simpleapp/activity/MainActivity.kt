package com.simpleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.simpleapp.R
import com.simpleapp.fragment.HomeFragment
import com.simpleapp.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val MENU_HOME = "home"
        private val MENU_PROFILE = "profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeMenu(HomeFragment(), MENU_HOME)
        initView()
    }

    fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.my_apps)
        txt_profile?.setOnClickListener {
            changeMenu(ProfileFragment(), MENU_PROFILE)
        }
        txt_home?.setOnClickListener {
            changeMenu(HomeFragment(), MENU_HOME)
        }
    }

    private fun changeMenu(fragment: Fragment, menu: String){
        changeColorMenu(menu)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    private fun changeColorMenu(menu: String){
        txt_home?.apply {
            isSelected = false
        }
        txt_profile?.apply {
            isSelected = false
        }

        when(menu){
            MENU_HOME -> {
                txt_home?.apply {
                    isSelected = true
                }
            }

            else -> {
                txt_profile?.apply {
                    isSelected = true
                }
            }
        }

    }
}

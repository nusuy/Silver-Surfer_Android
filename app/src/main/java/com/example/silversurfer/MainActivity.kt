package com.example.silversurfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView

lateinit var fragment1: Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = ButtonDialog(this)

        fragment1 = Fragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        val navigation = findViewById<NavigationBarView>(R.id.navigation)
        navigation.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()
                R.id.account -> supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()
                R.id.product -> supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()
                R.id.asset -> supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()
                R.id.menu -> dialog.myDig()

            }
        }
    }
}
package com.techinnovation.mainfragment

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton
import com.techinnovation.mainfragment.databinding.ActivityMainBinding
import com.techinnovation.mainfragment.databinding.FragmentDialogBoxBinding
import com.ubldigital.shared.base.bottomsheets.BaseBottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<AppCompatButton>(R.id.findDialog).setOnClickListener {



            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DialogTestFragment())
                .addToBackStack(null) // Optional: Adds this transaction to the back stack
                .commit()
        }


//        val binding = ActivityMainBinding.inflate(layoutInflater,parent,false)
//        setContentView(binding.root)




        }

    }

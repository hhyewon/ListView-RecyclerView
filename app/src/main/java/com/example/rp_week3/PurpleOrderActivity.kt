package com.example.rp_week3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.ActivityMainBinding
import com.example.rp_week3.databinding.PurpleOrderBinding

class PurpleOrderActivity : AppCompatActivity() {

    private lateinit var binding: PurpleOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PurpleOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.back.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}
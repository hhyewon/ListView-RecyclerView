package com.example.rp_week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.view.View
import com.example.rp_week3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.presentVi.setOnClickListener {
            intent= Intent(this, ClickActivity::class.java)
            startActivity(intent)
        }


        binding.purpleOrderVi.setOnClickListener {
            intent= Intent(this, PurpleOrderActivity::class.java)
            startActivity(intent)
        }

        binding.myMenuVi.setOnClickListener {
            intent= Intent(this, MyMenuActivity::class.java)
            startActivity(intent)
        }

        binding.userName2.setText(Html.fromHtml("<u>"+binding.userName2.text+"</u>"))

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}
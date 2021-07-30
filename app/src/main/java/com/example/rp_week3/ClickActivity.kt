package com.example.rp_week3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuClickBinding

class ClickActivity :AppCompatActivity() {

    private lateinit var binding: MyMenuClickBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyMenuClickBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.back.setOnClickListener {
            intent = Intent(this, MyMenuActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.myMenuItemName.text=intent.getStringExtra("name")
        binding.myMenuItemIv.setImageResource(intent.getIntExtra("img",0))
        binding.myMenuItemPrice.text = intent.getStringExtra("price")
        binding.myMenuItemSize.text= intent.getStringExtra("size")
        binding.myMenuItemPrice2.text=intent.getStringExtra("price")
        binding.myMenuItemCup.text=intent.getStringExtra("cup")


    }




    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}
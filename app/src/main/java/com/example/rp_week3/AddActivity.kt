package com.example.rp_week3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuAddBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AddActivity : AppCompatActivity() {
    
    private fun savePref() {  //데이터를 저장하는 함수
        val sharedPreferences =
            getSharedPreferences(MyMenuActivity.KEY_PREFS, Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val typeToken = object : TypeToken<ArrayList<MyMenus>>() {}.type
        var mymenu: ArrayList<MyMenus> = gson.fromJson(
            sharedPreferences.getString(MyMenuActivity.KEY_DATA, "없음"),
            typeToken
        )  // gson -> json
        if (sharedPreferences.getString(MyMenuActivity.KEY_DATA, "없음").equals("없음")) {


        } else {
            mymenu.add(
                MyMenus(
                    intent.getIntExtra("add_img", 0),
                    binding.mainTem.text.toString()
                            + binding.mainRealName.text.toString(),
                    binding.mainMenuPrice.text.toString(),
                    binding.mainMenuSize.text.toString(),
                    binding.mainMenuCup.text.toString()
                )
            )//여기에 지금 이화면에서 저장할값을입력

            var json = gson.toJson(mymenu)
            Log.e("값이 잘 쌓이고있니", mymenu.toString())

            editor.putString(MyMenuActivity.KEY_DATA, json)
            editor.apply()
            Log.d("debug", "Data saved")
        }


    }


    private lateinit var binding: MyMenuAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyMenuAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.back.setOnClickListener {
            intent = Intent(this, AddsActivity::class.java)
            startActivity(intent)
        }

        binding.mainRealName.text = intent.getStringExtra("add_name")
        binding.mainImg.setImageResource(intent.getIntExtra("add_img", 0))
        binding.mainMenuPrice.text = intent.getStringExtra("add_price")
        binding.myMenuItemPrice2.text = intent.getStringExtra("add_price")
        binding.myMenuItemPrice3.text = intent.getStringExtra("add_price")




        binding.hotTv.setOnClickListener {
            binding.mainTem.text = ""
            !binding.iceTv.isSelected
        }

        binding.iceTv.setOnClickListener {
            binding.mainTem.text = "아이스 "
            !binding.hotTv.isSelected
        }

        binding.smallTv.setOnClickListener {
            binding.mainMenuSize.text = "Small"
            !binding.regularTv.isSelected
            !binding.largeTv.isSelected

        }

        binding.regularTv.setOnClickListener {
            binding.mainMenuSize.text = "Regular"
            !binding.smallTv.isSelected
            !binding.largeTv.isSelected
        }
        binding.largeTv.setOnClickListener {
            binding.mainMenuSize.text = "Large"
            !binding.smallTv.isSelected
            !binding.regularTv.isSelected
        }
        binding.mugTv.setOnClickListener {
            binding.mainMenuCup.text = " 머그컵"

        }
        binding.ilTv.setOnClickListener {
            binding.mainMenuCup.text = " 일회용컵"
        }



        binding.personalMore.setOnClickListener {
            savePref()
        }

        binding.addSave.setOnClickListener {
            val q = (binding.mainMenuSize.text.toString() + binding.mainTem.text.toString()
                    + binding.mainRealName.text.toString())
            Log.d("a", q)
            savePref()
            finish()
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}
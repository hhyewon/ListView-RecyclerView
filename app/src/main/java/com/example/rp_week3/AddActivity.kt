package com.example.rp_week3

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuAddBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AddActivity : AppCompatActivity() {
    //startactivity 어쩌구 활용해보기 !
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
            mymenu.add( //여기에 지금 이화면에서 저장할값을입력
                0,  // 제일 위에 보이게 !
                MyMenus(
                    intent.getIntExtra("add_img", 0),
                    binding.mainTem.text.toString()
                            + binding.mainRealName.text.toString(),
                    binding.mainMenuPrice.text.toString(),
                    binding.mainMenuSize.text.toString(),
                    binding.mainMenuCup.text.toString()
                )
            )
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
            finish()
        }

        binding.mainRealName.text = intent.getStringExtra("add_name")
        binding.mainImg.setImageResource(intent.getIntExtra("add_img", 0))
        binding.mainMenuPrice.text = intent.getStringExtra("add_price")
        binding.myMenuItemPrice2.text = intent.getStringExtra("add_price")
        binding.myMenuItemPrice3.text = intent.getStringExtra("add_price")


//        binding.hotTv.setOnClickListener {
//            binding.mainTem.text = ""
//            !binding.iceTv.isSelected
//        }

        binding.hotTv.setOnClickListener {
            binding.mainTem.text = ""
            if (binding.hotTv.tag.toString().equals("true")) {
                binding.hotTv.setBackgroundResource(R.drawable.btn_off)

                binding.hotTv.tag = "false"
                binding.iceTv.tag = "true"
                binding.iceTv.setBackgroundResource(R.drawable.btn_on)
            } else {
                binding.hotTv.setBackgroundResource(R.drawable.btn_on)
                binding.hotTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.iceTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.iceTv.setPaintFlags(binding.iceTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)

                binding.hotTv.setPaintFlags(binding.hotTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.hotTv.tag = "true"
                binding.iceTv.tag = "false"
                binding.iceTv.setBackgroundResource(R.drawable.btn_off)
            }
        }

        binding.iceTv.setOnClickListener {
            binding.mainTem.text = "아이스 "
            if (binding.iceTv.tag.toString().equals("true")) {
                binding.hotTv.setBackgroundResource(R.drawable.btn_on)
                binding.hotTv.tag = "true"
                binding.iceTv.tag = "false"
                binding.iceTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.iceTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.hotTv.setTextColor(Color.parseColor("#b1b1b1"))  //폰트 색상 변경
                binding.hotTv.setPaintFlags(binding.hotTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.iceTv.setPaintFlags(binding.iceTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG) //볼드
                binding.hotTv.setBackgroundResource(R.drawable.btn_off)
                binding.hotTv.tag = "false" // 다른 버튼은 꺼짐
                binding.iceTv.tag = "true"  // 켜짐
                binding.iceTv.setBackgroundResource(R.drawable.btn_on) //켜졌을 때 이미지
            }
        }


        binding.smallTv.setOnClickListener {
            binding.mainMenuSize.text = "Small"
            if (binding.smallTv.tag.toString().equals("true")) {
                binding.smallTv.setBackgroundResource(R.drawable.btn_off)
                binding.smallTv.tag = "false"
                binding.regularTv.tag = "false"
                binding.smallTv.tag = "false"
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.smallTv.setBackgroundResource(R.drawable.btn_on)
                binding.smallTv.tag = "true"
                binding.regularTv.tag = "false"
                binding.smallTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.largeTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.regularTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.largeTv.setPaintFlags(binding.largeTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.regularTv.setPaintFlags(binding.regularTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.smallTv.setPaintFlags(binding.smallTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.largeTv.tag = "false"
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
            }

        }

        binding.regularTv.setOnClickListener {
            binding.mainMenuSize.text = "Regular"
            if (binding.regularTv.tag.toString().equals("true")) {
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.smallTv.tag = "false"
                binding.largeTv.tag = "false"
                binding.regularTv.tag = "false"
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.regularTv.setBackgroundResource(R.drawable.btn_on)
                binding.regularTv.tag = "true"
                binding.smallTv.tag = "false"
                binding.regularTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.smallTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.largeTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.largeTv.setPaintFlags(binding.largeTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.smallTv.setPaintFlags(binding.smallTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.regularTv.setPaintFlags(binding.regularTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.largeTv.tag = "false"
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
                binding.smallTv.setBackgroundResource(R.drawable.btn_off)
            }
        }
        binding.largeTv.setOnClickListener {
            binding.mainMenuSize.text = "Large"
            if (binding.largeTv.tag.toString().equals("true")) {
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
                binding.smallTv.tag = "false"
                binding.regularTv.tag = "false"
                binding.largeTv.tag = "false"
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.largeTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.largeTv.setBackgroundResource(R.drawable.btn_on)
                binding.largeTv.tag = "true"
                binding.smallTv.tag = "false"
                binding.largeTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.smallTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.regularTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.regularTv.setPaintFlags(binding.regularTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.smallTv.setPaintFlags(binding.smallTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.largeTv.setPaintFlags(binding.largeTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.regularTv.tag = "false"
                binding.regularTv.setBackgroundResource(R.drawable.btn_off)
                binding.smallTv.setBackgroundResource(R.drawable.btn_off)
            }
        }
        binding.mugTv.setOnClickListener {
            binding.mainMenuCup.text = " 머그컵"
            binding.mainTem.text = "아이스 "
            if (binding.mugTv.tag.toString().equals("true")) {
                binding.ilTv.setBackgroundResource(R.drawable.btn_off)
                binding.ilTv.tag = "false"
                binding.mugTv.tag = "false"
                binding.mugTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.ilTv.setBackgroundResource(R.drawable.btn_off)
                binding.ilTv.tag = "false"
                binding.mugTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.ilTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.mugTv.setPaintFlags(binding.mugTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.ilTv.setPaintFlags(binding.ilTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.mugTv.tag = "true"
                binding.mugTv.setBackgroundResource(R.drawable.btn_on)
            }

        }
        binding.ilTv.setOnClickListener {
            binding.mainMenuCup.text = " 일회용컵"
            if (binding.ilTv.tag.toString().equals("true")) {
                binding.mugTv.setBackgroundResource(R.drawable.btn_off)
                binding.mugTv.tag = "false"
                binding.ilTv.tag = "false"
                binding.ilTv.setBackgroundResource(R.drawable.btn_off)
            } else {
                binding.mugTv.setBackgroundResource(R.drawable.btn_off)
                binding.mugTv.tag = "false"
                binding.ilTv.setTextColor(Color.parseColor("#4F2E6F"))
                binding.mugTv.setTextColor(Color.parseColor("#b1b1b1"))
                binding.ilTv.setPaintFlags(binding.ilTv.getPaintFlags() or Paint.FAKE_BOLD_TEXT_FLAG)
                binding.mugTv.setPaintFlags(binding.mugTv.getPaintFlags() or Paint.LINEAR_TEXT_FLAG)
                binding.ilTv.tag = "true"
                binding.ilTv.setBackgroundResource(R.drawable.btn_on)
            }
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
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

//    private lateinit var AddsActivity: AddsActivity

    /*
    companion object { //SharedPreferences에서는 데이터를 다룰때 키를 사용하므로 그때 사용할 키를 정의
        private const val KEY_PREFS = "shared_preferences"
        private const val KEY_DATA = "monster_data"
    }
    */

    private fun savePref() {  //데이터를 저장하는 함수
        //여기가 그 하나의 메뉴를 셋팅하고 저장해야하는애죠 네네 그 로직이 이거거든요 근데 그래서 여긴 어답터가없어용
        // 어뎁터가 왜 필요하다고생각하심미까  엄.. 안필요한가야? 저기 빨간 글씨떄매 ,, ㅋㅋㅋ아 같은 어답터 ?이건가요 ..??... 뒤로뒤로..잠깐만여 여기가
        //더하기 눌렀을때 그 값을 리스트에 저장하는거잖아여  ?  네
        val sharedPreferences =
            getSharedPreferences(MyMenuActivity.KEY_PREFS, Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        //쉐어드에 나만의 리스트가있고  나는 그 리스트를 불러온다
        // 잠깐만여 짱구좀 네
        val typeToken = object : TypeToken<ArrayList<MyMenus>>() {}.type
        var mymenu: ArrayList<MyMenus> = gson.fromJson(
            sharedPreferences.getString(MyMenuActivity.KEY_DATA, "없음"),
            typeToken
        )  // gson -> json
        if (sharedPreferences.getString(MyMenuActivity.KEY_DATA, "없음").equals("없음")) {
            //안해

        } else {
            //이러면  mymenu에  제 메뉴들이있곘져 ?   아ㅏ아 헐 된건가요? 추가는 됐으니
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
            // 다시 savepref 사용해서 저장   이러면 될거같은데요 ? 한번 해봐도 됩니까?넹넹
            // apply
            var json = gson.toJson(mymenu)
            Log.e("값이 잘 쌓이고있니", mymenu.toString())

            editor.putString(MyMenuActivity.KEY_DATA, json)
            editor.apply()
            //mymenu 액티비티에서 어댑터가 음
            //잘 쌓이네요 저 소리질렀어요 ㅈ대박ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ굳굳굳우와 ...ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
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
            val q = ( binding.mainMenuSize.text.toString() + binding.mainTem.text.toString()
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
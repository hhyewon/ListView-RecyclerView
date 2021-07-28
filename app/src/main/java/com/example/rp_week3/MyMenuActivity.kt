package com.example.rp_week3

import android.R.attr.checked
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuBinding
import com.example.rp_week3.databinding.MyMenuItemBinding


data class MyMenus(val img: Int, val name: String, val price: String, val size: String,var checked:Boolean =false)

class MyMenuActivity : AppCompatActivity() {

    var MyMenuArrayList = ArrayList<MyMenus>()


    fun isChecked(): Boolean {
        return false
    }
    private lateinit var customAdapter: CustomAdapter


    private lateinit var binding: MyMenuBinding
    private lateinit var binding2: MyMenuItemBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MyMenuBinding.inflate(layoutInflater)
        binding2 = MyMenuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding2.myMenuItemCb


        binding.myMenuLv.choiceMode = ListView.CHOICE_MODE_MULTIPLE



        binding.back.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu1,
                "(S)헤이즐넛 아이스 커피",
                "5,300원",
                "Small 일회용컵"
                ,false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu16,
                "블랙다이몬 카페수아(R)",
                "7,000원",
                "Regular 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu17,
                "(R)에스프레소 달고나크림라떼",
                "5,500원",
                "Regular 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu18,
                "(R)아이스 흑임자 크림라떼",
                "6,300원",
                "Regular 일회용컵"
                ,false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu2,
                "스웨디쉬 베리즈",
                "5,000원",
                "Regular 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu3,
                "(R)스파클링 스웨디쉬 레몬티",
                "6,300원",
                "Regular 머그컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu4,
                "(S)잉글리쉬 라떼",
                "5,800원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu5,
                "(S)아이스 그린티 라떼",
                "5,800원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu6,
                "(S)차이라떼",
                "5,800원",
                "Small 머그컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu7,
                "(S)화이트 포레스트IB",
                "6,800원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu8,
                "(S)블랙 포레스트IB",
                "6,800원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu9,
                "(S)베리베리IB",
                "6,800원",
                "Small 일회용컵"
                ,false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu10,
                "(S)그린티IB",
                "6,500원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu11,
                "(S)퓨어 바닐라IB",
                "6,000원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu12,
                "(S)핫 바닐라",
                "5,500원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu13,
                "(S)더블 초코렛",
                "5,500원",
                "Small 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu14,
                "자몽 쥬스",
                "4,800원",
                "Regular 일회용컵"
                ,false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu15,
                "딜라이트 피치그린티(R)",
                "6,800원",
                "Regular 일회용컵"
                ,false
            )
        )




        customAdapter = CustomAdapter(this, MyMenuArrayList)

        binding.myMenuLv.adapter = customAdapter



        binding.allCb.setOnClickListener { //전체 선택
            var count = 0
            count = binding.myMenuLv.adapter.count
            for (i in 0 until count) {
                binding.myMenuLv.setItemChecked(i, isChecked())
            }
            customAdapter.notifyDataSetChanged();

        }


    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }
}
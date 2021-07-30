package com.example.rp_week3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuAddsBinding


data class AddMenus(
    val add_img: Int,
    val add_name: String,
    val add_price: String,
)


class AddsActivity : AppCompatActivity() {

    var AddArrayList = ArrayList<AddMenus>()


    private lateinit var addsadapter: Addsadapter

    private lateinit var binding: MyMenuAddsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyMenuAddsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.back.setOnClickListener {
            finish()
        }

            AddArrayList.add(
                AddMenus(
                    R.drawable.bag_menu1,
                    "헤이즐넛 아메리카노",
                    "5,300원"
                )
            )
            AddArrayList.add(
                AddMenus(
                    R.drawable.bag_menu14,
                    "자몽 쥬스",
                    "4,800원"
                )
            )
            AddArrayList.add(
                AddMenus(
                    R.drawable.bag_menu8,
                    "블랙 포레스트IB",
                    "6,800원"
                )
            )
        AddArrayList.add(
            AddMenus(
                R.drawable.bag_menu3,
                "스파클링 스웨디쉬 레몬티",
                "6,300원"
            )
        )


        addsadapter = Addsadapter(this, AddArrayList)

        binding.addLv.adapter = addsadapter

        binding.addLv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position: Int, id -> // 상세정보 화면으로 이동하기(인텐트 날리기)
                intent = Intent(this, AddActivity::class.java)

                intent.putExtra("add_name", AddArrayList[position].add_name)
                intent.putExtra("add_img", AddArrayList[position].add_img)
                intent.putExtra("add_price", AddArrayList[position].add_price)

                startActivity(intent)
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
package com.example.rp_week3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.ReMyMenuBinding


data class ReMenu(
    val img2: Int,
    val name2: String,
    val price2: String,
    val size2: String,
    val cup2: String,
    var checked2: Boolean = false
)



class ReMyMenuActivity: AppCompatActivity() {

    var ReMyMenuArrayList = ArrayList<ReMenu>()

    private lateinit var reCustomAdapter: ReCustomAdaptor

    private lateinit var binding: ReMyMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReMyMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ReMyMenuArrayList.add(
            ReMenu(
                R.drawable.bag_menu16,
                "블랙다이몬 카페수아",
                "7,000원",
                "Small",
                " 일회용컵",
                false
            )
        )
        ReMyMenuArrayList.add(
            ReMenu(
                R.drawable.bag_menu17,
                "에스프레소 달고나크림라떼",
                "5,500원",
                "Regular",
                " 머그컵",
                false
            )
        )
        ReMyMenuArrayList.add(
            ReMenu(
                R.drawable.bag_menu18,
                "아이스 흑임자 크림라떼",
                "6,300원",
                "Large",
                " 일회용컵",
                false
            )
        )

        ReMyMenuArrayList.add(
            ReMenu(
                R.drawable.bag_menu2,
                "스웨디쉬 베리즈",
                "5,000원",
                "Small",
                " 일회용컵",
                false
            )
        )


//        reCustomAdapter = ReCustomAdaptor(this, ReMyMenuArrayList)

        binding.myMenuLv.adapter = reCustomAdapter
    }
}
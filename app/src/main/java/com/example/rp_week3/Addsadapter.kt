package com.example.rp_week3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rp_week3.databinding.MyMenuAddsItemBinding

class Addsadapter(context: Context, private val AddArrayList: ArrayList<AddMenus>) :
    BaseAdapter() {


    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    lateinit var binding: MyMenuAddsItemBinding


    override fun getCount(): Int = AddArrayList.size //arraylist size 반환

    override fun getItem(position: Int): Any = AddArrayList[position] // 위치값에따른 데이터 반환

    override fun getItemId(position: Int): Long = position.toLong() //포지션 값 반환


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        binding = MyMenuAddsItemBinding.inflate(inflater, parent, false)
        binding.addImg.setImageResource(AddArrayList[position].add_img)
        binding.addName.text = AddArrayList[position].add_name
        binding.addPrice.text = AddArrayList[position].add_price


        return binding.root

    }
}

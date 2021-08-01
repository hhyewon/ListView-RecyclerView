package com.example.rp_week3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rp_week3.databinding.ReMyMenuItemBinding


class ReCustomAdaptor(private val ReMyMenuArrayList: ArrayList<ReMenu>) :
    RecyclerView.Adapter<ReCustomAdaptor.ViewHolder>() {

//    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    lateinit var binding: ReMyMenuItemBinding

    data class ReMenu(
        val img2: Int,
        val name2: String,
        val price2: String,
        val size2: String,
        val cup2: String,
        var checked2: Boolean = false
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val img2: ImageView = itemView.my_menu_item_iv
//        val name2 TextView = itemView.my_menu_item_name
//        val price2: TextView = itemView.my_menu_item_price
//        val size2: TextView = itemView.my_menu_item_size
//        val cup2: TextView = itemView.my_menu_item_cup
//        val checked2: CheckBox = itemView.my_menu_item_ad

        fun setItem(mainData: MainData) {
//            binding.myMenuItemIv.setImageResource()
//            binding.myMenuItemName.text = mainData.my_menu_item_name
//            binding.myMenuItemSize.text = mainData.my_menu_item_size
//            binding.myMenuItemCup.text= mainData.my_menu_item_cup
//            binding.myMenuItemPrice.text=mainData.my_menu_item_price
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.text_row_item, parent, false)
//
//        binding = ReMyMenuItemBinding.inflate(inflater, parent, false)

//        val itemView = inflater.inflate(R.layout.music_recycler_item, parent, false)
        return ViewHolder(parent)

    }

    override fun onBindViewHolder(holder: ReCustomAdaptor.ViewHolder, position: Int) {
//        holder.setItem(ReMyMenuArrayList[position])
//        holder.musicLongClickListener(position)
    }

    override fun getItemCount(): Int = ReMyMenuArrayList.size



}





















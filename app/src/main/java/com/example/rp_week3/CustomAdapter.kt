package com.example.rp_week3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rp_week3.databinding.MyMenuItemBinding


class CustomAdapter(context: Context, private val MyMenuArrayList: ArrayList<MyMenus>) :
    BaseAdapter() {


    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    lateinit var binding: MyMenuItemBinding

    var dataSet: ArrayList<MyMenus> = arrayListOf()



    override fun getCount(): Int = MyMenuArrayList.size //arraylist size 반환

    override fun getItem(position: Int): Any = MyMenuArrayList[position] // 위치값에따른 데이터 반환

    override fun getItemId(position: Int): Long = position.toLong() //포지션 값 반환


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //getView는 최초 시점에는 화면 크기만큼 아이템 레이아웃이 들어갈 수 있는 공간을 채울 때 만큼 호출이 되고
        //그 다음에는 스크롤이 되서 아이템이 보일 때마다 호출이 되는 함수라고 보면 된다.
        // << 이걸 하기 위해서는 inflate 라는 작업이 필요하다.
        // 그래서 inflater를 통해 시스템에 대한 권한을 부여받고 (xml을 메모리에 올려 주는 것)

//        val view=convertView // 리턴 타입 자동으로 추론
//
//        return view

        binding = MyMenuItemBinding.inflate(inflater, parent, false)
        binding.myMenuItemIv.setImageResource(MyMenuArrayList[position].img)
        binding.myMenuItemName.text = MyMenuArrayList[position].name
        binding.myMenuItemPrice.text = MyMenuArrayList[position].price
        binding.myMenuItemSize.text = MyMenuArrayList[position].size
        binding.myMenuItemCup.text = MyMenuArrayList[position].cup
        binding.myMenuItemCb.isChecked = MyMenuArrayList[position].checked


        /*
        // 새 액티비티에 값 넘겨주고 호출하는 다른 방법 !!
        binding.itemCl.setOnClickListener {
            var intent  = Intent(convertView?.context, ClickActivity::class.java)
            intent.putExtra("name", MyMenuArrayList[position].name)
            intent.putExtra("img", MyMenuArrayList[position].img)
            intent.putExtra("size", MyMenuArrayList[position].size)
            intent.putExtra("price", MyMenuArrayList[position].price)
            convertView?.context?.startActivity(intent)
        }
        */


//        binding.myMenuItemCb.isChecked = MyMenuArrayList[position].checked

        binding.myMenuItemCb.setOnClickListener {
            val newState: Boolean =
                !MyMenuArrayList[position].checked //check->uncheck, uncheck -> check로 바꿔 저장
            MyMenuArrayList[position].checked = newState
        }

        binding.myMenuItemCb.isChecked =
            isChecked(position) // 그 값을 체크박스에 저장하여 체크된 것들이 그대로 남아있도록 함
        return binding.root
    }

    fun isChecked(position: Int): Boolean {  //체크박스 상태
        return MyMenuArrayList[position].checked
    }



    fun setAllChecked(ischeked: Boolean) {
        var count = 0
        count = MyMenuArrayList.size
        for (i in 0 until count) {
            MyMenuArrayList[i].checked = ischeked
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int): Boolean {
        val text: MyMenus = MyMenuArrayList[fromPosition]
        MyMenuArrayList.removeAt(fromPosition)
        MyMenuArrayList.add(toPosition, text)
        notifyDataSetChanged()
        return true
    }


}


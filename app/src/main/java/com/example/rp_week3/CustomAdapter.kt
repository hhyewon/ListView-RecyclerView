package com.example.rp_week3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import com.example.rp_week3.databinding.MyMenuItemBinding


class CustomAdapter(context: Context, private val MyMenuArrayList: ArrayList<MyMenus>) :
    BaseAdapter() {

    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    lateinit var binding: MyMenuItemBinding

    override fun getCount(): Int = MyMenuArrayList.size //arraylist size 반환

    override fun getItem(position: Int): Any = MyMenuArrayList[position] // 위치값에따른 데이터 반환

    override fun getItemId(position: Int): Long = position.toLong() //포지션 값 반환


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //getView는 최초 시점에는 화면 크기만큼 아이템 레이아웃이 들어갈 수 있는 공간을 채울 때 만큼 호출이 되고
        //그 다음에는 스크롤이 되서 아이템이 보일 때마다 호출이 되는 함수라고 보면 된다.
        // << 이걸 하기 위해서는 inflate 라는 작업이 필요하다.
        // 그래서 inflater를 통해 시스템에 대한 권한을 부여받고 (xml을 메모리에 올려 주는 것)

//
//        val view=convertView // 리턴 타입 자동으로 추론
////
//        return view



//  리스트뷰의 아이템 객체잔하여 겟뷰가?  네 거기가 그럼 체크박스부터 =ㅡ 까지?  그 뷰를 눌렀을때  클릭리스너  VIew의 컨텍스트가지고 새로운 인텐트시작?
       // ㅣ신기하네
        //근데 왜 MyMenuActivity 저기선 안되고 여기서는 되는ㄱㅇㅇ??여기는 근데 사실 좀
        // 세부적인거 설정할떄 하는거고 저기서 온 아이템뭐시기 그거는 아이템 자체를 눌렀을때하ㅣ는건데 왜안되는지 한번 확인하러가시죠
        binding = MyMenuItemBinding.inflate(inflater, parent, false)
//        binding.myMenuItemIv.imageAlpha=MyMenuArrayList[position].img
        binding.myMenuItemIv.setImageResource(MyMenuArrayList[position].img)
        binding.myMenuItemName.text = MyMenuArrayList[position].name
        binding.myMenuItemPrice.text = MyMenuArrayList[position].price
        binding.myMenuItemSize.text = MyMenuArrayList[position].size
        binding.myMenuItemCb.isChecked = MyMenuArrayList[position].checked

        binding.itemCl.setOnClickListener {
            var intent  = Intent(convertView?.context, ClickActivity::class.java)
            intent.putExtra("name", MyMenuArrayList[position].name)
            intent.putExtra("img", MyMenuArrayList[position].img)
            intent.putExtra("size", MyMenuArrayList[position].size)
            intent.putExtra("price", MyMenuArrayList[position].price)
            convertView?.context?.startActivity(intent)
        }

        //        binding.myMenuItemCb.isChecked = MyMenuArrayList[num].checked
//
//        binding.myMenuItemCb.setOnCheckedChangeListener {
//            if(binding.myMenuItemCb.isChecked){
//                MyMenuArrayList[num]
//            }
//        }

//
//        binding.myMenuItemCb.isChecked = (DocPath.parent as ListView).isItemChecked(position)
//        binding.myMenuItemCb.isChecked = ((ListView)parent).isItemChecked(position);
//
//
//        binding.myMenuItemCb.isChecked = (DocPath.parent as ListView).isItemChecked(position);
//

//        binding.myMenuItemCb.setOnClickListener {
//
//            var newState = !MyMenuArrayList.get(position).isChecked()
//             newState = !MyMenuArrayList.get(position).isChecked()
//            MyMenuArrayList.get(position).isChecked() = newState
//        }


//        binding.myMenuItemCb.isChecked  = MyMenuArrayList[position].checked
//        binding.myMenuItemCb.setOnCheckedChangeListener { buttonView, isChecked ->
//            MyMenuArrayList[position].checked = isChecked
//
//
//        }




//        class checkboxData(
//            var id: Long,
//            val checked: Boolean
//        )
        fun isChecked(position: Int): Boolean {
            return MyMenuArrayList[position].checked
        }

        binding.myMenuItemCb.isChecked= MyMenuArrayList[position].checked


        binding.myMenuItemCb.setOnClickListener {
            val newState: Boolean = !MyMenuArrayList[position].checked //check->uncheck, uncheck -> check로 바꿔 저장
            MyMenuArrayList[position].checked = newState
        }


//        binding.myMenuItemCb.setOnCheckedChangeListener { buttonView, isChecked ->
//
//            MyMenuArrayList[position].checked =newState
//        }
//        checked

        binding.myMenuItemCb.isChecked = isChecked(position) // 그 값을 체크박스에 저장하여 체크된 것들이 그대로 남아있도록 함
//        binding.myMenuItemCb.setOnCheckedChangeListener(isChecked(position))
        return binding.root
    }




}
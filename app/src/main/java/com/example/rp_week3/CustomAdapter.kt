package com.example.rp_week3

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.rp_week3.databinding.MyMenuItemBinding


class CustomAdapter(
    context: Context, private val MyMenuArrayList: ArrayList<MyMenus>,
    private val dropCallback: OnItemDrop
) :
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

//        val view = convertView // 리턴 타입 자동으로 추론
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
        binding.itemCl.setOnClickListener {
            var intent = Intent(convertView?.context, ClickActivity::class.java)
            Log.e("눌리냐?","ㅇㅇ")

            intent.putExtra("name", MyMenuArrayList[position].name)
            intent.putExtra("img", MyMenuArrayList[position].img)
            intent.putExtra("price", MyMenuArrayList[position].price)
            intent.putExtra("size", MyMenuArrayList[position].size)
            intent.putExtra("cup", MyMenuArrayList[position].cup)

            convertView?.context?.startActivity(intent)

        }
        binding.itemCl.setOnLongClickListener { v ->
//if (longClick){
            //  if (motionEvent?.action == MotionEvent.ACTION_DOWN) {
//                    if (flag == 0) {
            val dragShadowBuilder = MyDragShadowBuilder(v)
            val item = ClipData.Item(position.toString())   // 드롭된 View에 전달할 드래그중인 view의 인덱스
            // 전달할 데이터 담아서 인스턴스화
            val dragData =
                ClipData("position", arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            //     longClick=false

            v?.startDrag(dragData, dragShadowBuilder, v, 0)
//                    true
//                }else{
//                    flag=0
//                    }
            //     longClick=false
            //  }
//}
            false
        }


        binding.itemCl.setOnDragListener { v, event ->
            when (event.action) {
                // 드래그 시작 이벤트에서 true를 반환한 리스너만 현재 드래그가 끝날 때까지 드래그 이벤트를 계속 수신한다.
                DragEvent.ACTION_DRAG_STARTED -> true

                // 드래그를 드롭했을 때,
                DragEvent.ACTION_DROP -> {
                    // setOnTouchListener()에서 담았던 드래그된 아이템의 인덱스
                    val fromPosition = event.clipData.getItemAt(0).text.toString().toInt()
                    val toPosition = position   // 드롭된 위치의 position

                    // MusicActivity에 드래그 & 드롭을 알리는 콜백 함수
                    // List View의 배열을 업데이트해서 드래그 & 드롭 결과를 반영한다.
                    dropCallback.onDrop(fromPosition, toPosition)
                    true
                }
                else -> false
            }
            true
        }


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

    class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

//        private val shadow = ColorDrawable(Color.LTGRAY)
//
//
////         Defines a callback that sends the drag shadow dimensions and touch point back to the
////         system.
//        override fun onProvideShadowMetrics(size: Point, touch: Point) {
//            // Sets the width of the shadow to half the width of the original View
//            val width: Int = view.width / 2
//
//            // Sets the height of the shadow to half the height of the original View
//            val height: Int = view.height / 2
//
//            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
//            // Canvas that the system will provide. As a result, the drag shadow will fill the
//            // Canvas.
//            shadow.setBounds(0, 0, width, height)
//
//            // Sets the size parameter's width and height values. These get back to the system
//            // through the size parameter.
//            size.set(width, height)
//
//            // Sets the touch point's position to be in the middle of the drag shadow
//            touch.set(width / 2, height / 2)
//        }
//
//        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
//        // from the dimensions passed in onProvideShadowMetrics().
//        override fun onDrawShadow(canvas: Canvas) {
//            // Draws the ColorDrawable in the Canvas passed in from the system.
//            shadow.draw(canvas)
//        }
    }
}


//원본코드
//    fun moveItem(fromPosition: Int, toPosition: Int): Boolean {
//        val text: MyMenus = MyMenuArrayList[fromPosition]
//        MyMenuArrayList.removeAt(fromPosition)
//        MyMenuArrayList.add(toPosition, text)
//        notifyDataSetChanged()
//        return true
//    }



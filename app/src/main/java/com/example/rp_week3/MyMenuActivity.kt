package com.example.rp_week3

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuBinding
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken


data class MyMenus(
    val img: Int,
    val name: String,
    val price: String,
    val size: String,
    val cup: String,
    var checked: Boolean = false
)

interface OnItemDrop {
    fun onDrop(fromIdx: Int, toIdx: Int)
}

class MyMenuActivity : AppCompatActivity(), OnItemDrop {


    var MyMenuArrayList = ArrayList<MyMenus>()

    private lateinit var customAdapter: CustomAdapter

    private lateinit var binding: MyMenuBinding

    companion object { //SharedPreferences에서는 데이터를 다룰때 키를 사용하므로 그때 사용할 키를 정의
        public const val KEY_PREFS = "shared_preferences"
        public const val KEY_DATA = "monster_data"
    }

    override fun onPause() {
        super.onPause()
        customAdapter.notifyDataSetChanged()
    }

    override fun onDrop(fromIdx: Int, toIdx: Int) {
        if (fromIdx > toIdx) {
            MyMenuArrayList.add(toIdx, MyMenuArrayList[fromIdx])
            MyMenuArrayList.removeAt(fromIdx + 1)

            customAdapter.notifyDataSetChanged()
        } else {
            MyMenuArrayList.add(toIdx + 1, MyMenuArrayList[fromIdx])
            MyMenuArrayList.removeAt(fromIdx)
        }
        customAdapter.notifyDataSetChanged()

    }

//    fun a() {
//        var edit = getSharedPreferences(KEY_PREFS, MODE_PRIVATE).edit()
//        if (getSharedPreferences(KEY_PREFS, MODE_PRIVATE).getString(KEY_DATA, "no")
//                .equals("no")
//        ) {
//            var gson = Gson()
//            var json = gson.toJson(MyMenuArrayList)
//            edit.putString(KEY_DATA, "[]")
//            edit.apply()
//        }
//    }

    private fun loadPref() { //데이터를 불러오는 함수
        val sharedPreferences = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)
        if (sharedPreferences.contains(KEY_DATA)) {
            val gson = Gson()
            val json = sharedPreferences.getString(KEY_DATA, "")
            try {  //타입토큰을 적용하여 데이터를 복원
                val typeToken = object : TypeToken<ArrayList<MyMenus>>() {}.type
                customAdapter.dataSet = gson.fromJson(json, typeToken)

            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
            Log.d("debug", "Data loaded")
        }
    }

    private fun savePref() {
        val sharedPreferences = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(customAdapter.dataSet)
        editor.putString(KEY_DATA, json)
        editor.apply()
        Log.d("debug", "Data saved")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MyMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.myMenuLv.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        customAdapter = CustomAdapter(this, MyMenuArrayList, this)

        binding.myMenuLv.adapter = customAdapter

        binding.plus.setOnClickListener {
            intent = Intent(this, AddsActivity::class.java)
            startActivity(intent)
        }

        binding.bag.setOnClickListener {
            loadPref()
            for (i in 0 until customAdapter.dataSet.size) {
                MyMenuArrayList.add(0, customAdapter.dataSet[i])
            }
            customAdapter.notifyDataSetChanged()
        }

        binding.back.setOnClickListener {
            finish()
        }


            binding.myMenuLv.setOnItemLongClickListener { parent, v, position, id ->
                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                val item = ClipData.Item(v.tag as? CharSequence)

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                val dragData = ClipData(
                    v.tag as? CharSequence,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item)

                // Instantiates the drag shadow builder.
                val myShadow = CustomAdapter.MyDragShadowBuilder(v)

                // Starts the drag
                v.startDrag(
                    dragData,   // the data to be dragged
                    myShadow,   // the drag shadow builder
                    null,       // no need to use local data
                    0           // flags (not currently used, set to 0)
                )

            }





//        binding.myMenuLv.onItemClickListener =
//            OnItemClickListener { parent, view, position: Int, id -> // 상세정보 화면으로 이동하기(인텐트 날리기)
//                intent = Intent(this, ClickActivity::class.java)
//
//                intent.putExtra("name", MyMenuArrayList[position].name)
//                intent.putExtra("img", MyMenuArrayList[position].img)
//                intent.putExtra("price", MyMenuArrayList[position].price)
//                intent.putExtra("size", MyMenuArrayList[position].size)
//                intent.putExtra("cup",MyMenuArrayList[position].cup)
//
//                startActivity(intent)
//            }

        binding.allCb.setOnClickListener { //전체 삭제
            customAdapter.setAllChecked(binding.allCb.isChecked)
            customAdapter.notifyDataSetChanged()
        }

        binding.trashCan.setOnClickListener {  //데이터 삭제
//            val checkedItems: SparseBooleanArray = binding.myMenuLv.checkedItemPositions
            for (i in customAdapter.count - 1 downTo 0) {
                Log.d("삭제", i.toString())
                Log.d("선택여부", customAdapter.isChecked(i).toString())
                if (customAdapter.isChecked(i)) {
                    MyMenuArrayList.removeAt(i)
                    var edit = getSharedPreferences(KEY_PREFS, MODE_PRIVATE).edit()
                    edit.putString(KEY_DATA, Gson().toJson(ArrayList<MyMenus>()))
                    edit.apply()
                    Log.e(
                        "전체자료리스트 값 ",
                        getSharedPreferences(KEY_PREFS, MODE_PRIVATE).getString(KEY_DATA, "널").toString()
                    )
                }
            }
            // 모든 선택 상태 초기화.
            binding.myMenuLv.clearChoices()
            customAdapter.notifyDataSetChanged()
        }

//        binding.lvSwipe.setOnRefreshListener {  //스와이프
//            customAdapter.notifyDataSetChanged()
//
//            binding.lvSwipe.isRefreshing=false
//        }

//        binding.myMenuLv.touchLi =
//        SetOnTouchListener { v, motionEvent ->
//            if(motionEvent?.action == MotionEvent.ACTION_DOWN){
//                val dragShadowBuilder = CustomAdapter.MyDragShadowBuilder(v)
//
//                val dragData = customAdapter.touch()
//                v?.startDrag(dragData, CustomAdapter.MyDragShadowBuilder,v,0)
//
//            }
//}





//        binding.allCb.setOnClickListener { //전체 선택
//            var count =0
//            val newState: Boolean = MyMenuArrayList[count].checked
//            count = binding.myMenuLv.adapter.count
//            for (i in 0 until count) {
//                binding.myMenuLv.setItemChecked(i, true)
//            }
//            customAdapter.notifyDataSetChanged();
//        }

//            }
//        binding.trashCan.setOnClickListener {
//            val checkedItems: SparseBooleanArray = binding.myMenuLv.checkedItemPositions
//            val count: Int = customAdapter.count
//            for (i in count - 1 downTo 0) {
//                if (checkedItems[i]) {
//                    MyMenuArrayList.remove()
//                }
//            }
//            // 모든 선택 상태 초기화.
//            binding.myMenuLv.clearChoices()
//            customAdapter.notifyDataSetChanged()
//        }
//
//        binding.allCb.setOnClickListener {
//            var size = 0
//            val isChecked: Boolean = binding.allCb.isChecked
//            if (isChecked) {
//                size = MyMenuArrayList.count()
//                for (i in 0..size) binding.myMenuLv.setItemChecked(i, true)
//            } else if (!isChecked) {
//                size = MyMenuArrayList.count()
//                for (i in 0..size) binding.myMenuLv.setItemChecked(i, false)
//            }
//        }


//
//        binding.allCb.setOnClickListener {
//            var count = 0
//            count = MyMenuArrayList.size
//            for (i in 0 until count) {
//                binding.myMenuLv.setItemChecked(i, true)
//            }
//        }

//        binding.allCb.setOnClickListener { //전체 선택
//            var count =0
//            count = binding.myMenuLv.adapter.count
//            for (i in 0 until count) {
//                binding.myMenuLv.setItemChecked(i, MyMenuArrayList[i].checked)
//                Log.d("i","i")
//            }
//            customAdapter.notifyDataSetChanged();
//        }


        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu16,
                "블랙다이몬 카페수아",
                "7,000원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu17,
                "에스프레소 달고나크림라떼",
                "5,500원",
                "Regular",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu18,
                "아이스 흑임자 크림라떼",
                "6,300원",
                "Large",
                " 일회용컵",
                false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu2,
                "스웨디쉬 베리즈",
                "5,000원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu3,
                "스파클링 스웨디쉬 레몬티",
                "6,300원",
                "Regular",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu4,
                "(S)잉글리쉬 라떼",
                "5,800원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu5,
                "아이스 그린티 라떼",
                "5,800원",
                "Large",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu6,
                "차이라떼",
                "5,800원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu7,
                "화이트 포레스트IB",
                "6,800원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu8,
                "블랙 포레스트IB",
                "6,800원",
                "Regular",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu9,
                "베리베리IB",
                "6,800원",
                "Small",
                " 일회용컵",
                false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu10,
                "그린티IB",
                "6,500원",
                "Regular",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu11,
                "퓨어 바닐라IB",
                "6,000원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu12,
                "핫 바닐라",
                "5,500원",
                "Small",
                " 일회용컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu13,
                "더블 초코렛",
                "5,500원",
                "Regular",
                " 머그컵",
                false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu15,
                "딜라이트 피치그린티",
                "6,800원",
                "Small",
                " 일회용컵",
                false
            )
        )

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }


    override fun onStop() {
        super.onStop()
        savePref()
        customAdapter.notifyDataSetChanged()
    }
}
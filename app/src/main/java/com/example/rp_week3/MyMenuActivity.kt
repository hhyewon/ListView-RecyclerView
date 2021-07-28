package com.example.rp_week3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rp_week3.databinding.MyMenuBinding
import com.example.rp_week3.databinding.MyMenuItemBinding


data class MyMenus(
    val img: Int,
    val name: String,
    val price: String,
    val size: String,
    var checked: Boolean = false
)

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
                "Small 일회용컵", false
            )
        )


        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu16,
                "블랙다이몬 카페수아(R)",
                "7,000원",
                "Regular 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu17,
                "(R)에스프레소 달고나크림라떼",
                "5,500원",
                "Regular 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu18,
                "(R)아이스 흑임자 크림라떼",
                "6,300원",
                "Regular 일회용컵", false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu2,
                "스웨디쉬 베리즈",
                "5,000원",
                "Regular 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu3,
                "(R)스파클링 스웨디쉬 레몬티",
                "6,300원",
                "Regular 머그컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu4,
                "(S)잉글리쉬 라떼",
                "5,800원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu5,
                "(S)아이스 그린티 라떼",
                "5,800원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu6,
                "(S)차이라떼",
                "5,800원",
                "Small 머그컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu7,
                "(S)화이트 포레스트IB",
                "6,800원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu8,
                "(S)블랙 포레스트IB",
                "6,800원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu9,
                "(S)베리베리IB",
                "6,800원",
                "Small 일회용컵", false
            )
        )

        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu10,
                "(S)그린티IB",
                "6,500원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu11,
                "(S)퓨어 바닐라IB",
                "6,000원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu12,
                "(S)핫 바닐라",
                "5,500원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu13,
                "(S)더블 초코렛",
                "5,500원",
                "Small 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu14,
                "자몽 쥬스",
                "4,800원",
                "Regular 일회용컵", false
            )
        )
        MyMenuArrayList.add(
            MyMenus(
                R.drawable.bag_menu15,
                "딜라이트 피치그린티(R)",
                "6,800원",
                "Regular 일회용컵", false
            )
        )



        customAdapter = CustomAdapter(this, MyMenuArrayList)

        binding.myMenuLv.adapter = customAdapter


//        binding.myMenuLv.onItemClickListener =
//            OnItemClickListener { parent, view, position, id -> // 상세정보 화면으로 이동하기(인텐트 날리기)
//                intent = Intent(this, ClickActivity::class.java)
//
//                intent.putExtra("name", MyMenuArrayList[position].name)
//                intent.putExtra("img", MyMenuArrayList[position].img)
//                intent.putExtra("price", MyMenuArrayList[position].price)
//                intent.putExtra("size", MyMenuArrayList[position].size)
//
//                startActivity(intent)
//            }


//        binding.myMenuLv.onItemClickListener =
//            OnItemClickListener { parent, view, position: Int, id -> // 상세정보 화면으로 이동하기(인텐트 날리기)
//                intent = Intent(this, ClickActivity::class.java)
//
//                intent.putExtra("name", MyMenuArrayList[position].name)
//                intent.putExtra("img", MyMenuArrayList[position].img)
//                intent.putExtra("price", MyMenuArrayList[position].price)
//                intent.putExtra("size", MyMenuArrayList[position].size)
//
//                startActivity(intent)
//            }


//        binding.myMenuLv.setOnItemClickListener(OnItemClickListener { parent, view, position, id -> // 리스트 뷰를 누를 때 EditActivity 로 넘어가야한다.
//            // 이때 클릭된 값에 해당하는 title 과, contents 를 그대로 넘겨 줘야 한다.
//            val check_position: Int = binding.myMenuLv.getCheckedItemPosition() //리스트 뷰의 포지션을 가져옴.
//            val vo: CustomAdapter = parent.adapter.getItem(position) as CustomAdapter //리스트 뷰의 포지션 가져옴.
//            val t_title: String = vo.getTitle()
//            val c_contents: String = vo.getContents()
//            val intent = Intent(this@MainActivity, EditActivity::class.java)
//            intent.putExtra("title", t_title)
//            intent.putExtra("contents", c_contents)
//            intent.putExtra("position", position)
//            startActivity(intent)
//            finish()
//        })

//        binding.myMenuLv.setOnItemClickListener(OnItemClickListener { adapterView, view, position, l ->
//            val data = adapterView.getItemAtPosition(position) as String
//            tvSelect.setText(data)
//        })


//       binding.myMenuLv.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this,MyMenuArrayList[position].name, Toast.LENGTH_SHORT).show()
//            intent = Intent(this, ClickActivity::class.java)
//
//            intent.putExtra("name", MyMenuArrayList[position].name)
//            intent.putExtra("img", MyMenuArrayList[position].img)
//            intent.putExtra("size", MyMenuArrayList[position].size)
//            intent.putExtra("price", MyMenuArrayList[position].price)
//
//            startActivity(intent)
//
//        }

//        binding.myMenuLv.setOnItemClickListener { parent, view, position, id ->
//            intent = Intent(this, ClickActivity::class.java)
//
//            intent.putExtra("name", MyMenuArrayList[position].name)
//            intent.putExtra("img", MyMenuArrayList[position].img)
//            intent.putExtra("price", MyMenuArrayList[position].price)
//            intent.putExtra("size", MyMenuArrayList[position].size)
//            startActivity(intent)
//
//        }



//        binding.myMenuLv.setOnClickListener {
//            intent = Intent(this, ClickActivity::class.java)
//            startActivity(intent)
//        }

//
//
//        binding.myMenuLv.setOnItemClickListener { parent, view, position, id ->
//
//
//            intent = Intent(this, ClickActivity::class.java)
//
//            intent.putExtra("name", MyMenuArrayList[position].name)
//            intent.putExtra("img", MyMenuArrayList[position].img)
//            intent.putExtra("price", MyMenuArrayList[position].price)
//            intent.putExtra("size", MyMenuArrayList[position].size)
//
//            startActivity(intent)
//
//        }









        binding.allCb.setOnClickListener { //전체 선택
            var count =0
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
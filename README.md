# 📌Coffee Bean Layout + ListView, RecyclerView Clone Project 

## 🚨 _Issue_ 🚨
> ListView를 사용하면서 발생한 문제와 해결 방안       
### 1. CheckBox
  - **문제 🤦‍ |** 체크박스의 상태가 스크롤을 사용하면 (화면 밖으로 없어지면) 상태가 초기화되는 문제 발생
  - **원인 💁‍ |** 리스트뷰는 리스트를 불러올 때마다 새로 데이터를 불러오기 때문에?. ❓ 
  - **해결 🙆‍ |** 다음과 같이 코드를 작성하여 해결 !
```
**CustomAdapter.kr**

override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
...

fun isChecked(position: Int): Boolean {
            return MyMenuArrayList[position].checked
        }

        binding.myMenuItemCb.setOnClickListener {
            val newState: Boolean = !MyMenuArrayList[position].checked //check->uncheck, uncheck -> check로 바꿔 저장
            MyMenuArrayList[position].checked = newState
        }   
        
binding.myMenuItemCb.isChecked = isChecked(position) // 그 값을 체크박스에 저장하여 체크된 것들이 그대로 남아있도록 함
}
```

### 2. ListView Item 클릭
  - **문제 🤦‍ |** istView를 클릭 시 상세 액티비티로 넘어가야 하는데, 클릭이 되지 않는 문제 발생 // 체크박스는 정상 동작
  - **원인 💁‍ |** 각 Item이 여러 View들을 조합한 Layout일 때, ListView를 클릭하면 List Item이 클릭되는게 아니라 표면의 View가 클릭되는 걸로 인식한다.
  - **해결 🙆‍ |** **[1]** Adapter에 바로 코드를 작성하여 해결 !  
```
**CustomAdapter.kr**

override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
...

        binding.itemCl.setOnClickListener {
            var intent  = Intent(convertView?.context, ClickActivity::class.java)
            intent.putExtra("name", MyMenuArrayList[position].name)
            intent.putExtra("img", MyMenuArrayList[position].img)
            intent.putExtra("size", MyMenuArrayList[position].size)
            intent.putExtra("price", MyMenuArrayList[position].price)
            convertView?.context?.startActivity(intent)
        }
}
```
  - **해결 🙆‍ |** **[2]** 루트 레이아웃에 다음과 같은 코드를 추가하여 해결 ! 
```
**my_menu_item.xml**

android:descendantFocusability="blocksDescendants"   //특정 뷰로 포커스되는 현상을 막아준다.
```



## 그 외 구현한 기능들
> ListView     
#### 1. 전체 선택 버튼을 누르면 List Item의 체크박스가 전부 선택 되도록 했다.
#### 2. 리스트뷰의 체크박스를 체크한 후 휴지통 아이콘을 누르면 데이터가 삭제 및 갱신되도록 하였다.




# 📌 Coffee Bean Layout + ListView, RecyclerView Clone Project 
 [[동영상]](https://user-images.githubusercontent.com/73240332/127762628-b80f9d71-2b75-4d4b-a9f0-2c716c06a38b.mp4)
#
## 📌 App Layout Preview 👀
![week3_real1](https://user-images.githubusercontent.com/73240332/127763291-26d97aef-9401-49b2-8a57-02237167e1a6.PNG)
![week3_real2](https://user-images.githubusercontent.com/73240332/127763301-fb4a4f72-6535-458e-9721-c372fae2b2c5.PNG)

#
## 📌 구현한 기능들
> ### "일부"기능 Preview 👀
#### ✨ 체크박스 상태유지, 전체선택, 옵션선택
![기능1](https://user-images.githubusercontent.com/73240332/127763771-e1d9d28a-43e9-4b31-9651-b1a6b7087a1e.PNG)

#### ✨ 스와이프로 데이터 추가, 롱클릭으로 드래그 앤 드롭 (포지션 변경)
![기능2](https://user-images.githubusercontent.com/73240332/127763773-dcee5aee-335b-49f6-a2f3-7de2da1ecdc7.PNG)

#
> ### 👩‍💻 _Skill_
> - 체크박스 상태유지
> - 전체선택 및 해제
> - 원하는 데이터 추가 및 체크된 데이터 삭제
> - 스와이프로 데이터 불러오기
> - 롱클릭으로 드래그 앤 드롭
> - 드래그 앤 드롭으로 포지션 변경
> - 리스트에서 클릭, 롱클릭 모두 작동하도록 하기
> - 모든 Activity 생명주기에도 변경된 데이터 상태가 유지되도록 하기
>> ### _Detail Skill_
>> - SharedPreferences를 사용하여 데이터 저장 
>>   - SharedPreferences에서는 데이터를 다룰때 키를 사용하므로 그때 사용할 키를 정의하여 사용
>> - Gson을 활용하여 Json을 list로 바꾸기
>> ```
>> val typeToken = object : TypeToken<ArrayList<MyMenus>>() {}.type
>> var mymenu: ArrayList<MyMenus> = gson.fromJson(sharedPreferences.getString(MyMenuActivity.KEY_DATA, "없음"), typeToken)  // gson -> json
>> ```
>> 밥 먹고 와서 추가해야징
>> - 체크박스 커스텀
>> - 카드뷰
#
### App scenario
```
1. 메인화면에서 나만의 메뉴 들어가기 (액티비티 전환)

2. 리스트뷰를 스크롤하여 화면 밖으로 나가더라도 체크박스 상태 유지

3. 체크박스 전체선택 및 해제

4. [+] 버튼으로 메뉴 선택 리스트뷰로 이동 (액티비티 전환)

5. 이동된 리스트뷰에서 클릭으로 옵션 선택 액티비티로 이동 (액티비티 전환)

6. 버튼을 클릭하여 옵션을 지정하면 텍스트 바로 반영

7. [이 옵션으로 저장하기] 버튼을 눌러 옵션이 지정한 배열을 저장하고 나만의 메뉴 액티비티로 다시 이동 (액티비티 전환)

8. 저장된 배열 스와이프로 리스트뷰에 불러오기

9. 롱클릭으로 드래그 앤 드롭 (포지션 변경)

10. 액티비티를 나갔다 들어와도 리스트 및 변경된 포지션 유지

11. 클릭으로 리스트 상세보기 (액티비티 전환)

12. 휴지통 아이콘 클릭으로 체크된 리스트뷰 목록 삭제 및 인덱스 순서 갱신하기 //뒤에서 부터 삭제

+ 13. 다시 들어오면 삭제된 데이터는 리스트에서 사라짐
```

## 📌 🚨 _Issue_ 🚨
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

### 3. 액티비티 나갔다 들어와도 리스트 상태 유지하기
  - **문제 🤦‍ |** 액티비티를 나갔다 들어오면 데이터는 그대로이나 목록에 추가된 리스트들이 안보이는 문제 발생
  - **원인 💁‍ |** 해당 액티비티를 실행할 때마다 데이터를 load 및 add 해주는 코드의 부재
  - **해결 🙆‍ |** 다음과 같이 코드를 작성하여 해결 !
```
**CustomAdapter.kr**

    override fun onPause() {
        super.onPause()
        customAdapter.notifyDataSetChanged()  // 화면을 나갈 때마다 데이터 저장하기
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        loadPref()  // 저장된 배열 불러오기
        for (i in 0 until customAdapter.dataSet.size) { // 배열에 저장되어있는 크기만큼 불러오기
            MyMenuArrayList.add(0, customAdapter.dataSet[i])
        }
        customAdapter.notifyDataSetChanged() // 데이터 상태
    }
```








## 참고자료
-
-
-
-
-
-
-


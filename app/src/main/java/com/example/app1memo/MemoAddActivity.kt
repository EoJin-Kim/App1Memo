package com.example.app1memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.example.app1memo.databinding.ActivityMemoAddBinding
import java.text.SimpleDateFormat
import java.util.*

class MemoAddActivity : AppCompatActivity() {
    lateinit var binding : ActivityMemoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoAddBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // toolbar 설정
        setSupportActionBar(binding.memoAddToolbar)
        title = "메모 추가"

        // 이전 버튼 설정
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // 스레드를 가동한다
        Thread{
            SystemClock.sleep(500)
            runOnUiThread{
                // 포커스를 준다.
                binding.addMemoSubject.requestFocus()
                // 키보드를 보여준다.
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.addMemoSubject,InputMethodManager.SHOW_FORCED)
            }
        }.start()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
            }
            R.id.add_menu_write -> {
                // 사용자가 입력한 내용을 가지고 온다.
                val memo_subject = binding.addMemoSubject.text
                val memo_text = binding.addMemoText.text

                 val sql = """
                     insert into MemoTable (memo_subject, memo_text, memo_date)
                     values(?,?,?)
                 """.trimIndent()


                // 데이터베이스 오픈
                val helper = DBHelper(this)

                // 현재시간을 구한다.
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // ? 에 세팅될 값
                val arg1 = arrayOf(memo_subject,memo_text,now)

                // 테이블에 저장한다.
                helper.writableDatabase.execSQL(sql,arg1)
                helper.writableDatabase.close()

                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
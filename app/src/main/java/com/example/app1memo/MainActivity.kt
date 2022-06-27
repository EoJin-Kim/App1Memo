package com.example.app1memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.app1memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 딜레이 시간을 주어 splash 화면을 좀더 보여지게 한다
        SystemClock.sleep(1000)

        // Splash 화면 이후로 보여질 화면 설정
        setTheme(R.style.Theme_App1Memo)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}
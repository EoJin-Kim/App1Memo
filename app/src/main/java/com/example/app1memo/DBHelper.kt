package com.example.app1memo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {
    // 생성자 ( 데이터 베이스 이름을 정해준다)
    constructor(context:Context) : super(context,"Memo.db",null,1)

    // 데이터베이스 파일이 생성될 때
    // 테이블 생성
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = """
            create table MemoTable
            (memo_idx integer primary key autoincrement,
            memo_subject text not null,
            memo_text text not null,
            memo_date date not null)
        """.trimIndent()
        p0?.execSQL(sql)
    }

    // 데이터베이스 버전이 업데이트 될때
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}
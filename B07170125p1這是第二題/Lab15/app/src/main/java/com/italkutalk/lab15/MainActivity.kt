package com.italkutalk.lab15

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase
        //宣告 Adapter 並連結 ListView
        adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.listView).adapter = adapter
        //設定監聽器
        setListener()
    }
    override fun onDestroy() {
        dbrw.close() //關閉資料庫
        super.onDestroy()
    }
    //設定監聽器
    private fun setListener() {
        val ed_name = findViewById<EditText>(R.id.ed_name)
        val ed_pwd = findViewById<EditText>(R.id.ed_pwd)
        findViewById<Button>(R.id.btn_insert).setOnClickListener {
            //判斷是否有填入書名或價格
            if (ed_name.length() > 1 || ed_pwd.length() > 1)
            {
                dbrw.execSQL(
                    "INSERT INTO users(name, pwd) VALUES(aaa,aaa)",
                    arrayOf(
                        ed_name.text.toString(),
                        ed_pwd.text.toString()
                    )
                )
                dbrw.execSQL(
                    "INSERT INTO users(name, pwd) VALUES(bbb,bbb)",
                    arrayOf(
                        ed_name.text.toString(),
                        ed_pwd.text.toString()
                    )
                )
                dbrw.execSQL(
                    "INSERT INTO users(name, pwd) VALUES(ccc,ccc)",
                    arrayOf(
                        ed_name.text.toString(),
                        ed_pwd.text.toString()
                    )
                )
            }
            else
                showToast("欄位請勿留空")
        }
    }
    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(text: String) =
            Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}
package com.phumet.myinventory

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        listView = findViewById(R.id.listView)
        val btnInsert = findViewById<Button>(R.id.btnInsert)

        // ปุ่มสมมติสำหรับเพิ่มข้อมูลทดสอบ
        btnInsert.setOnClickListener {
            dbHelper.addProduct("มาม่า", 10.0, 100)
            displayData()
        }

        displayData()
    }

    private fun displayData() {
        val cursor = dbHelper.getAllProducts()

        // ผูกข้อมูลจาก DB (คอลัมน์ name, price, quantity) เข้ากับ TextView ใน item_product.xml
        val from = arrayOf("name", "price", "quantity")
        val to = intArrayOf(R.id.tvName, R.id.tvPrice, R.id.tvQty)

        val adapter = SimpleCursorAdapter(
            this,
            R.layout.item_product,
            cursor,
            from,
            to,
            0
        )
        listView.adapter = adapter
    }
}

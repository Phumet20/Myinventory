package com.phumet.myinventory

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Inventory.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE products (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, " +
                "price REAL, " +
                "quantity INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS products")
        onCreate(db)
    }

    // เพิ่มข้อมูล (Create)
    fun addProduct(name: String, price: Double, qty: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("price", price)
            put("quantity", qty)
        }
        return db.insert("products", null, values)
    }

    // ดึงข้อมูลทั้งหมด (Read)
    fun getAllProducts(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM products", null)
    }

    // แก้ไขข้อมูล (Update)
    fun updateProduct(id: Int, name: String, price: Double, qty: Int): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("price", price)
            put("quantity", qty)
        }
        return db.update("products", values, "id=?", arrayOf(id.toString()))
    }

    // ลบข้อมูล (Delete)
    fun deleteProduct(id: Int): Int {
        val db = this.writableDatabase
        return db.delete("products", "id=?", arrayOf(id.toString()))
    }
}
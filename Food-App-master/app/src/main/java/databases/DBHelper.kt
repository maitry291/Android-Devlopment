package databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import models.OrderModel

const val name = "DATABASE2.db"
val factory = null

class DBHelper(context: Context?) : SQLiteOpenHelper(context, name, factory, 28) {

    val name = "DATABASE.db"
    val table_name = "orders"
    val col1 = "id"
    val col2 = "image"
    val col3 = "name"
    val col4 = "price"
    val col5 = "quantity"
    val col6 = "description"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE " + table_name +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +  //0
                    "image INTEGER," +                          //1
                    "name TEXT," +                              //2
                    "price TEXT," +                             //3
                    "quantity TEXT," +                          //4
                    "description TEXT)"                         //5
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $table_name")
        onCreate(p0)
    }

    fun insertOrder(
        image: Int,
        name: String?,
        price: String?,
        quantity: String?,
        description: String?,
    ): Boolean {
        val database: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()

        values.put(col2, image)
        values.put(col3, name)
        values.put(col4, price)
        values.put(col5, quantity)
        values.put(col6, description)

        val id: Long = database.insert(table_name, null, values)

        return id > 0
    }

    fun getOrder(): ArrayList<OrderModel> {
        val list = ArrayList<OrderModel>()
        val database: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = database.rawQuery("SELECT * FROM $table_name", null)

        // if (cursor.moveToFirst())
            while (cursor.moveToNext()) {
                val model: OrderModel = OrderModel(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3))
                list.add(model)
            }

        cursor.close()
        database.close()
        return list
    }

    fun getOrderByID(idx: Int?): Cursor {
        val database: SQLiteDatabase = this.writableDatabase
        val cursor =  database.rawQuery("select * from $table_name", null)

        return cursor
    }

    fun updateOrder(
        image: Int,
        name: String?,
        price: String?,
        quantity: String?
    ): Boolean {
        val database: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()

        values.put(col2, image)
        values.put(col3, name)
        values.put(col4, price)
        values.put(col5, quantity)

        database.update(table_name,values,"",null)

        return true
    }


}
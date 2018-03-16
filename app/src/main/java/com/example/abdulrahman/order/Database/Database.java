package com.example.abdulrahman.order.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.abdulrahman.order.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="FoodDB.db";
    private static final int DB_VER=1;


    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }
    public List<Order> getCarts()
    {
        SQLiteDatabase database=getReadableDatabase();
        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();
        String []sqlSelect={"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable="OrderDetail";
        queryBuilder.setTables(sqlTable);

        Cursor  c =queryBuilder.query(database,sqlSelect,null,null,null,null,null);

            final List<Order>result=new ArrayList<>();
            if (c.moveToFirst())
            {
                do {
                    result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                            c.getString(c.getColumnIndex("ProductName")),
                            c.getString(c.getColumnIndex("Quantity")),
                            c.getString(c.getColumnIndex("Price")),
                            c.getString(c.getColumnIndex("Discount"))
                    ));
                }while (c.moveToNext());


            }
          return  result;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase database=getReadableDatabase();
        String query=String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        database.execSQL(query);
    }
    public void cleanCart()
    {
        SQLiteDatabase database=getReadableDatabase();
        String query=String.format("DELETE FROM OrderDetail");
        database.execSQL(query);
    }

    //Favorites
    public  void addToFavorites(String foodId)
    {
        SQLiteDatabase  database =getReadableDatabase();
        String  query=String.format("INSERT INTO Favorites(FoodId) VALUES ('%s');",foodId);
        database.execSQL(query);
    }
    public  void removeFormFavorites(String foodId)
    {
        SQLiteDatabase database=getReadableDatabase();
        String  query=String.format("DELETE FROM Favorites WHERE FoodId ='%s';",foodId);
        database.execSQL(query);
    }
    public  boolean isFavorite(String foodId)
    {
        SQLiteDatabase  database =getReadableDatabase();
        String  query=String.format("SELECT * FROM  Favorites WHERE  FoodId='%s';",foodId);
        Cursor cursor =database.rawQuery(query,null);
        if (cursor.getCount()<=0)
        {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

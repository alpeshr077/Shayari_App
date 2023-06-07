package com.alpesh.shayari_app;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExternalDatabase extends SQLiteOpenHelper {

    Context context;
    static final String DB_NAME = "Data.db";
    String DB_PATH = "";


    public ExternalDatabase(@Nullable Context context ) {
        super(context, "Shayari", null, 1);
        context = context;
        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        if (!isDatabaseExist()) {
            copyDatabase();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private Boolean isDatabaseExist() {
        return new File(DB_PATH + DB_NAME).exists();
    }

    private void copyDatabase() {

        try {
            InputStream inputStream = context.getAssets().open(DB_NAME);

            FileOutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);

            byte[] buffer = new byte[8 * 1024];

            int readed;
            while ((readed = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readed);
            }

            outputStream.flush();

            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<ShayriModel> getShayari() {
        List<ShayriModel> modelList = new ArrayList<>();
        SQLiteDatabase dbhelper = getReadableDatabase();
        String que = "SELECT * FROM myShayari";
        Cursor cursor = dbhelper.rawQuery(que,null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            int id = cursor.getInt(0);
            String shayari = cursor.getString(1);
            String cat = cursor.getString(2);
            ShayriModel model = new ShayriModel(id,shayari,cat);
            modelList.add(model);
            cursor.moveToNext();
        }
        return modelList;
    }

}

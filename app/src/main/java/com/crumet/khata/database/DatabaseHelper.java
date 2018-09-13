package com.crumet.khata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crumet.khata.database.model.Record;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "records_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Record.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Record.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertRecord(String amount, String type, String category, String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Record.COLUMN_AMOUNT, amount);
        values.put(Record.COLUMN_TYPE, type);
        values.put(Record.COLUMN_CATEGORY, category);
        values.put(Record.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Record.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Record.TABLE_NAME + " ORDER BY " +
                Record.COLUMN_TIMESTAMP + " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(cursor.getInt(cursor.getColumnIndex(Record.COLUMN_ID)));
                record.setNote(cursor.getString(cursor.getColumnIndex(Record.COLUMN_NOTE)));
                record.setTimestamp(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(Record.COLUMN_TIMESTAMP))));
                record.setAmount(cursor.getInt(cursor.getColumnIndex(Record.COLUMN_AMOUNT)));
                record.setCategory(cursor.getString(cursor.getColumnIndex(Record.COLUMN_CATEGORY)));
                record.setType(cursor.getString(cursor.getColumnIndex(Record.COLUMN_TYPE)));
                records.add(record);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return records;
    }

    public List<Record> getRecordsByType(String type) {
        List<Record> records = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Record.TABLE_NAME + " WHERE " + Record.COLUMN_TYPE + " = '" + type + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(cursor.getInt(cursor.getColumnIndex(Record.COLUMN_ID)));
                record.setNote(cursor.getString(cursor.getColumnIndex(Record.COLUMN_NOTE)));
                record.setTimestamp(Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(Record.COLUMN_TIMESTAMP))));
                record.setAmount(cursor.getInt(cursor.getColumnIndex(Record.COLUMN_AMOUNT)));
                record.setCategory(cursor.getString(cursor.getColumnIndex(Record.COLUMN_CATEGORY)));
                record.setType(cursor.getString(cursor.getColumnIndex(Record.COLUMN_TYPE)));
                records.add(record);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return records;
    }

    public int getSumRecordsByType(String type) {
        String selectQuery = "SELECT  SUM(amount) FROM " + Record.TABLE_NAME + " WHERE " + Record.COLUMN_TYPE + " = '" + type + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int sum = 0;
        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }
        return sum;
    }

    public Record getRecord(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Record.TABLE_NAME,
                new String[]{Record.COLUMN_ID, Record.COLUMN_TIMESTAMP,
                        Record.COLUMN_AMOUNT, Record.COLUMN_TYPE, Record.COLUMN_CATEGORY, Record.COLUMN_NOTE},
                Record.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Record record = new Record(
                cursor.getInt(cursor.getColumnIndex(Record.COLUMN_ID)),
                Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(Record.COLUMN_TIMESTAMP))),
                cursor.getInt(cursor.getColumnIndex(Record.COLUMN_AMOUNT)),
                cursor.getString(cursor.getColumnIndex(Record.COLUMN_TYPE)),
                cursor.getString(cursor.getColumnIndex(Record.COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(Record.COLUMN_NOTE))
        );
        cursor.close();
        return record;
    }

    public int updateRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Record.COLUMN_NOTE, record.getNote());

        // updating row
        return db.update(Record.TABLE_NAME, values, Record.COLUMN_ID + " = ?",
                new String[]{String.valueOf(record.getId())});
    }

    public void deleteRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Record.TABLE_NAME, Record.COLUMN_ID + " = ?",
                new String[]{String.valueOf(record.getId())});
        db.close();
    }
}

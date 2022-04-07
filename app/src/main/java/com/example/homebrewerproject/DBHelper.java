package com.example.homebrewerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {

    public static final String RECIPE_TABLE = "RECIPE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_ADDITIONAL_INGREDIENT = "ADDITIONAL_INGREDIENT";
    public static final String COLUMN_LBS_HONEY = "LBS_HONEY";
    public static final String COLUMN_STARTING_GRAVITY = "STARTING_GRAVITY";
    public static final String COLUMN_GALLONS = "GALLONS";
    public static final String COLUMN_NOTES = "NOTES";

    public DBHelper(@Nullable Context context) {
        super(context, "recipe.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + RECIPE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " +
                COLUMN_ADDITIONAL_INGREDIENT + " TEXT, " + COLUMN_LBS_HONEY + " NUMERIC, " + COLUMN_STARTING_GRAVITY + " NUMERIC, " +
                COLUMN_GALLONS+" INTEGER, " + COLUMN_NOTES +" TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(RecipeModel recipeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, recipeModel.getTitle());
        cv.put(COLUMN_ADDITIONAL_INGREDIENT, recipeModel.getAdditional_ingredient());
        cv.put(COLUMN_LBS_HONEY, recipeModel.getLbsHoney());
        cv.put(COLUMN_STARTING_GRAVITY, recipeModel.getStartingGravity());
        cv.put(COLUMN_GALLONS, recipeModel.getGallons());
        cv.put(COLUMN_NOTES, recipeModel.getNotes());

        long insert = db.insert(RECIPE_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else return true;
    }

    public boolean deleteOne(RecipeModel recipeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + RECIPE_TABLE+ " WHERE " + COLUMN_ID + " = " +
               recipeModel.getId();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        } else return false;

    }

    public List<RecipeModel> selectAll() {
        List<RecipeModel> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + RECIPE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){

            do{
                int recipeID = cursor.getInt(0);
                String recipeTitle = cursor.getString(1);
                String recipeAdditionalIngredient = cursor.getString(2);
                Double recipeLbsHoney = cursor.getDouble(3);
                Double recipeStartingGravity = cursor.getDouble(4);
                int recipeGallons = cursor.getInt(5);
                String recipeNotes = cursor.getString(6);

                RecipeModel newRecipe = new RecipeModel(recipeID, recipeTitle, recipeAdditionalIngredient, recipeLbsHoney, recipeStartingGravity, recipeGallons, recipeNotes);
                returnList.add(newRecipe);

            } while (cursor.moveToNext());

        }else{


        }

        cursor.close();
        db.close();
        return returnList;
    }
}

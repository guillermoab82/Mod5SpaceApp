package memoscorp.unam.mx.spaceappm5.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import memoscorp.unam.mx.spaceappm5.model.FavoritesModel;

/**
 * Created by GuillermoAB on 16/08/2016.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    //GET DB TO MANIPULATE
    public AppDataSource(Context context) {
        MySqlLiteHelper helper = new MySqlLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    //Insert Favorites
    public void saveFav(FavoritesModel favoritesModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_IMAGEURI,favoritesModel.cImage_URI);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_FULLNAME,favoritesModel.cFullName);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_EARTHDATE,favoritesModel.cEarth_Date);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_ROVERNAME,favoritesModel.cRover_Name);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_TOTALPHOTO,favoritesModel.nTotal_Photo);
        db.insert(MySqlLiteHelper.TABLE_FAV_NAME,null,contentValues);
    }
    //Delete Favorites
    public void deleteFav(FavoritesModel favoritesModel){
        if(favoritesModel!=null){
            db.delete(MySqlLiteHelper.TABLE_FAV_NAME,MySqlLiteHelper.COLUMN_FAV_ID+"=?",new String[]{String.valueOf(favoritesModel.id)});
        }
    }
    //Update Favorites
    public void updateFav(FavoritesModel favoritesModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_ID,favoritesModel.id);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_IMAGEURI,favoritesModel.cImage_URI);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_FULLNAME,favoritesModel.cFullName);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_EARTHDATE,favoritesModel.cEarth_Date);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_ROVERNAME,favoritesModel.cRover_Name);
        contentValues.put(MySqlLiteHelper.COLUMN_FAV_TOTALPHOTO,favoritesModel.nTotal_Photo);
        db.update(MySqlLiteHelper.TABLE_FAV_NAME,contentValues,MySqlLiteHelper.COLUMN_FAV_ID+"=?",new String[]{String.valueOf(favoritesModel.id)});
    }
    //Reading all items in DB
    public List<FavoritesModel> getAllFAV(){
        List<FavoritesModel> modelFAVList = new ArrayList<>();
        Cursor cursor=db.query(MySqlLiteHelper.TABLE_FAV_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAV_ID));
            String FAVImageURI = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAV_IMAGEURI));
            String FAVFullName = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAV_FULLNAME));
            String FAVEarthDate = cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAV_EARTHDATE));
            String FAVRoverName=cursor.getString(cursor.getColumnIndexOrThrow(MySqlLiteHelper.COLUMN_FAV_ROVERNAME));
            int FAVTotalPhoto=cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.COLUMN_FAV_TOTALPHOTO));
            FavoritesModel favoritesModel = new FavoritesModel(id,FAVImageURI,FAVFullName,FAVEarthDate,FAVRoverName,FAVTotalPhoto);
            modelFAVList.add(favoritesModel);
        }
        return modelFAVList;
    }
}


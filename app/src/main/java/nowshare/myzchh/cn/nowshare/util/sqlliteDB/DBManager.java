package nowshare.myzchh.cn.nowshare.util.sqlliteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import nowshare.myzchh.cn.nowshare.util.localUser;

/**
 * Created by chao on 2015/5/19.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //��ΪgetWritableDatabase�ڲ�������mContext.openOrCreateDatabase(mName, 0, mFactory);
        //����Ҫȷ��context�ѳ�ʼ��,���ǿ��԰�ʵ����DBManager�Ĳ������Activity��onCreate��
        db = helper.getWritableDatabase();
    }

    public void setLocalUser(String UserName,String UUID){
        db.beginTransaction();  //��ʼ����
        db.execSQL("update local_user set local_name='"+UserName+"',local_uuid='"+UUID+"' where id=0;");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void getLocalUser(Context context){
        Cursor c = db.rawQuery("SELECT * FROM local_user where id=0;", null);
        String local_name = "";
        String local_uuid="";
        try {
            while (c.moveToNext()) {
                local_name = c.getString(c.getColumnIndex("local_name"));
                local_uuid = c.getString(c.getColumnIndex("local_uuid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
        }
        localUser.setUSERNAME(local_name);
        localUser.setUUID(local_uuid);
    }

    public void closeDB() {
        db.close();
    }
}

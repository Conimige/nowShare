package nowshare.myzchh.cn.nowshare.util.sqlliteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chao on 2015/5/19.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        //CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //���ݿ��һ�α�����ʱonCreate�ᱻ����
    @Override
    public void onCreate(SQLiteDatabase db) {
        //����ӵ�е��û����ݿ�
        db.execSQL("CREATE TABLE IF NOT EXISTS local_user" +
                "(id INT,local_name VARCHAR, local_uuid VARCHAR)");
        db.execSQL("INSERT INTO local_user VALUES(0,?, ?)", new Object[]{"", ""});
    }

    //���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS localuser" +
//                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, password VARCHAR,phone VARCHAR)");

    }
}
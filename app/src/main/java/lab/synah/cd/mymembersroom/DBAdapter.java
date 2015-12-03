package lab.synah.cd.mymembersroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class DBAdapter 
{
	private static final String DATABASE_TABLE = "members";
	public static final String KEY_ROW_ID = "_id";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	
	
	SQLiteDatabase mDb;
	Context mCtx;
	DBHelper mDbHelper;
	
	public DBAdapter(Context context)
	{
		this.mCtx = context;
	}

	public DBAdapter open() throws SQLException
	{
		mDbHelper = new DBHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		mDbHelper.close();
	}
	
	public long register(String user,String pw)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_USERNAME, user);
		initialValues.put(KEY_PASSWORD, pw);
		
		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}
	
	public boolean Login(String username, String password) throws SQLException 
    {
    	Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null) {           
            if(mCursor.getCount() > 0)
            {
            	return true;
            }
        }
     return false;
    }
}
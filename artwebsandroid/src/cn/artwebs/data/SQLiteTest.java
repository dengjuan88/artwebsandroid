package cn.artwebs.data;

import cn.artwebs.object.BinList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class SQLiteTest extends AndroidTestCase {
	private final static String tag="SQLiteTest";
	class Db extends SQLite{

		public Db(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)"); 
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(" ALTER TABLE person ADD phone VARCHAR(12) NULL "); //往表中增加
		}
		
	}
	
	public void testQuery()
	{
		Db db=new Db(this.getContext());
		db.execute("insert into person(name, age) values(?,?)",new Object[]{"张三",30});
		db.execute("insert into person(name, age) values(?,?)",new Object[]{"李四",30});
		BinList list=db.query("select personid,age,name from person", null);
		Log.d(tag,list.getItem().toString());
		
	}
}

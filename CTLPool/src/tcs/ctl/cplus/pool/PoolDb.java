package tcs.ctl.cplus.pool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PoolDb extends SQLiteOpenHelper{
	
	 // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "PoolDB";
 
    public PoolDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {    	
        // SQL statement to create users table
        String CREATE_USERS_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "mobile TEXT, "+
                "password TEXT, "+
                "empid TEXT, "+
                "gender TEXT )";
 
        // create users table        
        db.execSQL(CREATE_USERS_TABLE);
        
        String CREATE_POOLS_TABLE = "CREATE TABLE pools ( " +
        		"poolid INTEGER PRIMARY KEY AUTOINCREMENT, "+
        		"pfrom TEXT, "+
        		"pto TEXT,"+
        		"pvia TEXT,"+
        		"pdate TEXT,"+
        		"ptime TEXT,"+
        		"pvehicle TEXT,"+
        		"pseats TEXT,"+
        		"createId TEXT,"+
        		"createDate TEXT,"+
        		"createTime TEXT,"+
        		"availSeats TEXT,"+
        		"bookedSeats TEXT)";
        
        db.execSQL(CREATE_POOLS_TABLE);
        
        String CREATE_SUBSCRIBE_TABLE = "CREATE TABLE subscribe ( " +
        		"poolid TEXT,"+
        		"userid TEXT,"+
        		"quantity TEXT,"+
        		"date TEXT,"+
        		"time TEXT )";
        
        db.execSQL(CREATE_SUBSCRIBE_TABLE);        
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");
 
        // create fresh books table
        this.onCreate(db);
    }
    
    
    // Users table name
    private static final String TABLE_USERS = "users";
 
    // Users Table Columns names
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_MOBILE = "mobile";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMPID = "empid";
    private static final String USER_GENDER = "gender";
 
    private static final String[] USER_COLUMNS = {USER_ID,USER_NAME,USER_MOBILE,USER_PASSWORD,USER_EMPID,USER_GENDER};
    
    //pools table name
    private static final String TABLE_POOLS = "pools";
    
    //pools table columns names
    private static final String POOL_ID="poolid";	
    private static final String POOL_FROM="pfrom";
    private static final String POOL_TO="pto";
    private static final String POOL_VIA="pvia";
    private static final String POOL_DATE="pdate";
    private static final String POOL_TIME="ptime";    
    private static final String POOL_VEHICLE="pvehicle";
    private static final String POOL_SEATS="pseats";
    private static final String POOL_CRID="createId";
    private static final String POOL_CRDATE="createDate";
    private static final String POOL_CRTIME="createTime";
    private static final String POOL_AVAILSEATS="availSeats";
    private static final String POOL_BOOKEDSEATS="bookedSeats";
    
    
    private static final String[] POOL_COLUMNS = {POOL_ID,POOL_FROM,POOL_TO,POOL_VIA,POOL_DATE,POOL_TIME,POOL_VEHICLE,POOL_SEATS,POOL_CRID,POOL_CRDATE,POOL_CRTIME,POOL_AVAILSEATS,POOL_BOOKEDSEATS};
    
    
    //subscribe table name
    private static final String TABLE_SUBSCRIBE = "subscribe";
    
    //subscribe table columns names
    private static final String SUBS_PID="poolid";	
    private static final String SUBS_CID="userid";
    private static final String SUBS_QTY="quantity";
    private static final String SUBS_DATE="date";
    private static final String SUBS_TIME="time";
    
    private static final String[] SUBS_COLUMNS = {SUBS_PID,SUBS_CID,SUBS_QTY,SUBS_DATE,SUBS_TIME};
    
    
	protected boolean adduser(UserBean ubObj)
	{		
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        
        // 2. create ContentValues to add key "column"/value
        if(validateUser(ubObj)==null)
        {
        ContentValues values = new ContentValues();         
        values.put(USER_NAME, ubObj.getName()); // get name
        values.put(USER_MOBILE, ubObj.getMobile()); // get mobile
        values.put(USER_PASSWORD, ubObj.getPassword()); // get password
        values.put(USER_EMPID, ubObj.getEmpid()); // get empid
        values.put(USER_GENDER, ubObj.getGender()); // get gender
 
        // 3. insert
       double userRet= db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        
        if(userRet==-1)
        {
        	return false;
        }
        else{        
        return true;
        }
        }
        else
        {
        	return false;
        }
        
	}
	
	protected UserBean validateUser(UserBean ubObj)
	{
		UserBean ubObj2;
		
		// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_USERS, // a. table
                USER_COLUMNS, // b. column names
                " mobile = ?", // c. selections 
                new String[] { String.valueOf(ubObj.getMobile()) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            
         // 4. build user object
            ubObj2 = new UserBean();        
            ubObj2.setId(Integer.parseInt(cursor.getString(0)));
            ubObj2.setName(cursor.getString(1));
            ubObj2.setMobile(cursor.getString(2));
            ubObj2.setPassword(cursor.getString(3));
            ubObj2.setEmpid(cursor.getString(4));
            ubObj2.setGender(cursor.getString(5));
     
//            cursor.close();
//            db.close();
         // 5. return user
            if(ubObj.getPassword().equals(ubObj2.getPassword()))
            {
            	return ubObj2;
            }
            else
            {
            	return null;
            }
//            Log.d("validateUser("+ubObj2.getMobile()+")", ubObj2.toString());
        }
        else
        {
//        	cursor.close();
 //       	db.close();
        	return null;
        }
	}
	

	protected boolean addPool(UserBean ubObj, PoolBean poolObj)
	{
		  // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
       // 2. create ContentValues to add key "column"/value
        
        ContentValues values = new ContentValues();   
        
        values.put(POOL_FROM, poolObj.getFrom()); // get from
        values.put(POOL_TO, poolObj.getTo()); // get to
        values.put(POOL_VIA, poolObj.getVia()); // get via
        values.put(POOL_DATE, poolObj.getDate()); // get date
        values.put(POOL_TIME, poolObj.getTime()); // get time
        values.put(POOL_VEHICLE, poolObj.getVehicle()); // get vehicle
        values.put(POOL_SEATS, poolObj.getSeats()); // get seats
        values.put(POOL_CRID, ubObj.getId()); // get create id
        values.put(POOL_CRDATE, new SimpleDateFormat("MM-dd-yyyy").format(new Date()).toString()); // get create date
        values.put(POOL_CRTIME, new SimpleDateFormat("HH:mm").format(new Date()).toString()); // get create time
        values.put(POOL_AVAILSEATS, poolObj.getAvailableSeats());
        values.put(POOL_BOOKEDSEATS, poolObj.getBookedSeats());
      
        
        // 3. insert
       double userRet= db.insert(TABLE_POOLS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        // 4. close
 //       db.close(); 
        if(userRet==-1)
        {
        	return false;
        }
        else{        
        return true;
        }
        
        	
	}
	
	protected ArrayList<PoolBean> fetchPools(PoolBean poolObj)
	{
		ArrayList<PoolBean> pbObjAl=new ArrayList<PoolBean>();
		
		 // 1. build the query
        String query = "SELECT * FROM " + TABLE_POOLS +" where "+POOL_DATE+" like '%"+poolObj.getDate()+"%' and "+POOL_FROM+" like '%"+poolObj.getFrom()+"%' and "+POOL_TO+" like '%"+poolObj.getTo()+"%' and "+POOL_VIA+" like '%"+poolObj.getVia()+"%' and "+POOL_AVAILSEATS+" >= '"+poolObj.getSeats()+"';";
//      String query = "SELECT * FROM " + TABLE_POOLS +" where "+POOL_DATE+" like '%"+poolObj.getDate()+"%';";
     // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
       
     // 3. go over each row, build book and add it to list
        PoolBean tpmPbObj = null;
       
        if (cursor.moveToFirst()) {
            do {
            	tpmPbObj = new PoolBean();            	
            	tpmPbObj.setPoolid(Integer.parseInt(cursor.getString(0)));
            	tpmPbObj.setFrom(cursor.getString(1));
            	tpmPbObj.setTo(cursor.getString(2));
            	tpmPbObj.setVia(cursor.getString(3));
            	tpmPbObj.setDate(cursor.getString(4));
            	tpmPbObj.setTime(cursor.getString(5));
            	tpmPbObj.setVehicle(cursor.getString(6));
            	tpmPbObj.setSeats(cursor.getString(7));
            	tpmPbObj.setCreateId(cursor.getString(8));
            	tpmPbObj.setCreateDate(cursor.getString(9));
            	tpmPbObj.setCreateTime(cursor.getString(10));
            	tpmPbObj.setAvailableSeats(cursor.getString(11));
            	tpmPbObj.setBookedSeats(cursor.getString(12));
 
                // Add book to books
                pbObjAl.add(tpmPbObj);
                
            } while (cursor.moveToNext());
        }
        
 //       cursor.close();
 //       db.close();
        
        return pbObjAl;
        
	}
	
	protected PoolBean fetchUpdatedPool(String poolid)
	{
	       String query = "SELECT * FROM " + TABLE_POOLS +" where "+POOL_ID+" = "+Integer.parseInt(poolid)+";";
//	      String query = "SELECT * FROM " + TABLE_POOLS +" where "+POOL_DATE+" like '%"+poolObj.getDate()+"%';";
	     // 2. get reference to writable DB
	        SQLiteDatabase db = this.getWritableDatabase();
//	        Cursor cursor = db.rawQuery(query, null);
	        
	        // 2. build query
	        Cursor cursor = 
	                db.query(TABLE_POOLS, // a. table
	                POOL_COLUMNS, // b. column names
	                " poolid = ?", // c. selections 
	                new String[] { String.valueOf(poolid) }, // d. selections args
	                null, // e. group by
	                null, // f. having
	                null, // g. order by
	                null); // h. limit
	       
	     // 3. go over each row, build book and add it to list
	        PoolBean tpmPbObj = null;
	       if(cursor==null)
	       {
	    	   return null;
	       }
	       else if (cursor.moveToFirst()) {
	            
	            	tpmPbObj = new PoolBean();            	
	            	tpmPbObj.setPoolid(Integer.parseInt(cursor.getString(0)));
	            	tpmPbObj.setFrom(cursor.getString(1));
	            	tpmPbObj.setTo(cursor.getString(2));
	            	tpmPbObj.setVia(cursor.getString(3));
	            	tpmPbObj.setDate(cursor.getString(4));
	            	tpmPbObj.setTime(cursor.getString(5));
	            	tpmPbObj.setVehicle(cursor.getString(6));
	            	tpmPbObj.setSeats(cursor.getString(7));
	            	tpmPbObj.setCreateId(cursor.getString(8));
	            	tpmPbObj.setCreateDate(cursor.getString(9));
	            	tpmPbObj.setCreateTime(cursor.getString(10));
	            	tpmPbObj.setAvailableSeats(cursor.getString(11));
	            	tpmPbObj.setBookedSeats(cursor.getString(12));
	            	
	            	
	        }
	        
	 //       cursor.close();
	 //       db.close();
	        
	        return tpmPbObj;
	}
	protected SubscribeBean fetchSubscription(UserBean UBOBJ, PoolBean PBOBJ)
	{
		
		SubscribeBean sbObj=new SubscribeBean();
		
		// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();       
 
        String query="select * from "+TABLE_SUBSCRIBE+" where "+SUBS_PID+"='"+PBOBJ.getPoolid()+"' and "+SUBS_CID+"='"+UBOBJ.getId()+"';";
        // 2. build query
        Cursor cursor = db.rawQuery(query,null); 
 
        // 3. if we got results get the first one
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
        {         
            
         // 4. build user object
            
            sbObj.setPid(cursor.getString(0));
            sbObj.setCid(cursor.getString(1));
            sbObj.setQty(cursor.getString(2));
            sbObj.setDate(cursor.getString(3));
            sbObj.setTime(cursor.getString(4));
                
//            cursor.close();
//            db.close();
         // 5. return user
        }
        else
        {
//        	cursor.close();
 //       	db.close();
        	return null;
        }
		
		return sbObj;
	}
	
	protected boolean addSubscription(String uid, PoolBean pbObj, String qty)
	{
		  // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();       
        
        
        // 2. create ContentValues to add key "column"/value
        
        ContentValues values = new ContentValues();         
        values.put(SUBS_PID, pbObj.getPoolid());
        values.put(SUBS_CID, uid); 
        values.put(SUBS_QTY, qty);
        values.put(SUBS_DATE, new SimpleDateFormat("MM-dd-yyyy").format(new Date()).toString()); 
        values.put(SUBS_TIME, new SimpleDateFormat("HH:mm").format(new Date()).toString());
 
        // 3. insert
       double subsRet= db.insert(TABLE_SUBSCRIBE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
       ContentValues values1 = new ContentValues();
       values1.put(POOL_AVAILSEATS, pbObj.getAvailableSeats());
       values1.put(POOL_BOOKEDSEATS, pbObj.getBookedSeats());
       

       // 3. updating row
       int i = db.update(TABLE_POOLS, //table
               values1, // column/value
               POOL_ID+" = "+pbObj.getPoolid(),
               null); //selection args

        
        if(subsRet==-1 | i==-1)
        {
        	return false;
        }
        else{        
        return true;
        }
       
	}
	
	protected boolean updateSubscription(String uid, PoolBean pbObj, String qty)
	{
		  // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("quantity", qty); // get title       
 
        // 3. updating row
        int i = db.update(TABLE_SUBSCRIBE, //table
                values, // column/value
                SUBS_PID+" = '"+pbObj.getPoolid()+"' and "+SUBS_CID+" = '"+uid+"'",
                null); //selection args
 
              
        
        ContentValues values1 = new ContentValues();
        values1.put(POOL_AVAILSEATS, pbObj.getAvailableSeats());
        values1.put(POOL_BOOKEDSEATS, pbObj.getBookedSeats());
        
        
        // 3. updating row
        int i1 = db.update(TABLE_POOLS, //table
                values1, // column/value
                POOL_ID+" = "+pbObj.getPoolid(),
                null); //selection args
 
        if(i==-1 | i1==-1)
        {
        	return false;
        }
        else{        
        return true;
        }
	}
}

package app.sahid.crud_system;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper{


    private static final String Database="Crud_System";
    private static final String Table="Crud_System";
    private static final String Id="id";
    private static final String Name="name";
    private static final String Email="email";
    private static final String Phone="phone";
    private static final String Gender="gender";
    private static final String Password="password";
    private static final String Username="username";
    private static final String Drop="Drop table if exists "+Table;
    private static final String Select="Select * from "+Table;
    private static final String Count="Select count(*) from "+Table;
    private static final String Create="Create table "+Table+"( "
            +Id+" integer not null  primary key autoincrement,"
            +Name+" varchar(333) not null,"
            +Email+" varchar(333) not null,"
            +Username+" varchar(333) not null unique,"
            +Phone+" varchar(333) not null,"
            +Gender+" varchar(333) not null,"
            +Password+" varchar(333) not null);"
            ;
    Context context;
    private static final int version=2;

    public DatabaseHelper(Context context) {
        super(context, Database,null,version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try
        {
            sqLiteDatabase.execSQL(Create);
            Toast.makeText(context,"Successfully Connected",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Error is : "+e,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try
        {
            Toast.makeText(context,"On Upgrade stage",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(Drop);
            onCreate(sqLiteDatabase);
        }

        catch (Exception e)
        {
            Toast.makeText(context,"Error is : "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public Long insertdata(InsertDetails insertDetails) {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,insertDetails.getN());
        contentValues.put(Email,insertDetails.getE());
        contentValues.put(Username,insertDetails.getU());
        contentValues.put(Password,insertDetails.getPa());
        contentValues.put(Phone,insertDetails.getPh());
        contentValues.put(Gender,insertDetails.getG());
        Long rowid=sqLiteDatabase.insert(Table,null,contentValues);
        return rowid;
    }


    public StringBuilder show()
    {
        StringBuilder sb=new StringBuilder();
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(Select,null);
        if(cursor.getCount()==0)
        {
            sb.append("No Data");
        }
        else
        {
            while (cursor.moveToNext())
            {
                sb.append("Name: "+cursor.getString(1)+"\n");
                sb.append("Email: "+cursor.getString(2)+"\n");
                sb.append("Username: "+cursor.getString(3)+"\n");
                sb.append("Password: "+cursor.getString(4)+"\n");
                sb.append("Phone: "+cursor.getString(5)+"\n");
                sb.append("Gender: "+cursor.getString(6)+"\n\n\n");
            }
        }
        return sb;
    }

        public ArrayList<String> getheader()
        {
            ArrayList<String> header=new ArrayList<>();
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery(Select,null);
            if(cursor.getCount()==0)
            {
                //Toast.makeText(context,"No data",Toast.LENGTH_SHORT).show();
                header.add("NO data");
            }
            else
            {
                while (cursor.moveToNext())
                {
                    header.add(cursor.getString(3));
                }
            }
            return header;

        }
    public void delete(String username) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor c=sqLiteDatabase.rawQuery(Count,null);
        sqLiteDatabase.execSQL("Delete from "+Table+" where "+Username+" = '"+username+"';");
        Cursor d=sqLiteDatabase.rawQuery(Count,null);
        if(c.equals(d))
        {
            Toast.makeText(context,"Not deleted ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public InsertDetails update(String usernameforupdate) {
        InsertDetails ud = null;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("Select * from "+Table+" where "+Username+"= '"+usernameforupdate+"';",null);
        if(c.getCount()==0)
        {
            Toast.makeText(context,"No data found",Toast.LENGTH_SHORT).show();
            String name="";
            String email="";
            String username="";
            String password="";
            String phone="";
            String gender="";
            ud=new InsertDetails(name,email,username,password,phone,gender);
        }
        else
        {
            Toast.makeText(context," data founded",Toast.LENGTH_SHORT).show();
            while(c.moveToNext()) {
                String name = c.getString(1);
                String email = c.getString(2);
                String username = c.getString(3);
                String password = c.getString(6);
                String phone = c.getString(4);
                String gender = c.getString(5);
//
//                Toast.makeText(context,"name "+name,Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"email "+email,Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"username "+username,Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"password "+password,Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"phone "+phone,Toast.LENGTH_SHORT).show();
//                Toast.makeText(context,"gender "+gender,Toast.LENGTH_SHORT).show();
                ud = new InsertDetails(name, email, username, password, phone, gender);
            }
        }
        return ud;
    }

    public boolean original_update(InsertDetails updatelast) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,updatelast.getN());
        contentValues.put(Email,updatelast.getE());
        contentValues.put(Username,updatelast.getU());
        contentValues.put(Password,updatelast.getPa());
        contentValues.put(Phone,updatelast.getPh());
        contentValues.put(Gender,updatelast.getG());
        sqLiteDatabase.update(Table,contentValues,Username+"= ?",new String[]{updatelast.getU()});
        return true;
    }

    public List<String> getchild() {
        ArrayList<String> header=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(Select,null);
        if(cursor.getCount()==0)
        {
            //Toast.makeText(context,"No data",Toast.LENGTH_SHORT).show();
            header.add("NO data");
        }
        else
        {
            while (cursor.moveToNext())
            {
                header.add(cursor.getString(1));
                header.add(cursor.getString(2));
                header.add(cursor.getString(4));
                header.add(cursor.getString(5));
                header.add(cursor.getString(6));
            }
        }
        return header;
    }
}

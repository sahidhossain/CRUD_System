package app.sahid.crud_system;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class list_view extends AppCompatActivity {

    ListView listview_id;
    ArrayList<String> al;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        al=new ArrayList<String>();
        listview_id=findViewById(R.id.listview_id);
        db=new DatabaseHelper(this);
        StringBuilder sb=db.show();
        al.add(String.valueOf(sb));
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,al);
        listview_id.setAdapter(adp);

    }
}

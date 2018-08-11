package app.sahid.crud_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class expand extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private DatabaseHelper db;
    private List<String> headerlist;
    private HashMap<String ,List<String>> Childlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        preparedata();
        expandableListView=findViewById(R.id.expandlist_id);
    }

    private void preparedata() {
        Childlist=new HashMap<>();
        db=new DatabaseHelper(expand.this);
        headerlist=db.getheader();
        List<String> child=new ArrayList<>();
        child=db.getchild();
        for(int i=0;i<headerlist.size();i++)
        {
          Childlist.put(headerlist.get(i),child);
        }
            Toast.makeText(expand.this, String.valueOf(Childlist), Toast.LENGTH_LONG).show();
    }
}

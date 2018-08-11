package app.sahid.crud_system;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button insert,delete,update,show,list;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        show.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    void init()
    {
        insert=findViewById(R.id.btn_insert);
        delete=findViewById(R.id.btn_delete);
        update=findViewById(R.id.btn_update);
        show=findViewById(R.id.btn_show);
        list=findViewById(R.id.btn_list);
        db=new DatabaseHelper(MainActivity.this);
        db.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        Fragment fr;
        if(view.getId()==R.id.btn_insert)
        {
            fr=new Insert();
            FragmentManager frm=getFragmentManager();
           FragmentTransaction frt=frm.beginTransaction();
            frt.replace(R.id.fragment_id,fr);
            frt.commit();
        }
        if(view.getId()==R.id.btn_delete)
        {
            fr=new Delete();
            getFragmentManager().beginTransaction().replace(R.id.fragment_id,fr).commit();
        }
        if(view.getId()==R.id.btn_update)
        {
            fr=new Update();
            getFragmentManager().beginTransaction().replace(R.id.fragment_id,fr).commit();
        }
        if(view.getId()==R.id.btn_show)
        {
            StringBuilder sb=db.show();
            final AlertDialog.Builder alrt=new AlertDialog.Builder(MainActivity.this);
            alrt.setTitle("Total Database is ");
            alrt.setMessage(sb);
            alrt.setCancelable(true);
            alrt.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alrt1=alrt.create();
            alrt1.show();
        }
        if(view.getId()==R.id.btn_list)
        {
            //startActivity(new Intent(MainActivity.this,list_view.class));
            startActivity(new Intent(MainActivity.this,expand.class));
        }
    }
}

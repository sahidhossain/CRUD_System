package app.sahid.crud_system;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends Fragment {
    private EditText name,email,username,password,phone,gender;
    Context context2;
    private Button insert_btn;
    private DatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_insert,container,false);
        init(view);
        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String e=email.getText().toString();
                String u=username.getText().toString();
                String pa=password.getText().toString();
                String ph=phone.getText().toString();
                String g=gender.getText().toString();

                InsertDetails insertDetails=new InsertDetails(n,e,u,pa,ph,g);
                Long rowid=db.insertdata(insertDetails);
                if(rowid>0)
                {
                    Toast.makeText(getActivity(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context2,"Not Inserted",Toast.LENGTH_SHORT).show();
                }

                clear();
            }
        });
        return view;
    }

    private void clear() {
        name.setText("");
        email.setText("");
        username.setText("");
        password.setText("");
        phone.setText("");
        gender.setText("");
    }

    private void init(View view) {
        name=view.findViewById(R.id.in_et_name);
        email=view.findViewById(R.id.in_et_email);
        username=view.findViewById(R.id.in_et_username);
        password=view.findViewById(R.id.in_et_password);
        phone=view.findViewById(R.id.in_et_phone);
        gender=view.findViewById(R.id.in_et_gender);
        insert_btn=view.findViewById(R.id.in_btn);
        db=new DatabaseHelper(getActivity());
        db.getWritableDatabase();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context2=context;
    }


}

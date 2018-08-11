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

public class Update extends Fragment {
    private EditText up_et,up_name,up_email,up_username,up_password,up_phone,up_gender;
    private Button up_btn,up_btn_update;
    DatabaseHelper db;
    private InsertDetails ud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_update,container,false);
        init(view);
        // Inflate the layout for this fragment


        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameforupdate=up_et.getText().toString();
             ud= db.update(usernameforupdate);
             settext(ud);
            }
        });

        up_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=up_name.getText().toString();
                String e=up_email.getText().toString();
                String u=up_username.getText().toString();
                String pa=up_password.getText().toString();
                String ph=up_phone.getText().toString();
                String g=up_gender.getText().toString();

                InsertDetails updatelast= new InsertDetails(n,e,u,pa,ph,g);
                boolean update=db.original_update(updatelast);
                if(update==true)
                {
                    Toast.makeText(getActivity(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Not Updated Successfully",Toast.LENGTH_SHORT).show();
                }
                clearall();
            }
        });
        return view;
    }

    private void clearall() {
        up_name.setText("");
        up_email.setText("");
        up_username.setText("");
        up_password.setText("");
        up_phone.setText("");
        up_gender.setText("");
    }

    private void settext(InsertDetails ud) {
        up_name.setText(ud.getN());
        up_email.setText(ud.getE());
        up_username.setText(ud.getU());
        up_password.setText(ud.getPa());
        up_phone.setText(ud.getPh());
        up_gender.setText(ud.getG());
    }

    private void init(View view) {
        up_et=view.findViewById(R.id.up_et_id);
        up_name=view.findViewById(R.id.up_et_name);
        up_email=view.findViewById(R.id.up_et_email);
        up_username=view.findViewById(R.id.up_et_username);
        up_password=view.findViewById(R.id.up_et_password);
        up_phone=view.findViewById(R.id.up_et_phone);
        up_gender=view.findViewById(R.id.up_et_gender);
        up_btn=view.findViewById(R.id.up_btn_id);
        up_btn_update=view.findViewById(R.id.up_btn);
        db=new DatabaseHelper(getActivity());
    }

}


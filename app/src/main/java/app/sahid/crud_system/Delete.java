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


public class Delete extends Fragment {
    private Context context;
    private EditText et;
    private Button btn;
    DatabaseHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_delete,container,false);
      et=view.findViewById(R.id.del_et);
      btn=view.findViewById(R.id.del_btn);
      db=new DatabaseHelper(getActivity());

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String username=et.getText().toString();
              db.delete(username);
          }
      });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       this.context=context;
    }

}

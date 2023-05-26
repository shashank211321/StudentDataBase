package com.technicbvoc.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {
    ArrayList<String> ar;
    ArrayAdapter<String> adapter;
    Spinner sp;
    EditText eddob,edname,edmob,edloc;
    String stdob,stdepartment,stname,stmob,stloc;
    DBhelper dbhprobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        eddob=findViewById(R.id.editTextTextPersonName4);
        edname=findViewById(R.id.editTextTextPersonName);
        edmob=findViewById(R.id.editTextTextPersonName2);
        edloc=findViewById(R.id.editTextTextPersonName3);
        dbhprobj=new DBhelper(this);

        ar=new ArrayList<String>();
        sp=findViewById(R.id.spinner);

        eddob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp=new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        eddob.setText(dayOfMonth+"/"+(1+month)+"/"+year);
                    }
                }, 2023, 0, 1);
                dp.show();
            }
        });
        ar.add("Software & App Development");
        ar.add("Retail & Supply Management");
        ar.add("Digital and Film Making");
        adapter=new ArrayAdapter<>(SignUp.this, android.R.layout.simple_spinner_dropdown_item,ar);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stdepartment=ar.get(position);
                Toast.makeText(SignUp.this, ""+stdepartment, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void signup(View view) {
        stname=edname.getText().toString();
        stloc=edloc.getText().toString();
        stmob=edmob.getText().toString();
        stdob=eddob.getText().toString();
        Toast.makeText(this, stname+"-"+stloc+"-"+stmob+"-"+stdob, Toast.LENGTH_SHORT).show();
        dbhprobj.savedata(stname,stmob,stloc,stdob,stdepartment);
    }


}
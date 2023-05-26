package com.technicbvoc.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    ArrayList<String> departmentarray;
    ArrayAdapter<String> adapter;
    Spinner sp;
    String stdepartment;
    DBhelper dbhrobj;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        sp=findViewById(R.id.spinner2);
        t=findViewById(R.id.textView2);
        dbhrobj=new DBhelper(this);
        departmentarray=new ArrayList<>();
        departmentarray.add("Software & App Development");
        departmentarray.add("Retail & Supply Management");
        departmentarray.add("Digital and Film Making");
        adapter=new ArrayAdapter<>(Admin.this, android.R.layout.simple_spinner_dropdown_item,departmentarray);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stdepartment=departmentarray.get(position);
                Toast.makeText(Admin.this, ""+stdepartment, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void selbol(View view) {

        String res=dbhrobj.getda(stdepartment);
        t.setText(res);
    }
}
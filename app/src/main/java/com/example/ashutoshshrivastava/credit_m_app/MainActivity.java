package com.example.ashutoshshrivastava.credit_m_app;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit,show;
    DatabaseHelper databaseHelper;

    EditText etname,etemail,etcompany,etnumber;
    String name,city,company ;
    int credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etcompany = (EditText) findViewById(R.id.etcompany);
        etnumber = (EditText) findViewById(R.id.etphone);
        submit = (Button) findViewById(R.id.submit);


        submit = (Button) findViewById(R.id.submit);
        show= (Button) findViewById(R.id.show);

        databaseHelper = new DatabaseHelper(this,DatabaseHelper.DATABASE,null,1);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etname.getText().toString();
                city = etemail.getText().toString();
                company=etcompany.getText().toString();
                String cr1=etnumber.getText().toString();
               // credit = Integer.valueOf(etnumber.getText().toString());
                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (name.isEmpty() || city.isEmpty()|| company.isEmpty() || cr1.isEmpty()){

                    Snackbar.make(v, "Please fill all the details", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .setAction("Action", null).show();

                }else {

                    credit = Integer.valueOf(cr1);
                    databaseHelper.insertdata(name,company,city,credit);
                    etname.setText("");
                    etemail.setText("");
                    etcompany.setText("");
                    etnumber.setText("");
                    Intent intent = new Intent(MainActivity.this,Viewall.class);
                    startActivity(intent);

                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Viewall.class);
                startActivity(intent);
            }
        });
    }
}
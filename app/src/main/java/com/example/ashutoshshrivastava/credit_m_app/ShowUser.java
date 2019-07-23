package com.example.ashutoshshrivastava.credit_m_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ShowUser extends AppCompatActivity {
TextView name,city,credit,company;
EditText amount;
Button sendbutton,delete;
ImageView imageView;
Spinner sp;
    List<DataModel> dataModelArrayList;
    String senderNameStr,senderNumStr;

    DatabaseHelper databaseHelper=new DatabaseHelper(ShowUser.this,DatabaseHelper.DATABASE ,null ,1 );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        name=findViewById(R.id.textView);
        city=findViewById(R.id.textView2);
        delete=findViewById(R.id.delete);
        credit=findViewById(R.id.textView3);
        imageView=findViewById(R.id.imageView);
        company=findViewById(R.id.textView4);
        amount=findViewById(R.id.Amount);
        sendbutton=findViewById(R.id.SendCredit);
        getIncomingIntent();
//SPinner start//
        sp=findViewById(R.id.spinner);
        ArrayList<String> list=databaseHelper.getAllProvinces();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.spinertext,list);
        sp.setAdapter(adapter);
//spinner end//


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String recievrNameStr = sp.getSelectedItem().toString();
                databaseHelper.deleteTitle(senderNameStr);
                Intent intent=new Intent(ShowUser.this,Viewall.class);
                startActivity(intent);

            }
        });

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String recievrNameStr=recname.getText().toString();
                String recievrNameStr = sp.getSelectedItem().toString();
                String valueStr=amount.getText().toString();

                if(Integer.valueOf(valueStr)>Integer.valueOf(senderNumStr))
                {
                    Snackbar.make(v, "You Don't have enough Credit", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

                else {
                    int newSenderNum = Integer.valueOf(senderNumStr) - Integer.valueOf(valueStr);


                    databaseHelper.update(senderNameStr, newSenderNum);

                    dataModelArrayList = new ArrayList<>();
                    dataModelArrayList = databaseHelper.showReciever(recievrNameStr);

                    String recieverName = dataModelArrayList.get(0).name;
                    int recieverNum = dataModelArrayList.get(0).credit;

                    recieverNum = recieverNum + Integer.valueOf(valueStr);
                    String recieverNumStr = String.valueOf(recieverNum);

                    databaseHelper.update(recieverName, recieverNum);

                    Intent intent = new Intent(ShowUser.this, Viewall.class);
                    startActivity(intent);
                }

            }
        });


    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("name") && getIntent().hasExtra("city") && getIntent().hasExtra("credit") && getIntent().hasExtra("company")){

            senderNameStr = getIntent().getStringExtra("name");
            String city = getIntent().getStringExtra("city");
            senderNumStr = getIntent().getStringExtra("credit");
            String company=getIntent().getStringExtra("company");
            setImage(senderNameStr, city, senderNumStr,company);

        }
    }

    private void setImage(String name1, String city, String credit,String company){
        name.setText(name1);
        this.city.setText(city);
        this.credit.setText(credit);
        this.company.setText(company);

    }

}

package com.example.siddarthnyati.lets_split;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main1_Activity extends AppCompatActivity {
        Button btnnext;
       // EditText Bill_Amt=(EditText)findViewById(R.id.billAmount);
        EditText billAmount;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            billAmount= (EditText) findViewById(R.id.billAmount);

            final EditText merchant = findViewById(R.id.edit_merchant);
            btnnext=(Button)findViewById(R.id.btn_next);

            if  (billAmount.getText().toString().equals("") || billAmount.getText().toString() == null)

            {
                Toast toast = Toast.makeText(getApplicationContext(), "Please Enter some value", Toast.LENGTH_SHORT);// Set your own toast  message
                toast.show();

            }

            btnnext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {


                    /*Intent intent;
                    intent = new Intent(Main1_Activity.this, MainActivity.class);
                    startActivity(intent);*/


                    Intent bill_share =new Intent(Main1_Activity.this,MainActivity.class);
                    bill_share.putExtra("billAmount",billAmount.getText().toString());
                    bill_share.putExtra("merchant", merchant.getText().toString());
                    startActivity(bill_share);


                    //Intent i = new Intent(EditActivity.this, ViewActivity.class);
                    //intent.putExtra("number_of_people", number_of_people.getText());
                    // startActivity(new Intent(MainActivity.this,MyActivity.class));
                    //i.putExtra("name", edtName.getText().toString();



                }
            });
        }

    }

package com.example.siddarthnyati.lets_split;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main_Logic extends AppCompatActivity implements View.OnClickListener {


    Button btn_owe;
    Button btn_settelment;
    RecyclerView recycler_owe;
    adapter_for_owe adapter_for_owe;
    Context ctx;
    //Main_Logic  TotalPaid;
    Main_Logic act;
    ArrayList<Contact> selectedContacts;
    double share_activity,TotalPaid=0.0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owe_details);
        recycler_owe = findViewById(R.id.recycler_owe);
        btn_owe = findViewById(R.id.btn_owe);
        btn_settelment = findViewById(R.id.btn_settelment);


        Intent i = getIntent();

        selectedContacts = i.getParcelableArrayListExtra("a");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_owe.setLayoutManager(layoutManager);
        adapter_for_owe= new adapter_for_owe(selectedContacts,this,Main_Logic.this);//doubt what to pass here

        recycler_owe.setAdapter(adapter_for_owe);


        btn_settelment.setOnClickListener(this);
           /* public void onClick(View v) {

                Intent i = new Intent(Main_Logic.this, Settelment_logic.class);

                i.putParcelableArrayListExtra("a", selectedContacts);
                startActivity(i);
            }

        });
**/


    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_settelment:

                int size_array = selectedContacts.size();

               // adapter_for_owe.CheckFilled();


                 for (int j = 0; j < size_array; j++) {

                    share_activity=selectedContacts.get(j).share;

                }




                if(TotalPaid==selectedContacts.size()*share_activity)

                {
                    Intent i = new Intent(Main_Logic.this, Settelment_logic.class);
                    i.putParcelableArrayListExtra("a", selectedContacts);
                    startActivity(i);
                }



                else{  Toast toast = Toast.makeText(getApplicationContext(), "check total paid", Toast.LENGTH_SHORT);// Set your own toast  message
                    toast.show();
                }




        }
    }

}
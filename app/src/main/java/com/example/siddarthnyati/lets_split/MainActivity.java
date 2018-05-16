package com.example.siddarthnyati.lets_split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    double billAmount;
    TextView contactsDisplay;
    Button pickContacts;
    Button btn_owe;
    TextView billA;
    final int CONTACT_PICK_REQUEST = 1000;

    RecyclerView recyclerView;
    SplitAdapter splitAdapter;

    String merchant;
    double size;

    ArrayList<Contact> contacts;
    ArrayList<Contact> selectedContacts;
    Contact cn=new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitys_main);
        contactsDisplay = findViewById(R.id.txt_selected_contacts);
        billA = (TextView) findViewById(R.id.txtName);
        //billAmount=findViewById(R.id.billAmount);
        pickContacts = findViewById(R.id.btn_pick_contacts);
        recyclerView = findViewById(R.id.recycler_users);
        btn_owe = findViewById(R.id.btn_owe);
       billAmount = Double.parseDouble(getIntent().getStringExtra("billAmount"));

        merchant = getIntent().getStringExtra("merchant");
        pickContacts.setOnClickListener(this);//send onclick from here
        btn_owe.setOnClickListener(this);


        contacts = new ArrayList<Contact>();

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        splitAdapter = new SplitAdapter(contacts);
        recyclerView.setAdapter(splitAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK) {

            selectedContacts = data.getParcelableArrayListExtra("SelectedContacts");
            /*String display="";
            for(int i=0;i<selectedContacts.size();i++){
                display += (i+1)+". "+selectedContacts.get(i).toString()+"\n";
            }*/

            //contacts.clear();

            contacts.addAll(selectedContacts);

            splitAdapter.notifyDataSetChanged();
            size = contacts.size();
            addShare();

            /*contactsDisplay.setText("Selected Contacts : \n\n"+display);*/



        }

    }


    private void addShare() {

        double share = calculateShare();
        for (Contact contact : contacts) {
            contact.setShare(share);

        }

    }

    private double calculateShare() {

        if (contacts.size() > 1) {
            double billval=0;

             billval =billAmount / contacts.size();
            Log.e("billval",billval+"");


            double newbill=round(Math.abs(billval), 2);
            Log.e("after func",billval+"");

            return newbill;


        }
     return 0;
    }




    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }




    public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btn_owe:

                    if (size < 2) {
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please Pick More Contacts", Toast.LENGTH_LONG);// Set your own toast  message
                            toast.show();

                        }

                    } else {
                        Intent i = new Intent(MainActivity.this, Main_Logic.class);
                        i.putParcelableArrayListExtra("a", contacts);
                        startActivity(i);


                    }

                    break;


                case R.id.btn_pick_contacts : {
                    Intent intentContactPick = new Intent(MainActivity.this, ContactsPickerActivity.class);
                    MainActivity.this.startActivityForResult(intentContactPick, CONTACT_PICK_REQUEST);


                    break;
                }

            }
        }
    }






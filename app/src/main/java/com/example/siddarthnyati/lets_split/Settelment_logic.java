package com.example.siddarthnyati.lets_split;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Settelment_logic extends AppCompatActivity {
    Button btn_settelment;
    RecyclerView settelment_recycler;
    Settelment_adapter Settelment_adapter;
    List printBill;
    TextView txt_ShowData;
    HashMap parm;
    double edit_pay_amt;
    double result;
    // Button btn_parse;
    String json_data;
    int iterate_till_end=0;
    int checkiterateval=0;
    ArrayList<Contact> selectedContacts;
    Contact cobject = new Contact();
    //ArrayList<Contact> value = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settelment_recycler);
        settelment_recycler = findViewById(R.id.settelment_recycler);
        btn_settelment = findViewById(R.id.btn_settelment);
        //btn_parse = findViewById(R.id.btn_parse);


        Intent i = getIntent();

        selectedContacts = i.getParcelableArrayListExtra("a");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        settelment_recycler.setLayoutManager(layoutManager);
        Settelment_adapter = new Settelment_adapter(selectedContacts);
        settelment_recycler.setAdapter(Settelment_adapter);

        //txtName = (TextView) findViewById(R.id.txtName);
        //txtShare = (TextView) findViewById(R.id.txtShare);
        txt_ShowData = (TextView) findViewById(R.id.txt_ShowData);

            FindPath();

            parsedvalue();
        // btn_parse.setOnClickListener(this);
    }


    private void FindPath() {
        parm = new HashMap();
        int size_array = selectedContacts.size();


        for (int j = 0; j < size_array; j++) {

            double value = selectedContacts.get(j).PayNow;//extract from model
            edit_pay_amt = value;


            String Name = selectedContacts.get(j).name;
            parm.put(Name, edit_pay_amt);
        }


        JSONObject rootObj = new JSONObject();

        try {
            findPath(parm, rootObj,size_array, iterate_till_end++);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txt_ShowData.setText(rootObj.toString());
        //get the json in string format and then parse it
        rootObj.toString();
        json_data = rootObj.toString();

    }


    public void findPath(HashMap details, JSONObject rootObj, int size_array, int iterator) throws JSONException {

            Log.e("size of iterator",iterator+"");
            printBill = new ArrayList();
            double balance = 0;
            Double Max_Value = (Double) Collections.max(details.values());
            Double Min_Value = (Double) Collections.min(details.values());
            String Max_Key = getKeyFromValue(details, Max_Value).toString();
            String Min_Key = getKeyFromValue(details, Min_Value).toString();




            if (Max_Value != 0.0 && Min_Value != 0.0) {

                if (!rootObj.has(Max_Key)) {
                    rootObj.put(Max_Key, new JSONObject());


                }


                if (!rootObj.has(Min_Key)) {
                    rootObj.put(Min_Key, new JSONObject());
                }


                if (Max_Value != Min_Value) {
                    result = Max_Value + Min_Value;
                    if(result<0.0){balance= Max_Value;}

                }

                if (result == 0.0) {
                    details.put(Max_Key, 0.0);//max key has nothing
                    details.put(Min_Key, result);//
                    JSONObject minSettlementObject = (JSONObject) rootObj.get(Min_Key);

                    JSONObject maxSettlementObject = (JSONObject) rootObj.get(Max_Key);

                    double max_value = 0 - (Max_Value);

                    round(Math.abs(max_value), 2);

                    minSettlementObject.put(Max_Key, max_value);

                    double min_value = 0 - (Min_Value);

                    maxSettlementObject.put(Min_Key, min_value);



                }


                if (result > 0.0) {

                    //String message = (Min_Key + "needs to pay" + Max_Key + ":");

                    details.put(Max_Key, result);//min key has given everything
                    details.put(Min_Key, 0.0);
                    JSONObject maxSettlementObject = (JSONObject) rootObj.get(Max_Key);
                    JSONObject minSettlementObject = (JSONObject) rootObj.get(Min_Key);

                    double minvals = 0 - (Min_Value);
                    round(Math.abs(minvals), 2);
                    maxSettlementObject.put(Min_Key, minvals);

                    minSettlementObject.put(Max_Key, Min_Value);

                }
                if (result < 0.0) {


                    // String message = (Min_Key + " needs to pay " + Max_Key + ":");

                    details.put(Max_Key, 0.0);//max key has nothing
                    details.put(Min_Key, result);//
                    JSONObject minSettlementObject = (JSONObject) rootObj.get(Min_Key);
                    JSONObject maxSettlementObject = (JSONObject) rootObj.get(Max_Key);
                    double minvals = 0 - (Min_Value);

                    round(Math.abs(minvals), 2);

                    maxSettlementObject.put(Min_Key, balance);

                    //double max_value = 0 - (Max_Value);
                    round(Math.abs(Min_Value), 2);

                    minSettlementObject.put(Max_Key, 0-balance);


                }


               /* if (Max_Value != Min_Value) {
                    //System.out.println(Min_Key + " needs to pay " + Max_Key + ":");

                    details.put(Max_Key, 0.0);
                    details.put(Max_Key, result);


                    JSONObject maxSettlementObject = (JSONObject) rootObj.get(Max_Key);
                    double minvals = 0 - (Min_Value);
                    round(Math.abs(minvals), 2);
                    maxSettlementObject.put(Min_Key, minvals);


                    JSONObject minSettlementObject = (JSONObject) rootObj.get(Min_Key);
                    double maxval = 0 - Min_Value;

                    minSettlementObject.put(Max_Key, Min_Value);

                }**/

            }
        if (Max_Value == 0.0 && Min_Value == 0.0 && size_array==iterator) {
            return;
        }

        findPath(details, rootObj, size_array, iterate_till_end++);

            //checkiterateval=iterate_till_end;

        }



    //start setting data in textviews which are present;

                /* store_json=minSettlementObject.getString(Min_Key);
                txt_ShowData.setText(store_json);


                 store_json=maxSettlementObject.getString(Min_Key);
                txt_ShowData.setText(store_json);


                store_json=minSettlementObject.getString(Min_Key);
                txt_ShowData.setText(store_json);

                  store_json=maxSettlementObject.getString(Max_Key);
                txt_ShowData.setText(store_json); **/


    //store_json=rootObj;






    public static Object getKeyFromValue(HashMap hm, double value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


  /*  public void onClick(View v){
        switch(v.getId()) {
            case R.id.btn_parse:
                Intent Intent =new Intent(Settelment_logic.this,Parsed.class);
                Intent.putExtra("jsondata",json_data);
                startActivity(Intent);

        }



    }**/

    private void parsedvalue() {


        try {
            JSONObject jsonObject = new JSONObject(json_data);

            Iterator<String> iterator = jsonObject.keys();
            String key;
            while (iterator.hasNext()) {
                Contact contactobj = new Contact();
                key = iterator.next();
                //contactobj.setsettler(key));
                contactobj.setsettler(key);
                selectedContacts.add(contactobj);
                JSONObject jo_layer1;
                jo_layer1 = jsonObject.getJSONObject(key);
                Iterator<String> itr = jo_layer1.keys();
                while (itr.hasNext()) {
                    String settledvalue=contactobj.getsettled();
                    //if (contactobj.getsettled() != null)

                    if(settledvalue.isEmpty()) {


                        String name = itr.next();
                        Log.e("name ",name+"");

                        String k=jo_layer1.getString(name);
                        Log.e("name val",k+"");

                        int nameval=Integer.parseInt(k);


                        if(nameval<0) {
                            String setval=jo_layer1.getString(name);
                            int intval=Integer.parseInt(setval);
                            int positiveval=0-intval;


                            // String test = contactobj.getsettled() + "\n" + name + " has to take " + jo_layer1.getString(name);
                            String test = contactobj.getsettled() + "" + name + " has to take " + positiveval;
                            contactobj.setsettled(test);


                        }




                        else{
                            String setval=jo_layer1.getString(name);
                            int intval=Integer.parseInt(setval);
                            int positiveval=0-intval;


                            String test = contactobj.getsettled() + "" + name + " will give " +intval;
                            contactobj.setsettled(test);}
                    }

                    //if(!settledvalue.isEmpty())
                    else {
                        String name = itr.next();
                        String k=jo_layer1.getString(name);
                        int nameval=Integer.parseInt(k);


                        if(nameval<0) {
                            String setval=jo_layer1.getString(name);
                            int intval=Integer.parseInt(setval);
                            int positiveval=0-intval;


                            // String test = contactobj.getsettled() + "\n" + name + " has to take " + jo_layer1.getString(name);
                            String test = contactobj.getsettled() + "\n" + name + " has to take " + positiveval;
                            contactobj.setsettled(test);


                        } else{
                            String setval=jo_layer1.getString(name);
                            int intval=Integer.parseInt(setval);
                            int positiveval=0-intval;


                            String test = contactobj.getsettled() + "\n" + name + " will give " + intval;
                            contactobj.setsettled(test);}
                    }


                    }

                }
                //value.add(contactobj);
            }
            catch (JSONException e1) {
            e1.printStackTrace();}


            Settelment_adapter = new Settelment_adapter(selectedContacts);
            settelment_recycler.setAdapter(Settelment_adapter);
        }
    }
































































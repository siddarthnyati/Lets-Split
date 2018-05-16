package com.example.siddarthnyati.lets_split;
import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class adapter_for_owe  extends RecyclerView.Adapter<adapter_for_owe.ViewHolder> {
    private ArrayList<Contact> values;
    Context ctx;
    double prevValue;
    double Share;
    Main_Logic activity;


/*
    public void check() {

        EditText ed;
        ed = (EditText) findViewById(R.id.editPaid);


        if (ed.getText().toString().equals("") || ed.getText().toString() == null)

        {
            Toast toast = Toast.makeText(ctx, "Please Enter some value", Toast.LENGTH_SHORT);// Set your own toast  message
            toast.show();

            //editPaid.setText("0");
        }


    }**/


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    //adapter me activty ka object
    //activity se adapter constructor ke thru
    //adapter to activity interface

//

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EditText editPaid;
        public EditText editPayNow;
        double Paid, PayNow, TotalPaidActivity ;
        public TextView txtName;
        public TextView txtShare;

        //Item item = new Item(String title, String name, ArraylistImages[0]);
        //viewHolder.chkContact.setText(this.filteredContactsList.contactArrayList.get(position).toString());


        Contact contact = new Contact(txtName, txtShare, editPaid, editPayNow);

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.txtName);
            txtShare = (TextView) v.findViewById(R.id.txtShare);
            editPaid = (EditText) v.findViewById(R.id.editPaid);
            editPayNow = (EditText) v.findViewById(R.id.editPayNow);
            //editPaid.setText("0");
            // String Shares = String.valueOf(contact.getShare());
           // values.get(getAdapterPosition()).setPaid(0.0);


           // values.get(getLayoutPosition()).setPaid(0.0);
            double contact_share=0;
            for(int i=0;i<values.size();i++) {
                contact_share = values.get(i).share;
                double neg_contact_share=0-contact_share;
                String string_share = Double.toString(neg_contact_share);
                editPayNow.setText(string_share);

            }


            editPaid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {
                        if (editPaid.getText().toString().equals("") || editPaid.getText().toString() == null)

                        {
                            Toast toast = Toast.makeText(ctx, "Please Enter some value", Toast.LENGTH_SHORT);// Set your own toast  message
                            toast.show();

                            //editPaid.setText("0");
                        }


                        Paid = Double.parseDouble(editPaid.getText().toString());
                        Share = Double.parseDouble(txtShare.getText().toString());






                        PayNow = Paid - Share;
                        values.get(getAdapterPosition()).setPaid(Paid);
                        editPayNow.setText(PayNow + "");




                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        if (editPaid.getText().toString().equals("") || editPaid.getText().toString() == null)

                        {
                            Toast toast = Toast.makeText(ctx, "Please Enter some value", Toast.LENGTH_SHORT);// Set your own toast  message
                            toast.show();

                            //editPaid.setText("0");
                            PayNow=-Share;
                        }



                        if (Paid > Share * values.size()) {
                            Toast toast = Toast.makeText(ctx, "Enter below bill amt", Toast.LENGTH_SHORT);// Set your own toast  message
                            toast.show();


                        }

                        editPayNow.setText(PayNow + "");//enter value in model
                        values.get(getAdapterPosition()).setPaid(Paid);
                        values.get(getAdapterPosition()).setPayNow(PayNow);


                        prevValue = Double.parseDouble(s.toString());

                        TotalPaidActivity = findtotal();
                        activity.TotalPaid = TotalPaidActivity;

                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                    }




                   /* prevValue = Double.parseDouble(s.toString());
                    TotalPaid = -TotalPaid+prevValue;
                    Log.e("logic_after_change",TotalPaid+"");


					for( int i = 0;i<values.size (); i ++)
                    {
                        double Paid_now = values.get(i).paid;

                        TotalPaid = TotalPaid + Paid_now;

                    }

                    activity.TotalPaid=TotalPaid;

                    Log.e("logic_after_change",TotalPaid+"");
**/


                }
            });





        }




    }
    private double findtotal()
    { double TotalPaid=0.0;

        for (int i = 0; i < values.size(); i++) {

            double Paid_now = values.get(i).paid;

            TotalPaid = TotalPaid + Paid_now;
            //Log.e("Value total paid",TotalPaid+"");




            if(TotalPaid>values.size()*Share){

                Toast toast = Toast.makeText(ctx, "already above bill amount", Toast.LENGTH_SHORT);// Set your own toast  message
                toast.show();

            }



        }
        //TotalPaid = TotalPaid-prevValue;
        // Log.e("subtracting prevvalue",TotalPaid+"");
        // TotalPaid = TotalPaid-prevValue;
        return (TotalPaid);




    }




   /*
   double TotalPaid=0.0;

        TotalPaid= TotalPaid+paid;
        Log.e("total paid",TotalPaid+"");



        TotalPaid=TotalPaid-prevValue;
        Log.e("after subtracting",TotalPaid+"");



        return TotalPaid;
*/




/*




    private double findtotal(double paid, double TotalPaid) {

        TotalPaid = paid + TotalPaid;
        Log.e("after total paid: ", TotalPaid + "");

        TotalPaid = TotalPaid - prevValue;
        Log.e("findtotal: ", prevValue + "");

        for (int i = 0; i < values.size(); i++) {

            double Paid_now = values.get(i).paid;

            TotalPaid = TotalPaid + Paid_now;
        }


        // TotalPaid = TotalPaid-prevValue;
        return (TotalPaid);


    }
**/

//added in constructor

    public adapter_for_owe(ArrayList<Contact> myDataset, Main_Logic activity, Context context) {
        values = myDataset;
        this.activity = activity;
        this.ctx = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public adapter_for_owe.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recyclerview_owe_details, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Contact contact = values.get(position);

        holder.txtName.setText(contact.getName());
        holder.txtShare.setText(contact.getShare() + "");


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return values.size();
    }

}





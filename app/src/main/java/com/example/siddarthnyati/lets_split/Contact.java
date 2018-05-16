package com.example.siddarthnyati.lets_split;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;


public class Contact implements Parcelable {

    public String id,name,phone,label;
    double share,paid=0.0,PayNow,billAmount;
    String settled="",settler="";
    String text_Settle;

    Contact(String id, String name,String phone,String label,double PayNow,double paid,double billAmount,String settler,String settled){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.label=label;
        this.PayNow=PayNow;
        this.paid=paid;
        this.billAmount=billAmount;
        this.settler=settler;
        this.settled=settled;




    }

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        label = in.readString();
        share = in.readDouble();
        paid = in.readDouble();
        PayNow=in.readDouble();
        text_Settle=in.readString();
        billAmount=in.readDouble();
        settler=in.readString();
        settled=in.readString();



    }

    public Contact(TextView name, TextView share, TextView txtName, TextView txtShare) {
    }
public Contact(TextView settler,TextView settled){}
public Contact(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getShare() {
        return share;
    }

    public double setShare(double share) {
        this.share = share;
        return share;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid)
    {


        this.paid= paid;

       Log.e("contact_paid",paid+"");
    }

    public double getPayNow() {
        return PayNow;
    }


    public void setPayNow(double PayNow)
    {
                this.PayNow=PayNow;

        //this.PayNow=PayNow;
    }


    public double getbillAmount() {
        return billAmount;
    }

    public void setBillAmount(double share) {
        this.billAmount = billAmount;
    }



    public String getsettler() {
        return settler;
    }

  public void setsettler(String settler){this. settler=settler;}



    public String getsettled() {
        return settled;
    }

    public void setsettled(String settled) {
        this.settled = settled;
    }









    //end of model


    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public String toString()
    {
        return name+" | "+label+" : "+phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(label);
        dest.writeDouble(share);
        dest.writeDouble(paid);
        dest.writeDouble(PayNow);
        dest.writeString(text_Settle);
        dest.writeDouble(billAmount);
        dest.writeString(settler);
        dest.writeString(settled);

    }



}

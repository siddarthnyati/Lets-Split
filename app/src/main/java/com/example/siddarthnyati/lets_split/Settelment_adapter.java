package com.example.siddarthnyati.lets_split;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

public class Settelment_adapter extends RecyclerView.Adapter<Settelment_adapter.ViewHolder> {
    private List<Contact> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //public TextView txtName;

        public TextView settler;
        public TextView settled;
        public ViewHolder(View v) {
            super(v);
           // txtName= (TextView) v.findViewById(R.id.txtName);
            settler=(TextView) v.findViewById(R.id.settler);
            settled=(TextView) v.findViewById(R.id.settled);

        }
    }


    public Settelment_adapter(List<Contact> myDataset) {

        values = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public Settelment_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.settelment_details, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Contact contact = values.get(position);

      //  holder.txtName.setText(contact.getName());

        holder.settler.setText(contact.getsettler());
        holder.settled.setText(contact.getsettled());

    }


    public int getItemCount() {
        return values.size();
    }

}

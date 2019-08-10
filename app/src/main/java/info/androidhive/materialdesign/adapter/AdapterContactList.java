package info.androidhive.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.model.Contact;

/**
 * Created by Tops on 4/19/2017.
 */

public class AdapterContactList extends RecyclerView.Adapter<AdapterContactList.ViewHolder> {

    private Context mContext;
    private ArrayList<Contact> mList;
    Contact contact;
    LayoutInflater mInflater;

    public AdapterContactList(Context mContext, Contact contact) {
        this.mContext = mContext;
        this.contact = contact;

        mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(contact.getContacts().get(position).getName());
        holder.mobile.setText(contact.getContacts().get(position).getPhone().getMobile());
        holder.email.setText(contact.getContacts().get(position).getEmail());




    }

    @Override
    public int getItemCount() {
        return contact.getContacts().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, email, mobile;


        private ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            mobile = (TextView) itemView.findViewById(R.id.mobile);


        }
    }
}

package com.example.aprilcapistrano.mycontacts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aprilcapistrano.mycontacts.Methods.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private View itemView;
    private Context ctx;
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView cItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            cItemView = itemView.findViewById(R.id.textView);
        }


    }

    private final LayoutInflater mInflater;
    private List<Contact> mContact; // Cached copy of words

    ContactListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.recyclerview_item, (ctx = parent), false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (mContact != null) {
            final Contact current = mContact.get(position);
            holder.cItemView.setText(current.getName());
            final FragmentActivity fx = (FragmentActivity) ctx;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ContactDetailsFragment cdf = new ContactDetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", current.getName());
                    bundle.putString("number", current.getNumber());
                    bundle.putString("email", current.getEmail());
                    cdf.setArguments(bundle);
                    fx.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, cdf).commit();

                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.cItemView.setText("No Record");
        }
    }

    void setContact(List<Contact> words){
        mContact = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mContact != null)
            return mContact.size();
        else return 0;
    }
}
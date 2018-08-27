package com.example.aprilcapistrano.mycontacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aprilcapistrano.mycontacts.Methods.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemView;

        private ContactViewHolder(View iv) {
            super(iv);
            contactItemView = iv.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contact; // Cached copy of words
    private List<String> name;

    ContactListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (contact != null) {
            Contact current = contact.get(position);
            holder.contactItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.contactItemView.setText("No Record");
        }
    }

    void setContact(List<Contact> c){
        contact = c;
        notifyDataSetChanged();
    }

    void setName(List<String> name){
        this.name = name;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (contact != null)
            return contact.size();
        else return 0;
    }
}
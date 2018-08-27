package com.example.aprilcapistrano.mycontacts;
//package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aprilcapistrano.mycontacts.Methods.Contact;
import com.example.aprilcapistrano.mycontacts.Methods.ContactViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

     private ContactViewModel mContactViewModel;
    public static final int NEW_NAME_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        mContactViewModel.getAllNames().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                adapter.setName(strings);
            }
        });

        final Button button = (Button) findViewById(R.id.add_contact);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), NewContact.class));
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivityForResult(intent, NEW_NAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
//
        if (requestCode == NEW_NAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Contact contact = new Contact(data.getStringExtra(NewContact.EXTRA_REPLY), data.getStringExtra(NewContact.EXTRA_REPLY), data.getStringExtra(NewContact.EXTRA_REPLY));
            mContactViewModel.insert(contact);
            Toast.makeText(MainActivity.this, "New contact is successfully added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }



}

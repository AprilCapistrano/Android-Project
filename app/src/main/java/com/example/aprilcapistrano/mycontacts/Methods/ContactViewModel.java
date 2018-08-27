package com.example.aprilcapistrano.mycontacts.Methods;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;
    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<String>> mAllNames;

    public ContactViewModel(Application application){
        super(application);
        contactRepository = new ContactRepository(application);
        mAllContacts = contactRepository.getAllContacts();
        mAllNames = contactRepository.getAllNames();
    }

    LiveData<List<Contact>> getAllContacts(){return mAllContacts;}
    public LiveData<List<String>> getAllNames(){return mAllNames; }

    public void insert(Contact contact){contactRepository.insert(contact);}
}

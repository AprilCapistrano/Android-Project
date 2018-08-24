package com.example.aprilcapistrano.mycontacts.Methods;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ContactRepository {

    private ContactDAO mContactDAO;
    private LiveData<List<Contact>> mAllContacts;
    private LiveData<List<String>> mAllNames;

    ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDAO = db.contactDAO();
        mAllContacts = mContactDAO.getAllContacts();
        mAllNames = mContactDAO.getAllNames();


    }

    LiveData<List<Contact>> getAllContacts(){
        return mAllContacts;
    }

    LiveData<List<String>> getAllNames(){
        return mAllNames;
    }

    public void insert (Contact contact){
        new insertAsyncTask(mContactDAO).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO mAsyncTaskDao;

        insertAsyncTask(ContactDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


}

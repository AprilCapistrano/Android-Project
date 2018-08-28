package com.example.aprilcapistrano.mycontacts.Methods;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.util.Log;

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

    LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    LiveData<List<String>> getAllNames() {
        return mAllNames;
    }

    public void insert(Contact contact) {
        new insertAsyncTask(mContactDAO).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDAO mAsyncTaskDao;

        insertAsyncTask(ContactDAO dao) {
            mAsyncTaskDao = dao;
        }

                @SuppressLint("WrongConstant")
        @Override
        protected Void doInBackground(final Contact... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            } catch (Exception e) {
                Log.d("DB ERROR",e.toString());
            }
            return null;
        }
    }


}

package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactRoomDatabase extends RoomDatabase {
    public abstract ContactDAO contactDAO();

    private static ContactRoomDatabase INSTANCE;

    public static ContactRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if(INSTANCE == null){
                    //CREATE DATABASE
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contacts_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDAO mDao;

        PopulateDbAsync(ContactRoomDatabase db) {
            mDao = db.contactDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //for delete
            mDao.deleteAll();

            //for Adding
            Contact contact = new Contact("FirstName LastName", "0909 123 1234", "fname@gmail.com");
            mDao.insert(contact);
            return null;
        }
    }
}

package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactsRoomDatabase extends RoomDatabase {
    public abstract ContactDAO contactDAO();

    private static ContactsRoomDatabase INSTANCE;

    public static ContactsRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ContactsRoomDatabase.class){
                if(INSTANCE == null){
                    //CREATE DATABASE
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsRoomDatabase.class, "contacts_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

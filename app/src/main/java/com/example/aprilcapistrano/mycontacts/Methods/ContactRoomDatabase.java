package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

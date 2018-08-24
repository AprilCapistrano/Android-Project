package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * from contact_table ORDER BY contact ASC")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT name from contact_table ORDER BY name ASC")
    LiveData<List<String>> getAllNames();
}

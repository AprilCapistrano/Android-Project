package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void insert(Contacts contacts);

    @Query("DELETE FROM contacts_table")
    void deleteAll();

    @Query("SELECT * from contacts_table ORDER BY contacts ASC")
    List<Contacts> getAllContacts();

    @Query("SELECT name from contacts_table ORDER BY name ASC")
    LiveData<List<String>> getAllNames();
}

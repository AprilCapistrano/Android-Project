package com.example.aprilcapistrano.mycontacts.Methods;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    public Contact(@NonNull String name, @NonNull String number, @NonNull String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }


    @NonNull
    public String getName() {
        return this.name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getNumber() {
        return this.number;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
    }

    @NonNull
    public String getEmail() {
        return this.email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}

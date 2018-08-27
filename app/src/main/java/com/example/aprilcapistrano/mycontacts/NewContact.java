package com.example.aprilcapistrano.mycontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContact extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditContactName, mEditContactNum, mEditContactEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        mEditContactName = findViewById(R.id.edit_word);
        mEditContactEmail = findViewById(R.id.edit_email);
        mEditContactNum = findViewById(R.id.edit_number);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditContactName.getText()) || TextUtils.isEmpty(mEditContactEmail.getText())
                        || TextUtils.isEmpty(mEditContactNum.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mEditContactName.getText().toString();
                    String email = mEditContactEmail.getText().toString();
                    String num = mEditContactNum.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, name);
                    replyIntent.putExtra(EXTRA_REPLY, email);
                    replyIntent.putExtra(EXTRA_REPLY, num);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

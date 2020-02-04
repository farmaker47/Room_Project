package com.george.room_project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItemsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "com.george.room_project.REPLY_NAME";
    public static final String EXTRA_REPLY_QUANTITY = "com.george.room_project.REPLY_QUANTITY";
    private EditText mEditName, mEditQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button button = findViewById(R.id.button_save);
        mEditName = findViewById(R.id.edit_item);
        mEditQuantity = findViewById(R.id.edit_quantity);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditName.getText().toString();
                    int quantity = Integer.parseInt(mEditQuantity.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_NAME, word);
                    replyIntent.putExtra(EXTRA_REPLY_QUANTITY, quantity);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

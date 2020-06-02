package com.example.pickpick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Random;

public class ItemSettingActivity extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    ListView itemListView;
    Button addButton, deleteButton, chooseButton;
    Random random;
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";

    EditText nameValue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);

        items = new ArrayList<String>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice,items);

        itemListView = (ListView) findViewById(R.id.itemList);
        itemListView.setAdapter(adapter);
        itemListView.setChoiceMode(itemListView.CHOICE_MODE_SINGLE);

        nameValue = (EditText) findViewById(R.id.addItem);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = nameValue.getText().toString();
                if (text.length() != 0) {
                    items.add(text);
                    nameValue.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Intent intent = getIntent();
        ArrayList<String> getNameValue = new ArrayList<>();
        getNameValue = intent.getExtras().getStringArrayList("nameValue");
        if(getNameValue.size() != 0) {
            for (int i = 0; i < getNameValue.size(); i++) {
                items.add(getNameValue.get(i).toString());
                adapter.notifyDataSetChanged();
            }
        }



        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos;
                pos = itemListView.getCheckedItemPosition();
                if(pos != itemListView.INVALID_POSITION) {
                    items.remove(pos);
                    itemListView.clearChoices();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        chooseButton = (Button) findViewById(R.id.choose);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random = new Random();
                int randomValue = random.nextInt(items.size());
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemSettingActivity.this);
                builder.setTitle("빠-밤 ☆");
                builder.setMessage(items.get(randomValue));
                builder.setPositiveButton("확인", null);
                builder.create().show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        /*items.clear();
        adapter.notifyDataSetChanged();*/
        super.onBackPressed();
        Intent intent = new Intent(ItemSettingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.example.pickpick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class PersonalActivity extends AppCompatActivity {
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

        items = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonalActivity.this);
                builder.setTitle("빠-밤 ☆");
                builder.setMessage(items.get(randomValue));
                builder.setPositiveButton("확인", null);
                builder.create().show();
            }
        });
    }

    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }

        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();
    }

    private ArrayList getStringArrayPref(Context context, String key) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList urls = new ArrayList();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);

                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
        startActivity(intent);
        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON, items);
        Log.d(TAG, "Put json");
        finish();
    }
}

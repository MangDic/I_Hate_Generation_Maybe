package com.example.pickpick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private long time = 0;
    TextView jm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button eat = (Button) findViewById(R.id.eat);
        final Button dodo = (Button) findViewById(R.id.dodo);
        final Button personal = (Button) findViewById(R.id.personal);
        final Button noButton = (Button) findViewById(R.id.noButton);
        final Button oX = (Button) findViewById(R.id.half);
        final ArrayList<String> nameValue = new ArrayList<>();

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemSettingActivity.class);
                nameValue.add("피자");
                nameValue.add("치킨");
                nameValue.add("짜장면");
                nameValue.add("덮밥");
                nameValue.add("파스타");
                nameValue.add("돈까스");
                nameValue.add("닭갈비");
                nameValue.add("회");
                nameValue.add("떡볶이");
                nameValue.add("마라탕");
                nameValue.add("햄버거");
                nameValue.add("고기");
                nameValue.add("라멘");
                intent.putExtra("nameValue", nameValue);
                startActivity(intent);
                finish();
            }
        });

        dodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemSettingActivity.class);
                nameValue.add("영화보기");
                nameValue.add("VR");
                nameValue.add("방탈출카페");
                nameValue.add("사격");
                nameValue.add("집가쟈");
                nameValue.add("술먹쟈");
                nameValue.add("산책");
                nameValue.add("PC방");
                nameValue.add("당구장");
                nameValue.add("만화카페");
                intent.putExtra("nameValue", nameValue);
                startActivity(intent);
                finish();
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("아직 마음의 준비가 안 됐어요 >_<");
                builder.setPositiveButton("그..그래..", null);
                builder.create().show();
            }
        });

        oX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Random random = new Random();
                int randomValue = random.nextInt(2);
                if(randomValue == 0) {
                    builder.setTitle("빠-밤 ☆");
                    builder.setMessage("O");
                    builder.setPositiveButton("확인", null);
                }
                else {
                    builder.setTitle("빠-밤 ☆");
                    builder.setMessage("X");
                    builder.setPositiveButton("확인", null);
                }
                builder.create().show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "한번 더 누르면 종료합니다!", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finish();
        }
    }
}

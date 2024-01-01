package com.example.easykhatayt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etTitle, etDesc, etDate, etPrice;
    Button btnAddNewKhaatas, btnUpdateKhaata, btnDeleteKhaata, btnViewAllKhaatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        KhaataDB db = new KhaataDB(this);

        btnAddNewKhaatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String price = etPrice.getText().toString().trim();

                try {
                    db.open();
                    long records = db.addNewKhata(title, desc, date, price);
                    db.close();
                    Toast.makeText(MainActivity.this, "Khaata added successfully", Toast.LENGTH_SHORT).show();
                }catch (SQLException e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteKhaata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.open();
                    db.removeKhaata("1");
                    db.close();
                }catch (SQLException e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdateKhaata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    db.open();
                    db.updatKhaata("2", "New Title", "New Description", "1-Jan-2024", "15000");
                    db.close();
                }catch (SQLException e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnViewAllKhaatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewAllKhaatas.class);
                startActivity(intent);
            }
        });

    }

    private void init()
    {
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        etPrice = findViewById(R.id.etPrice);
        btnAddNewKhaatas = findViewById(R.id.btnAddNewKhata);
        btnDeleteKhaata = findViewById(R.id.btnDeleteKhata);
        btnUpdateKhaata = findViewById(R.id.btnUpdateKhata);
        btnViewAllKhaatas = findViewById(R.id.btnViewKhata);
    }
}
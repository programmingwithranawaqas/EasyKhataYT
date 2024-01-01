package com.example.easykhatayt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAllKhaatas extends AppCompatActivity {

    TextView tvViewAllKhaatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_khaatas);
        tvViewAllKhaatas = findViewById(R.id.tvViewAllKhaatas);
        KhaataDB db = new KhaataDB(this);

        try {
            db.open();
            tvViewAllKhaatas.setText(db.getAllKhaatas());
            db.close();
        }catch (SQLException e)
        {
            Toast.makeText(ViewAllKhaatas.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
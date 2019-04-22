package com.example.monoad.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.monoad.R;


public class delete extends AppCompatActivity {
    AppCompatButton order,user,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initializer();
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(delete.this,delete_first.class));
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //
                startActivity(new Intent(delete.this,delete_annonce.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(delete.this,delete_inside_annonce.class));

            }
        });

    }
    public void initializer(){
        order=(AppCompatButton)findViewById(R.id.appCompatButtonorder);
        user=(AppCompatButton)findViewById(R.id.appCompatButtonuser);
        add=(AppCompatButton)findViewById(R.id.appCompatButtonorder_daily);

    }

}

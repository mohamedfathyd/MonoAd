package com.example.monoad.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.monoad.R;
import com.example.monoad.model.Apiclient_home;
import com.example.monoad.model.apiinterface_home;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_code extends AppCompatActivity {
    TextInputLayout textInputLayouttime,textInputLayoutname;
    TextInputEditText textInputEditTexttime,textInputEditTextname;
    AppCompatButton regesiter;
    ProgressDialog progressDialog;
    apiinterface_home apiinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_code);
        initializer();
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                regesiter.setClickable(false);
            }
        });
    }
    public void initializer(){

        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutName);

        textInputLayouttime=findViewById(R.id.textInputLayoutphone);

        textInputEditTextname=(TextInputEditText)findViewById(R.id.textInputEditTextName);

        textInputEditTexttime=findViewById(R.id.textInputEditTextphone);

        regesiter=(AppCompatButton)findViewById(R.id.appCompatButtonRegister);
    }

    public void fetchInfo(){
        progressDialog = ProgressDialog.show(add_code.this,"جارى تسجيل الكود","Please wait...",false,false);
        progressDialog.show();

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_code(textInputEditTextname.getText().toString()
               ,textInputEditTexttime.getText().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(add_code.this);
                dlgAlert.setMessage("تم تسجيل الكود بنجاح ");
                dlgAlert.setTitle("MonoAd");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                regesiter.setClickable(true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                // Toast.makeText(add_shop.this,"تم التسجيل .. برجاء التأكد المره القادمة من الانترنت ",Toast.LENGTH_LONG).show();
            }
        });
    }
}


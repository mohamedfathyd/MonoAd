package com.example.monoad.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.monoad.R;
import com.example.monoad.model.Apiclient_home;
import com.example.monoad.model.apiinterface_home;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_shop extends AppCompatActivity {
    Intent intent;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> arrayList_id=new ArrayList<>();
    apiinterface_home apiinterface;
    AppCompatButton regesiter;
    private  static final int IMAGE = 100;
    ImageView imageView;
    Toolbar toolbar;
    int category_id;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    Spinner spinner;
    TextInputLayout textInputLayouttime,textInputLayoutname,textInputLayoutaddress,textInputLayoutphone,textInputLayouttext;
    TextInputEditText textInputEditTexttime,textInputEditTextname,textInputEditTextaddress,textInputEditTextphone,textInputEditTexttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        toolbar = (Toolbar) findViewById(R.id.app_bar);

        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        intent = getIntent();
        arrayList = intent.getStringArrayListExtra("category_list");
        arrayList_id=intent.getIntegerArrayListExtra("category_id");
        initializer();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                arrayList
        );
        spinner.setAdapter(adapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category_id=arrayList_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                regesiter.setClickable(false);
            }
        });
    }
    public void initializer(){
        spinner=(Spinner)findViewById(R.id.spin);
        imageView=(ImageView)findViewById(R.id.image);

        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutphone=(TextInputLayout)findViewById(R.id.textInputLayoutphone);
         textInputLayouttime=findViewById(R.id.textInputLayouttime);
         textInputLayouttext=findViewById(R.id.textInputLayouttext);
        textInputEditTextname=(TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextphone=(TextInputEditText)findViewById(R.id.textInputEditTextphone);
        textInputEditTexttime=findViewById(R.id.textInputEditTextime);
        textInputEditTexttext=findViewById(R.id.textInputEditTexttext);
        regesiter=(AppCompatButton)findViewById(R.id.appCompatButtonRegister);
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }
    private String convertToString()
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(add_shop.this,"جارى تسجيل الاعلان الجديد","Please wait...",false,false);
        progressDialog.show();
        String image = convertToString();
        int rate= Integer.parseInt(textInputEditTextphone.getText().toString());
        int time= Integer.parseInt(textInputEditTexttime.getText().toString());
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_shop(category_id,textInputEditTextname.getText().toString()
                ,image,rate,time,textInputEditTexttext.getText().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(add_shop.this);
                dlgAlert.setMessage("تم تسجيل الاعلان الجديد بنجاح ");
                dlgAlert.setTitle("MonoAd");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                regesiter.setClickable(true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(add_shop.this);
                dlgAlert.setMessage("تم تسجيل المرة القادمة قم بأختيار صور اصغر حجما وتاكد من جودة الأنترنت ");
                dlgAlert.setTitle("MonoAd");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                regesiter.setClickable(true);
                // Toast.makeText(add_shop.this,"تم التسجيل .. برجاء التأكد المره القادمة من الانترنت ",Toast.LENGTH_LONG).show();
            }
        });
    }
}

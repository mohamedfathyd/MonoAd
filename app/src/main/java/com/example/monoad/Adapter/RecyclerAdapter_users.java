package com.example.monoad.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monoad.R;
import com.example.monoad.model.Apiclient_home;
import com.example.monoad.model.apiinterface_home;
import com.example.monoad.model.contact_users;
import com.example.monoad.view.Users;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter_users extends RecyclerView.Adapter<RecyclerAdapter_users.MyViewHolder> {
    Typeface myTypeface;
    int points ;
    private Context context;
    List<contact_users> contactslist;
    apiinterface_home apiinterface;

    public RecyclerAdapter_users(Context context, List<contact_users> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

         myTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/flat.ttf");

         holder.Name.setText(contactslist.get(position).getName());
         holder.Name.setTypeface(myTypeface);
        holder.phone.setText(contactslist.get(position).getPhone());
        holder.phone.setTypeface(myTypeface);
        holder.point.setText(contactslist.get(position).getPoint()+" Pt");
        holder.point.setTypeface(myTypeface);
        holder.id.setTypeface(myTypeface);
        holder.id.setText(contactslist.get(position).getId() +" : id ");
        holder.age.setTypeface(myTypeface);
        holder.age.setText( " السن : " +contactslist.get(position).getAge());
        holder.country.setTypeface(myTypeface);
        holder.country.setText(" المحافظة : " + contactslist.get(position).getCountry());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo(contactslist.get(position).getId());
            }
        });
         holder.point.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 fetchInfo_update_points(contactslist.get(position).getId());
             }
         });
         holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,phone,point,id,country,age;

        ImageView delete;


    public MyViewHolder(View itemView) {
        super(itemView);
        Name=(TextView)itemView.findViewById(R.id.txt_fish_title);
        phone=itemView.findViewById(R.id.numtext);
        point= itemView.findViewById(R.id.points);
        country=itemView.findViewById(R.id.countrytext);
        age=itemView.findViewById(R.id.agetext);
        delete=itemView.findViewById(R.id.delete);
        id=itemView.findViewById(R.id.idtext);

    }
}
    public void fetchInfo(int id) {
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_user(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(context,"تم المسح",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, Users.class));
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
    public void fetchInfo_update_points(final int id) {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = dialogBuilder.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit, null);

        dialogBuilder.setTitle("MonoAd");

        dialogBuilder.setCancelable(false);


        final EditText etComments = (EditText) dialogView.findViewById(R.id.etComments);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                points = Integer.parseInt(etComments.getText().toString());
                fetch(id,points);
            }
        });


        dialogBuilder.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();

    }
    public void fetch(int id,int point){
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.update_user_points(id, point);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(context,"تم التعديل",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, Users.class));
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
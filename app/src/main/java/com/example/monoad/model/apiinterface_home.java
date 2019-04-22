package com.example.monoad.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiinterface_home {
    @FormUrlEncoded
    @POST("Mano_add_first_category.php") Call<ResponseBody> getcontacts_add_first_category(@Field("name") String name, @Field("image") String image);
    @FormUrlEncoded
    @POST("Mano_add_annonce.php") Call<ResponseBody> getcontacts_add_annonce(@Field("image") String image);
    @FormUrlEncoded
    @POST("mano_adcode.php") Call<ResponseBody> getcontacts_add_code(@Field("code") String image,@Field("points") String points);
    @GET("Mano_first_category.php")
    Call<List<contact_category>> getcontacts_category();
    @FormUrlEncoded
    @POST("Mano_add_shop.php") Call<ResponseBody> getcontacts_add_shop(@Field("category_id") int category_id,@Field("name") String name,
                                                                                           @Field("image") String image,@Field("points")int points,
                                                                                           @Field("time")int time,@Field("text")String text);
    @GET("Mano_users_list.php")
    Call<List<contact_users>> getcontacts_users();
    @FormUrlEncoded
    @POST("Mano_user_delete.php")
    Call<ResponseBody> delete_user(@Field("id") int id);

    @FormUrlEncoded
    @POST("Mano_user_update_point.php")
    Call<ResponseBody> update_user_points(@Field("id") int id,@Field("points")int points);

    @FormUrlEncoded
    @POST("Mano_all_first_category_delete.php")
    Call<ResponseBody> delete_first(@Field("id") int id);

    @GET("Mano_annonce.php")
    Call<List<contact_annonce>> getcontacts_annonce();
    @FormUrlEncoded
    @POST("Mano_all_annonce_category_delete.php")
    Call<ResponseBody> delete_annonce(@Field("id") int id);
    @GET("Mano_inside_annonce.php")
    Call<List<contact_inside_annonce>> getcontacts_inside_annonce();
    @FormUrlEncoded
    @POST("Mano_all_inside_annonce_category_delete.php")
    Call<ResponseBody> delete_inside_annonce(@Field("id") int id);
}


package com.dam.proyectodamdaw.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class ImageDownloader {

    private final static String TAG = ImageDownloader.class.getName();

    private static RequestQueue colaPeticiones ;

    public static void downloadImage(String url, ImageView imageView){
        Picasso.get().load(url).into(imageView);
    }

    public static void downloadImage(Context context, String url, ImageView imageView, ImageView.ScaleType scale, int defaultDrawable){
        ImageRequest peticion = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, scale,null, // maxWidth, maxHeight, scale,decodeConfig
                new Response.ErrorListener() {
                    @Override public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(defaultDrawable);
                        Log.e(TAG,error.getMessage());
                    }
                }
        );
        getRequestQueue(context).add(peticion);
    }

    private static RequestQueue getRequestQueue(Context context){
        if(colaPeticiones==null)
            colaPeticiones = Volley.newRequestQueue(context);
        return colaPeticiones;
    }
}
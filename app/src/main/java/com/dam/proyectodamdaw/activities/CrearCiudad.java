package com.dam.proyectodamdaw.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.api.Connector;
import com.dam.proyectodamdaw.base.BaseActivity;
import com.dam.proyectodamdaw.base.CallInterface;

public class CrearCiudad extends BaseActivity implements CallInterface {

    EditText nombre, lan, lon, ima;
    Button acept, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ciudad);

        nombre = findViewById(R.id.city);
        lan = findViewById(R.id.laLatitud);
        lon = findViewById(R.id.laLong);
        ima = findViewById(R.id.laIma);
        acept = findViewById(R.id.acept);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("cityname",nombre.getText().toString());
                intent.putExtra("citylon",lon.getText().toString());
                intent.putExtra("citylat",lan.getText().toString());
                intent.putExtra("cityima",ima.getText().toString());
                setResult(RESULT_OK, intent);
                //showProgress();
                executeCall(CrearCiudad.this);
            }
        });

    }

    @Override
    public void doInBackground() {
        Connector.getConector().post(Ciudad.class, new Ciudad(nombre.getText().toString(), Float.parseFloat(lon.getText().toString()), Float.parseFloat(lan.getText().toString()), ima.getText().toString()),"/addciudad");
    }

    @Override
    public void doInUI() {
        //hideProgress();
        finish();
    }
}
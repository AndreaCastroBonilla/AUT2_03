package com.example.aut2_03;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import db.DbTravel;
import db.entidades.Travel;

public class VerActivity extends AppCompatActivity {

    private EditText txtId, txtNombre, txtCapital, txtIdioma;
    private ImageButton guardarBtn;
    private Travel travel;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtId = findViewById(R.id.textId);
        txtNombre = findViewById(R.id.textNombre);
        txtCapital = findViewById(R.id.textPais);
        txtIdioma = findViewById(R.id.textIdioma);
        guardarBtn = findViewById(R.id.imageButtonSave);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }

        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbTravel dbTravel = new DbTravel(VerActivity.this);
        travel = dbTravel.verDato(id);

        if(travel != null){
            txtId.setText(travel.getId());
            txtNombre.setText(travel.getNombre());
            txtCapital.setText(travel.getCapital());
            txtIdioma.setText(travel.getIdioma());

            guardarBtn.setVisibility(View.INVISIBLE);
            txtId.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCapital.setInputType(InputType.TYPE_NULL);
            txtIdioma.setInputType(InputType.TYPE_NULL);
        }

    }
}
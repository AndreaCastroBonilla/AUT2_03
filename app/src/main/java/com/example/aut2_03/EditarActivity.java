package com.example.aut2_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DbTravel;
import db.entidades.Travel;

public class EditarActivity extends AppCompatActivity {

    private EditText txtId, txtNombre, txtCapital, txtIdioma;
    private ImageButton guardarBtn;
    private Travel travel;
    boolean correcto = false;
    private int i = 0;

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
                i = Integer.parseInt(null);
            }else {
                i = extras.getInt("ID");
            }

        }else {
            i = (int) savedInstanceState.getSerializable("ID");
        }

        DbTravel dbTravel = new DbTravel(EditarActivity.this);
        travel = dbTravel.verDato(i);

        if(travel != null){
            txtId.setText(travel.getId());
            txtNombre.setText(travel.getNombre());
            txtCapital.setText(travel.getCapital());
            txtIdioma.setText(travel.getIdioma());
        }

        guardarBtn.setOnClickListener(new View.OnClickListener() {
            String nombre = txtNombre.getText().toString();
            String capital = txtCapital.getText().toString();
            String idioma = txtIdioma.getText().toString();
            String id = txtId.getText().toString();
            @Override
            public void onClick(View view) {
                if(!id.equals("") && !nombre.equals("")){
                    correcto = dbTravel.modificarDato(i, id, nombre, capital, idioma);

                    if(correcto){
                        Toast.makeText(EditarActivity.this,"REGISTRO MODIFICADO :)",Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this,"ERROR AL MODIFICAR :(",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this,"CÓDIGO Y NOMBRE OBLIGATORIOS :(",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private  void verRegistro(){
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra("ID",i);
        startActivity(intent);
    }
}
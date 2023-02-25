package com.example.aut2_03;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import db.DbTravel;

public class Activity1_Add extends AppCompatActivity {

    private EditText txtId, txtNombre, txtPais, txtIdioma;
    private ImageButton saveBtn, cancelBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity1_add);

        txtId = findViewById(R.id.textId);
        txtNombre = findViewById(R.id.textNombre);
        txtPais = findViewById(R.id.textPais);
        txtIdioma = findViewById(R.id.textIdioma);

        saveBtn = findViewById(R.id.imageButtonSave);
        cancelBtn = findViewById(R.id.imageButtonCancel);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbTravel dbTravel = new DbTravel(Activity1_Add.this);
                long res = dbTravel.insertar(txtId.getText().toString(),txtNombre.getText().toString(),txtPais.getText().toString(),txtIdioma.getText().toString());

                if(res > 0){
                    Toast.makeText(Activity1_Add.this, "GUARDADO CORRECTO :)", Toast.LENGTH_SHORT).show();
                    clean();
                }else {
                    Toast.makeText(Activity1_Add.this, "GUARDADO INCORRECTO :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clean(){
        txtId.setText("");
        txtNombre.setText("");
        txtPais.setText("");
        txtIdioma.setText("");
    }
}

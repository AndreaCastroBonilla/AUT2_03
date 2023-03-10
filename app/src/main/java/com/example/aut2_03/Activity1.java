package com.example.aut2_03;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import db.DbHelper;
import db.DbTravel;

public class Activity1 extends Fragment {

    private EditText txtId, txtNombre, txtPais, txtIdioma;
    private ImageButton saveBtn, cancelBtn;
    private Button createBtn;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Activity1() {
        // Required empty public constructor
    }

    public static Activity1 newInstance(String param1, String param2) {
        Activity1 fragment = new Activity1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_activity1, container, false);
    }





    //CREAR BASES DE DATOS
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //createBtn = (Button) getView().findViewById(R.id.crearButton);
       /* createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(getContext());
                SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

                if(dbHelper != null){
                    Toast.makeText(getContext(),"BASE DE DATOS CREADA :)", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"ERROR AL CREAR LA BASE DE DATOS :(", Toast.LENGTH_LONG).show();
                }
            }
       });
        */
    }


    //CARGAR MENU OPCIONES BASES DE DATOS
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu1,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    //ACCIONES MEN?? BASE DE DATOS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        txtId = getView().findViewById(R.id.textId);
        txtNombre = getView().findViewById(R.id.textNombre);
        txtPais = getView().findViewById(R.id.textPais);
        txtIdioma = getView().findViewById(R.id.textIdioma);
        DbTravel dbTravel = new DbTravel(getContext());
        switch (item.getItemId()){
            case R.id.add:
                //comprobaci??n del tama??o del id
                if(txtId.getText().toString().length() == 3){
                    long res = dbTravel.insertar(txtId.getText().toString(),txtNombre.getText().toString(),txtPais.getText().toString(),txtIdioma.getText().toString());

                    //comprobaci??n del guardado
                    if(res > 0){
                        Toast.makeText(getContext(), "GUARDADO CORRECTO :)", Toast.LENGTH_SHORT).show();
                        clean();
                    }else {
                        Toast.makeText(getContext(), "C??DIGO INCORRECTO :(", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "EL C??DIGO DEBE CONTENER 3 CARACTERES", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.delete:
                boolean res2 = dbTravel.modificarDato(0,txtId.getText().toString(),txtNombre.getText().toString(),txtPais.getText().toString(),txtIdioma.getText().toString());

                //comprobaci??n del borrado
                if(res2){
                    Toast.makeText(getContext(), "DELETE", Toast.LENGTH_SHORT).show();
                    clean();
                }else {
                    Toast.makeText(getContext(), "DELETE FAILS :(", Toast.LENGTH_SHORT).show();
                }
                return  true;

            case R.id.modify:
                Toast.makeText(getContext(), "SHOULD MODIFY :(", Toast.LENGTH_SHORT).show();
                //Intent intent= new Intent(getContext(),EditarActivity.class);
                //startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
    }

}

    //M??TODO QUE LIMPIA LOS CAMPOS
    private void clean(){
        txtId.setText("");
        txtNombre.setText("");
        txtPais.setText("");
        txtIdioma.setText("");
    }


}
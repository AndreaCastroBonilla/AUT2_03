package com.example.aut2_03;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity1 extends Fragment {

    private EditText txtId, txtNombre, txtPais, txtIdioma;
    private ImageButton saveBtn, cancelBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Activity1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Activity1.
     */
    // TODO: Rename and change types and number of parameters
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

    private Button createBtn;



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


    //ACCIONES MENÚ BASE DE DATOS
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        txtId = getView().findViewById(R.id.textId);
        txtNombre = getView().findViewById(R.id.textNombre);
        txtPais = getView().findViewById(R.id.textPais);
        txtIdioma = getView().findViewById(R.id.textIdioma);

        switch (item.getItemId()){
            case R.id.add:
                DbTravel dbTravel = new DbTravel(getContext());
                long res = dbTravel.insertar(txtId.getText().toString(),txtNombre.getText().toString(),txtPais.getText().toString(),txtIdioma.getText().toString());

                if(res > 0){
                    Toast.makeText(getContext(), "GUARDADO CORRECTO :)", Toast.LENGTH_SHORT).show();
                    clean();
                }else {
                    Toast.makeText(getContext(), "CÓDIGO OBLIGATORIO :(", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.delete:
                Toast.makeText(getContext(), "DELETE", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.modify:
                Toast.makeText(getContext(), "MODIFY", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
    }

}
    private void clean(){
        txtId.setText("");
        txtNombre.setText("");
        txtPais.setText("");
        txtIdioma.setText("");
    }

    private void newView(){
        Intent intent = new Intent(getActivity(),Activity1_Add.class);
        startActivity(intent);
    }

}
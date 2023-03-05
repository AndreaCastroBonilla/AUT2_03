package com.example.aut2_03;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import db.DbTravel;
import db.adaptadores.ListaTravelAdapter;
import db.entidades.Travel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Activity2() {
        // Required empty public constructor
    }

    public static Activity2 newInstance(String param1, String param2) {
        Activity2 fragment = new Activity2();
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
        return inflater.inflate(R.layout.fragment_activity2, container, false);
    }


    private RecyclerView listaTravel;
    private ArrayList<Travel> listaArrayTravel;

    //CARGAR LOS ELEMENTOS DEL ADAPTADOR A LA VISTA PRINCIPAL DEL FRAGMENTO
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaTravel = getView().findViewById(R.id.listaTravel);
        listaTravel.setLayoutManager(new LinearLayoutManager(getContext()));

        DbTravel dbTravel = new DbTravel(getContext());
        listaArrayTravel = new ArrayList<Travel>();

        ListaTravelAdapter adapter = new ListaTravelAdapter(dbTravel.mostrarDatos());
        listaTravel.setAdapter(adapter);
    }
}
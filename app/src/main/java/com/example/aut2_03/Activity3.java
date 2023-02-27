package com.example.aut2_03;

import static android.os.Environment.getExternalStorageDirectory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Activity3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Activity3.
     */
    // TODO: Rename and change types and number of parameters
    public static Activity3 newInstance(String param1, String param2) {
        Activity3 fragment = new Activity3();
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
        return inflater.inflate(R.layout.fragment_activity3, container, false);
    }


    private MediaRecorder grabadora = null;
    private String ruta = null;
    private ImageView imgGrabar = null;
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgGrabar = getView().findViewById(R.id.imageStop);
        String[]arrayPermitions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),arrayPermitions,1000);
        }

    }

    @SuppressLint("WrongConstant")
    public void grabar(View view){
        if(grabadora == null){
            ruta = getExternalStorageDirectory().getAbsolutePath().toString() + "/grabacion.mp3";
            grabadora = new MediaRecorder();
            grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabadora.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            grabadora.setOutputFile(ruta);

            try{
                grabadora.prepare();
                grabadora.start();
                imgGrabar.setBackgroundColor(Color.rgb(255,0,0));
                Toast.makeText(getContext(), "GRABANDO...", Toast.LENGTH_SHORT).show();
            }catch (IOException e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }else {
            try {
                grabadora.stop();
                grabadora.release();
                imgGrabar.setBackgroundColor(Color.rgb(0,0,255));
                Toast.makeText(getContext(), "GRABANCIÓN FINALIZADA :)", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
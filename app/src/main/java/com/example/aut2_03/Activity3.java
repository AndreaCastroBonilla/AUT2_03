package com.example.aut2_03;

import static android.os.Environment.getExternalStorageDirectory;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity3 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Activity3() {
        // Required empty public constructor
    }

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
        return inflater.inflate(R.layout.fragment_activity3, container, false);
    }


    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private ImageButton btnRecorder,btnPlay;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRecorder = getView().findViewById(R.id.btnStop);
        String[]arrayPermitions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
            Toast.makeText(getContext(), "PERMISSIONS ok", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "PERMISSIONS fail :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void grabar(View view){
        if(grabacion == null){
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/grabacion.mp3";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            grabacion.setOutputFile(archivoSalida);

            try{
                grabacion.prepare();
                grabacion.start();
                //btnRecorder.setBackgroundColor(Color.rgb(255,0,0));
                Toast.makeText(getContext(), "RECORDING...", Toast.LENGTH_SHORT).show();

            }catch (IOException e) {
            }

        }else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
           // btnRecorder.setBackgroundColor(Color.rgb(0,0,255));
            Toast.makeText(getContext(), "RECORDING FINISHED :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view){
        if(archivoSalida != null){
            MediaPlayer mediaPlayer = new MediaPlayer();
            try{
                mediaPlayer.setDataSource(archivoSalida);
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(getContext(), "REPRODUCING RECORGIND...", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
            }

        }else {
            Toast.makeText(getContext(), "MUST RECORD AN AUDIO :)", Toast.LENGTH_SHORT).show();
        }
    }
}
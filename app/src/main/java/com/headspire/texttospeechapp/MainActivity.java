package com.headspire.texttospeechapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech toSpeech;
    private EditText texttospeech;
    private Button speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texttospeech=findViewById(R.id.texttospeech);
        speak=findViewById(R.id.speak);
        toSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    toSpeech.setLanguage(Locale.UK);
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=texttospeech.getText().toString();
                toSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    protected void onPause() {
        if(toSpeech!=null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
        super.onPause();
    }
}

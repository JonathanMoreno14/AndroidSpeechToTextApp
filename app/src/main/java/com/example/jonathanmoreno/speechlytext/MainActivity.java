package com.example.jonathanmoreno.speechlytext;

import android.Manifest;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecognitionListener {


    TextView speechReturnedText;
    ProgressBar progressBar;
    FloatingActionButton fabBtn;
    SpeechRecognizer speechRecognizer = null;
    Intent speechRecoginzerIntent;
    final int REQUEST_PERMISSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechReturnedText = (TextView) findViewById(R.id.speechTextView);
        fabBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);


        String[] PERMISSIONS = {Manifest.permission.RECORD_AUDIO};
        if(!com.example.jonathanmoreno.speechlytext.Function.hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION);
        }

        progressBar.setVisibility(View.INVISIBLE);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
        speechRecoginzerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //Adding extra RecognizerIntent
        speechRecoginzerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        speechRecoginzerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        speechRecoginzerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        speechRecoginzerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        //Input recording time 5 seconds => you change change the time of recording if you like
        speechRecoginzerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 5000);
        speechRecoginzerIntent.putExtra("android.speech.extra.DICTATION_MODE", true);



       fabBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               speechRecognizer.startListening(speechRecoginzerIntent);
               fabBtn.setEnabled(false);
               Snackbar snackbar = Snackbar.make(v, "Clr the speech to text", Snackbar.LENGTH_INDEFINITE)
                       .setAction("CLR", new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               speechReturnedText.setText("");
                               Snackbar snackbar1 = Snackbar.make(view, "Cleared speech to text", Snackbar.LENGTH_SHORT);
                               snackbar1.show();
                           }
                       });
               snackbar.setActionTextColor(getColor(R.color.colorPrimaryDark));
               snackbar.show();


           }
       });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();

        }

    }

    @Override
    public void onBeginningOfSpeech() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBufferReceived(byte[] buffer) {
     //   Log.d("Log", "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        progressBar.setVisibility(View.INVISIBLE);
        fabBtn.setEnabled(true);
    }


    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        progressBar.setVisibility(View.INVISIBLE);
        speechReturnedText.setText(errorMessage);
        fabBtn.setEnabled(true);
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        ArrayList<String> matches = arg0.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        text = matches.get(0);
        speechReturnedText.setText(text);
    }


    @Override
    public void onReadyForSpeech(Bundle arg0) {
        //Log.d("Log", "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
     //   Log.d("Log", "onResults");

    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
     //   Log.d("Log", "onEvent");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        progressBar.setProgress((int) rmsdB);

    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Cannot understand, please try again.";
                break;
        }
        return message;
    }


}

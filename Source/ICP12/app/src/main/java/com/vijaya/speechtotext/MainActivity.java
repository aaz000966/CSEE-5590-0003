package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener{

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView voiceInput;
    private ImageButton mSpeakBtn;
    private static final String NAME = "name";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static String USERNAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceInput = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        voiceInput.addTextChangedListener(new TextWatcher() {
            TextToSpeech tts = new TextToSpeech(MainActivity.this,MainActivity.this);
            public void afterTextChanged(Editable s) {

                if(voiceInput.getText().toString().equals("hello"))
                {

                    tts.setLanguage(Locale.US);
                    tts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
                }
                if(voiceInput.getText().toString().equals("I'm not feeling good"))
                {

                    tts.setLanguage(Locale.US);
                    tts.speak("I can understand. Please tell your symptoms in short.", TextToSpeech.QUEUE_FLUSH, null);
                }
                else {
                    tts.setLanguage(Locale.US);
                    tts.speak(voiceInput.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(result.get(0).contains("name")) {

                        int length = result.get(0).length();
                        preferences = getSharedPreferences("PREFS", 0);
                        editor = preferences.edit();
                        editor.putString(NAME, result.get(0).substring(result.get(0).indexOf("is") + 3, length)).apply();
                        USERNAME = result.get(0).substring(result.get(0).indexOf("is") + 3, length);
                        voiceInput.setText("Hi " + result.get(0).substring(result.get(0).indexOf("is") + 3, length));
                    }
                    if(result.get(0).toLowerCase().equals(("I'm not feeling good").toLowerCase()))
                    {
                        Log.i("secondcase", "triggered");
                        voiceInput.setText("I can understand. Please tell your symptoms in short.");
                    }//Thank you, my Medical Assistant
                    if(result.get(0).toLowerCase().equals(("thank you my medical assistant").toLowerCase()))
                    {
                        Log.i("Thirdcase", "triggered");
                        voiceInput.setText("Thank you too " + USERNAME+ " .Take care");
                    }
                    if(result.get(0).toLowerCase().equals(("what time is it").toLowerCase()))
                    {
                        Log.i("Forthcase", "triggered");
                        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm a");//dd/MM/yyyy
                        Date now = new Date();
                        String[] strDate = sdfDate.format(now).split(":");
                        if(strDate[1].contains("00"))
                        {
                            strDate[1] = "o'clock";
                        }
                        Log.i("Date","The time is " + sdfDate.format(now));
                        voiceInput.setText("The time is " + sdfDate.format(now));
                    }
                    if (result.get(0).toLowerCase().equals(("hello").toLowerCase())){
                        voiceInput.setText(result.get(0));
                    }
                }
                break;
            }

        }
    }

    @Override
    public void onInit(int status) {

    }
}
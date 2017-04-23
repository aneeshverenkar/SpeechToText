package com.example.aneesh.sinque;

import android.graphics.Typeface;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ibm.watson.developer_cloud.android.library.audio.utils.ContentType;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;

import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneInputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    MicrophoneInputStream capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*Button myTextView = (Button) findViewById(R.id.button1);
        Typeface typeface= Typeface.createFromAsset(getAssets(), "fonts/Moon Light.otf");
        myTextView.setTypeface(typeface);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int sampleRate = 16000;

        final Button button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                capture = new MicrophoneInputStream(true);
                SpeechToText service = new SpeechToText();
                service.setUsernameAndPassword("f7c43500-f215-48c5-8697-9f288814ea0e", "VJ00zCxcI8y0");

                Log.d("Print","clicked");


                try {
                    service.recognizeUsingWebSocket(capture, getRecognizeOptions(), new MicrophoneRecognizeDelegate());
                } catch (Exception e) {
                    //showError(e);
                }
            }
        });

    }

    private RecognizeOptions getRecognizeOptions() {
        return new RecognizeOptions.Builder()
                .continuous(true)
                .contentType(ContentType.OPUS.toString())
                .model("en-US_BroadbandModel")
                .interimResults(true)
                .inactivityTimeout(2000)
                .build();
    }


    private class MicrophoneRecognizeDelegate implements RecognizeCallback {

        @Override
        public void onTranscription(SpeechResults speechResults) {
            //System.out.println(speechResults);
            if (speechResults.getResults() != null && !speechResults.getResults().isEmpty()) {
                String text = speechResults.getResults().get(0).getAlternatives().get(0).getTranscript();
                //showMicText(text);

                if (text.contains("pause")) {
                    URL url;
                    HttpURLConnection urlConnection = null;
                    Log.d("Print","Asd");
                    try {
                        url = new URL("http://5ec67a0f.ngrok.io/sampleurl?name=pause");

                        urlConnection = (HttpURLConnection) url
                                .openConnection();

                        InputStream in = urlConnection.getInputStream();





                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }


                }else if (text.contains("play")){
                    URL url;
                    HttpURLConnection urlConnection = null;
                    Log.d("Print","Asd");
                    try {
                        url = new URL("http://5ec67a0f.ngrok.io/sampleurl?name=play");

                        urlConnection = (HttpURLConnection) url
                                .openConnection();

                        InputStream in = urlConnection.getInputStream();





                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
                }else if(text.contains("next")){
                    URL url;
                    HttpURLConnection urlConnection = null;
                    Log.d("Print","Asd");
                    try {
                        url = new URL("http://5ec67a0f.ngrok.io/sampleurl?name=next");

                        urlConnection = (HttpURLConnection) url
                                .openConnection();

                        InputStream in = urlConnection.getInputStream();





                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
                }


            }
        }


        @Override public void onConnected() {

        }

        @Override public void onError(Exception e) {
            //showError(e);
            //enableMicButton();
        }
;
        @Override public void onDisconnected() {
            //enableMicButton();
        }

        @Override
        public void onInactivityTimeout(RuntimeException runtimeException) {

        }

        @Override
        public void onListening() {

        }
    }


}


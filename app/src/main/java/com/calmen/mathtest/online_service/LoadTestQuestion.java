package com.calmen.mathtest.online_service;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.calmen.mathtest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;

public class LoadTestQuestion extends AppCompatActivity {

    public static final String BASE_URL = "https://10.0.2.2:8000/testwebservice/rest";
    private static final String TAG = "LoadTestQuestion";
    public static final int NUM_ITEMS = 4; // The server returns only 4 items for the question
    public static final int INDEX_FOR_QUESTIONS = 0;
    public static final int INDEX_FOR_RESULT = 1;
    public static final int INDEX_FOR_OPTIONS = 2;
    public static final int INDEX_FOR_TIME_TO_SOLVE = 3;

    private ProgressBar progressBar;
    private TextView textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_test_question);

        textArea = findViewById(R.id.textArea);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);
                new DownloaderTask().execute();
            }
        });
    }

    private class DownloaderTask extends AsyncTask<Void, Integer, String> {
        private int totalBytes;

        @Override
        protected String doInBackground(Void... voids) {
            String text;
            try {
                String urlString = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("method", "thedata.getit")
                        .appendQueryParameter("format", "json")
                        .appendQueryParameter("api_key", "01189998819991197253")
                        .build().toString();
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                DownloadUtils.addCertificate(LoadTestQuestion.this, (HttpsURLConnection) connection);

                totalBytes = connection.getContentLength();

                try {
                    Log.d(TAG, "Connecting");

                    int responseCode = connection.getResponseCode();
                    String responsMSG = connection.getResponseMessage();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        String msg = String.format(
                                "Internal error from server: %d - %s",
                                responseCode, responsMSG
                        );
                        Log.d(TAG, msg);
                        throw new IOException(msg);
                    }
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    int totalBytesRead = 0;
                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    bytesRead = is.read(buffer);
                    while (bytesRead > 0) {
                        baos.write(buffer, 0, bytesRead);
                        bytesRead = is.read(buffer);
                        // publishProgress(totalBytesRead);
                    }
                    baos.close();
                    return new String(baos.toByteArray());
                } finally {
                    Log.d(TAG, "Disconnect");
                    connection.disconnect();
                }

            } catch (GeneralSecurityException |IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setIndeterminate(false);
            progressBar.setMax(totalBytes);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            String[] items = parseResult(result);
            String itemStr = "";

            for (int i = 0; i < items.length; ++i) {
                itemStr += items[i] + "\n";
            }
            textArea.setText(itemStr);
            progressBar.setVisibility(View.INVISIBLE);
        }

        /***
         * @param result is in the form of JSON
         * @return items that consists of all the attributes returned in JSON
         */
        private String[] parseResult(String result) {
            System.out.println("result: " + result);
            String[] items = new String[NUM_ITEMS];

            try {
                JSONObject jBase = new JSONObject(result);
                for (int i = 0; i < NUM_ITEMS; ++i) {
                    switch (i) {
                        case INDEX_FOR_QUESTIONS:
                            items[i] = jBase.getString("question");
                            break;
                        case INDEX_FOR_RESULT:
                            items[i] = jBase.getString("result");
                            break;
                        case INDEX_FOR_OPTIONS:
                            JSONArray optionsArr = jBase.getJSONArray("options");
                            // if it is not manual input, then the options length is not 0
                            if (optionsArr.length() == 0) {
                                items[i] = "";
                            } else {
                                // Initialise the entry first so that we will not have null at the beginning
                                items[i] = "";
                                for (int ii = 0; ii < optionsArr.length(); ++ii) {
                                    items[i] += optionsArr.getString(ii) + ",";
                                }
                            }
                            break;
                        case INDEX_FOR_TIME_TO_SOLVE:
                            items[i] = jBase.getString("timetosolve");
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return items;
        }
    }

}
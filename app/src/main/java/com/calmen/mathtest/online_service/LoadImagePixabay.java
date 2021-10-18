package com.calmen.mathtest.online_service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.calmen.mathtest.R;
import com.calmen.mathtest.view_list.grid_view_image.GridImageAdapter;
import com.calmen.mathtest.view_list.recycler_view_student.StudentListRecyclerAdapter;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;

public class LoadImagePixabay extends AppCompatActivity {
    public static final int NUM_IMAGES = 20;

    GridView gridView;
    Context context = this;
    private String searchVal;
    private Bitmap[] images = new Bitmap[NUM_IMAGES];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image_pixabay);

        searchVal = getIntent().getStringExtra("searchVal");
        gridView = findViewById(R.id.gridViewImage);
        new DownloaderTask().execute();
    }

    private class DownloaderTask extends AsyncTask<String, Integer, Bitmap[]> implements Serializable {
        public static final String BASE_URL = "https://pixabay.com/api/";
        public static final String API_KEY = "23740806-7f34edb495a9109a0d41af9df";
        public static final String TAG = "LoadImagePixabay";

        @Override
        protected Bitmap[] doInBackground(String... strings) {
            String data;
            try {
                Uri.Builder url = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter("key", API_KEY)
                        .appendQueryParameter("q", searchVal);
                String urlString = url.build().toString();
                Log.d("Hello", "pictureRetrievalTask: " + urlString);

                HttpsURLConnection connection = openConnection(urlString);
                try {
                    Log.d(TAG, "Connecting");

                    int responseCode = connection.getResponseCode();
                    String responseMsg = connection.getResponseMessage();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        String msg = String.format(
                                "Internal error from server: %d - %s",
                                responseCode, responseMsg
                        );
                        Log.d(TAG, msg);
                        throw new IOException(msg);
                    }

                    data = downloadToString(connection);
                    if (data != null) {
                        String[] imageUrls = getImagesLargeUrl(data);
                        if (imageUrls != null) {
                            for (int i = 0; i < imageUrls.length; ++i) {
                                images[i] = getImageFromUrl(imageUrls[i]);
                            }
                        }
                    }
                } catch (InternalError e) {
                    throw new InternalError(e.getMessage());
                } finally {
                    Log.d(TAG, "Disconnect");
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return images;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            /*
            progressBar.setIndeterminate(false);
            progressBar.setMax(totalBytes);
            progressBar.setProgress(values[0]);
             */
        }

        @Override
        protected void onPostExecute(Bitmap[] images) {
            GridImageAdapter adapter = new GridImageAdapter(images, context);
            gridView.setAdapter(adapter);
        }

        private String downloadToString(HttpURLConnection conn){
            String data = null;
            try {
                InputStream inputStream = conn.getInputStream();
                byte[] byteData = IOUtils.toByteArray(inputStream);
                data = new String(byteData, StandardCharsets.UTF_8);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }

        private String[] getImagesLargeUrl(String data) {
            String[] imageUrls = new String[NUM_IMAGES];
            try {
                JSONObject jBase = new JSONObject(data);
                JSONArray jHits = jBase.getJSONArray("hits");
                if(jHits.length() > 0) {
                    for (int i = 0; i < NUM_IMAGES; ++i) {
                        JSONObject jHitsItem = jHits.getJSONObject(i);
                        imageUrls[i] = jHitsItem.getString("largeImageURL");
                        System.out.println("imageUrl[" + i + "]: " + imageUrls[i]);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return imageUrls;
        }

        private Bitmap getImageFromUrl(String imageUrl) throws InternalError {
            Bitmap image;

            Uri.Builder url = Uri.parse(imageUrl).buildUpon();
            String urlString = url.build().toString();
            Log.d("Hello", "ImageUrl: " + urlString);

            HttpURLConnection connection = openConnection(urlString);
            if(connection == null) {
                throw new InternalError("Check internet");
            }
            else if (isConnectionOkay(connection) == false){
                throw new InternalError("Problem with downloading");
            } else{
                image = downloadToBitmap(connection);
                if(image !=null) {
                    Log.d("Hello", image.toString());
                }
                else{
                    Log.d("Hello", "Nothing returned");
                }
                connection.disconnect();
            }
            return image;
        }

        private boolean isConnectionOkay(HttpURLConnection conn){
            try {
                if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        private Bitmap downloadToBitmap(HttpURLConnection conn){
            Bitmap data = null;
            try {
                InputStream inputStream = conn.getInputStream();
                byte[] byteData = getByteArrayFromInputStream(inputStream);
                Log.d("Hello byteData length", String.valueOf(byteData.length));
                data = BitmapFactory.decodeByteArray(byteData,0,byteData.length);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }

        private byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[4096];
            int progress = 0;
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
                progress = progress+nRead;
            }
            return buffer.toByteArray();
        }

        private HttpsURLConnection openConnection(String urlString) {
            HttpsURLConnection connection = null;

            try {
                URL url = new URL(urlString);
                connection = (HttpsURLConnection) url.openConnection();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
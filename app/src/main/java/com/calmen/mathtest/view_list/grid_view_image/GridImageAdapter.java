package com.calmen.mathtest.view_list.grid_view_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.shared.Conversion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GridImageAdapter extends BaseAdapter, AsyncTask<String, Integer, String[]> {
    // private byte[][] images;
    private String[] imagesURL;
    private Context context, loadImageContext;
    private LayoutInflater layoutInflater;

    public GridImageAdapter(String[] inImagesURL, Context inContext, Context inLoadImageContext) {
        this.imagesURL = inImagesURL;
        this.context = inContext;
        this.loadImageContext = inLoadImageContext;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesURL.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_images, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.imageViewGrid);
        Bitmap bitmap = getImageFromUrl(imagesURL[i]);
        // imageView.setImageBitmap(Conversion.convertImageFromByteToBitmap(images[i]));
        imageView.setImageBitmap(bitmap);
        return view;
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

    @Override
    protected String[] doInBackground(String... strings) {
        return new String[0];
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

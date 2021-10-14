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

import com.calmen.mathtest.R;
import com.calmen.mathtest.shared.Conversion;

import java.util.ArrayList;

public class GridImageAdapter extends BaseAdapter {
    private Bitmap[] images;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridImageAdapter(Bitmap[] inImages, Context inContext) {
        this.images = inImages;
        this.context = inContext;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
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
        imageView.setImageBitmap(images[i]);
        return view;
    }

}

package com.example.gbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ItemAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] Ids;
	private final int list_row_resource;

	public ItemAdapter(Context context, int listRowResourceId, String[] objects) {
		super(context, listRowResourceId, objects);
		this.context = context;
		this.Ids = objects;
		this.list_row_resource = listRowResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(list_row_resource, parent, false);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.list_imageView);
		TextView textView = (TextView) rowView.findViewById(R.id.list_textView);

		int id = Integer.parseInt(Ids[position]);
		
		Log.i("Inside ItemAdapter", String.valueOf(id));
		if(Model.GetbyId(id).Name != null) {
			textView.setText(Model.GetbyId(id).Name);
		} else {
			textView.setText("");
		}
		
		// get input stream
		InputStream ims = null;
		final Bitmap bmap[] = new Bitmap[1];

		// load image as Drawable
			Thread t = new Thread() {
				public void run() {
					URL sampleURL = null;
					try {
						sampleURL = new URL(
								"http://eofdreams.com/data_images/dreams/image/image-07.jpg");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						bmap[0] = BitmapFactory.decodeStream(sampleURL
								.openConnection().getInputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		Drawable d = Drawable.createFromStream(ims, null);
		// set image to ImageView
		// imageView.setImageDrawable(d);
		imageView.setImageBitmap(bmap[0]);
		return rowView;

	}

}
package com.example.gbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
	private final int rowResourceId;

	public ItemAdapter(Context context, int textViewResourceId, String[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.Ids = objects;
		this.rowResourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(rowResourceId, parent, false);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.list_imageView);
		TextView textView = (TextView) rowView.findViewById(R.id.list_textView);

		int id = Integer.parseInt(Ids[position]);
		String imageFile = Model.GetbyId(id).IconFile;

		textView.setText(Model.GetbyId(id).Name);
		// get input stream
		InputStream ims = null;
		final Bitmap bmap[] = new Bitmap[1];
		try {
			ims = context.getAssets().open(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
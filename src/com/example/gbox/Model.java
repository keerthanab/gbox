package com.example.gbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

import com.gbox.amazon.*;

public class Model {

	public static ArrayList<Item> Items;

	public static void LoadModel() {

		Items = new ArrayList<Item>();
		ArrayList<String> keywords = new ArrayList<String>() {
			{
				add("wifi card");
				add("movie");
			}
		};
		List<AmazonSearchHelper.Result> results = new ArrayList<AmazonSearchHelper.Result>();
		//java.util.Collections.shuffle((keywords));
		for (int i = 0; i < keywords.size(); i++)
			results.addAll(AmazonSearchHelper.search(keywords.get(i), 2));

		for (int i = 0; i < 10 && i<results.size(); i++) {
			AmazonSearchHelper.Result result = results.get(i);
			Items.add(new Item(i+1, result.smallImage, result.title));
			Log.i("Bazinga", result.title);
		}

	}

	public static Item GetbyId(int id) {

		for (Item item : Items) {
			if (item.Id == id) {
				return item;
			}
		}
		return null;
	}

}
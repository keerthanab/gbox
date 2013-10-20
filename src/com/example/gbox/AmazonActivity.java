package com.example.gbox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.gbox.FriendsActivity.Data;
import com.facebook.Session;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AmazonActivity extends Activity {
	String[] names = null;
	String[] categories = null; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amazon);
		String friendID = getIntent().getStringExtra("friendID");
		Log.i("AmazonActivity", friendID);
		try {
			getFriendsData(Data.likes, friendID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getFriendsData(Data d, String friendId) throws JSONException {
		String str = Session.getActiveSession().getAccessToken();
		JsonHttpResponseHandler handler = null;
		if (d == Data.movies) {
			str = friendId + "/movies?access_token=" + str;
		} else if (d == Data.activities) {
			str = friendId + "/activities?access_token=" + str;
		} else if (d == Data.books) {
			str = friendId + "/books?access_token=" + str;
		} else if (d == Data.interests) {
			str = friendId + "/interests?access_token=" + str;
		} else if (d == Data.likes) {
			str = friendId + "/likes?access_token=" + str;
		} else if (d == Data.music) {
			str = friendId + "/music?access_token=" + str;
		} else if (d == Data.television) {
			str = friendId + "/television?access_token=" + str;
		}
		Log.i("FriendsActivity", str);
		handler = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject activities) {
				Log.i("FriendsActivity", "json returned");
				try {
					JSONArray friendsArray = (JSONArray) activities.get("data");
					names = new String[friendsArray.length()];
					categories = new String[friendsArray.length()];
					for (int i = 0; i < friendsArray.length(); i++) {
						try {
							names[i] = friendsArray
									.getJSONObject(i).getString("name");
							categories[i] = friendsArray
									.getJSONObject(i).getString("category");
							// TODO: INSERT CODE HERE
							
						} catch (JSONException e) {
							Log.i("AmazonActivity", e.getMessage());
						}
					}
					
					AllInterestCollection allLikes = new AllInterestCollection();
					int size = names.length;
					for(int i=0; i<size; i++)
						allLikes.addInterest(new InterestItem(names[i],categories[i],""));
						
					allLikes.finalizeSearch();
					String[] query = allLikes.getSuggestions();
					String textString = "";
					for (int i = 0; i< query.length; i++) {
						Log.i("AmazonActivity",query[i]);
						textString += query[i] + "\n";
					}
					TextView text = (TextView)findViewById(R.id.resultText);
					text.setText(textString);
					
				} catch (JSONException e) {
					Log.i("FriendsActivity", e.getMessage());
				}

			}
		};
		HTTPClient.get(str, null, handler);
	}
}

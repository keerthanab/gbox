package com.example.gbox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.facebook.Session;
import com.facebook.widget.LoginButton;
import com.loopj.android.http.JsonHttpResponseHandler;

public class FriendsActivity extends Activity {
	private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

	private LoginButton buttonLogout;

	public enum Data {
		movies, music, likes, interests, books, television, activities
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		buttonLogout = (LoginButton) findViewById(R.id.authButton);
		Session session = Session.getActiveSession();
		if (session.isOpened()) {
			buttonLogout.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					onClickLogout();
				}
			});
		}
		if (session.isOpened()) {
			Log.i("FriendsActivity",
					URL_PREFIX_FRIENDS + session.getAccessToken());
			try { 
				//getFriends();
				getFriendsData(Data.movies, "605512675");//pass in a friend's id
			} catch (JSONException e) {
				Log.i("FriendsActivity", e.getMessage());
			}
		}

	}

	public void getFriends() throws JSONException {
		String str = "me/friends?access_token="
				+ Session.getActiveSession().getAccessToken();
		Log.i("FriendsActivity", str);
		HTTPClient.get(str, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject friends) {
				Log.i("FriendsActivity", "json returned");
				try {
					JSONArray friendsArray = (JSONArray) friends.get("data");
					for (int i = 0; i < friendsArray.length(); i++) {
						try {
							Log.i("FriendsActivity", friendsArray
									.getJSONObject(i).toString());
							// TODO: INSERT CODE HERE
						} catch (JSONException e) {
							Log.i("FriendsActivity", e.getMessage());
						}
					}

				} catch (JSONException e) {
					Log.i("FriendsActivity", e.getMessage());
				}

			}
		});
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
					for (int i = 0; i < friendsArray.length(); i++) {
						try {
							Log.i("FriendsActivity", friendsArray
									.getJSONObject(i).toString());
							// TODO: INSERT CODE HERE
						} catch (JSONException e) {
							Log.i("FriendsActivity", e.getMessage());
						}
					}
				} catch (JSONException e) {
					Log.i("FriendsActivity", e.getMessage());
				}

			}
		};
		HTTPClient.get(str, null, handler);
	}
	
    public void displayListView(View view) 
    {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

	public void onClickLogout() {
		Session session = Session.getActiveSession();
		if (!session.isClosed()) {
			session.closeAndClearTokenInformation();
			startActivity(new Intent(this, MainActivity.class));
			finish();
		}
	}

}

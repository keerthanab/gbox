package com.example.gbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		buttonLogout = (LoginButton) findViewById(R.id.authButton);
		buttonLogout.setText(R.string.logout);
		buttonLogout.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				onClickLogout();
			}
		});
		Session session = Session.getActiveSession();
		if (session.isOpened()) {
			Log.i("FriendsActivity",
					URL_PREFIX_FRIENDS + session.getAccessToken());
			try {
				getFriends();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.i("FriendsActivity", e.getMessage());
			}
			// new JSONTask().execute();
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
							Log.i("FriendsActivity", friendsArray.getJSONObject(i)
									.toString());
							//TODO: INSERT CODE HERE
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Log.i("FriendsActivity",e.getMessage());
						}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.i("FriendsActivity",e.getMessage());
				}

			}
		});
	}

	public void onClickLogout() {
		Session session = Session.getActiveSession();
		if (!session.isClosed()) {
			session.closeAndClearTokenInformation();
		}
	}

}

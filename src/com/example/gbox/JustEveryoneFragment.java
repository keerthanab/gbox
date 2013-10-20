package com.example.gbox;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gbox.FriendsActivity.Data;
import com.facebook.Session;
import com.loopj.android.http.JsonHttpResponseHandler;

public class JustEveryoneFragment extends Fragment {
	private List<String> friendList;
	private List<String> friendIDList;
	private ListView list;
	View inflatedView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View rootView = inflater.inflate(R.layout.fragment_just_everyone,
				container, false);
		inflatedView = rootView;

		try {
			getFriends();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rootView;
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
					friendList = new ArrayList<String>();
					friendIDList = new ArrayList<String>();
					for (int i = 0; i < friendsArray.length(); i++) {
						try {
//							Log.i("FriendsActivity", friendsArray
//									.getJSONObject(i).toString());
							friendList.add(friendsArray.getJSONObject(i)
									.getString("name"));
							friendIDList.add(friendsArray.getJSONObject(i)
									.getString("id"));
						} catch (JSONException e) {
							Log.i("FriendsActivity", e.getMessage());
						}
					}
					CreateListView();
				} catch (JSONException e) {
					Log.i("FriendsActivity", e.getMessage());
				}

			}
		});
	}
	

	private void CreateListView() {
		Log.i("JustEveryone", "Friends: " + friendList.size());
		list = (ListView) inflatedView
				.findViewById(R.id.just_everyone_listview);
		list.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, friendList));
		list.setOnItemClickListener(new ListView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// Object o = list.getItemAtPosition(position);
				// String str="HOWDY";//As you are using Default String Adapter
				// Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
				String friendID = friendIDList.get(position);
				Intent i = new Intent(getActivity(), AmazonActivity.class);
				i.putExtra("friendID", friendID);
				startActivity(i);
			
				
			}
		});
		// list.setOnItemSelectedListener(new OnItemSelectedListener() {
		//
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// switch( position )
		// {
		//
		// }
		// }
		//
		// });
	}

}

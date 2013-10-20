package com.example.gbox;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CloseFriendFragment extends Fragment {

	private List<String> friendList;
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
		View rootView = inflater.inflate(R.layout.fragment_close_friend,
				container, false);
		inflatedView = rootView;
		CreateListView();
		return rootView;
	}

	private void CreateListView() {
		friendList = new ArrayList<String>();
		friendList.add("Phone");

		list = (ListView) inflatedView.findViewById(R.id.close_friend_listview);
		list.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, friendList));

	}

}

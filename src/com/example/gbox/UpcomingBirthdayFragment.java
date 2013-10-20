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

public class UpcomingBirthdayFragment extends Fragment {
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
		View rootView = inflater.inflate(R.layout.fragment_upcoming_birthday,
				container, false);
		inflatedView = rootView;
		CreateListView();
		return rootView;
	}

	private void CreateListView() {
		friendList = new ArrayList<String>();
		friendList.add("Google");

		list = (ListView) inflatedView.findViewById(R.id.upcoming_birthday_listview);
		list.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, friendList));

	}

}

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

public class JustEveryoneFragment extends Fragment {
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
		View rootView = inflater.inflate(R.layout.fragment_just_everyone,
				container, false);
		inflatedView = rootView;
		CreateListView();
		return rootView;
	}

	private void CreateListView() {
		friendList = new ArrayList<String>();
		friendList.add("Nexus");

		list = (ListView) inflatedView.findViewById(R.id.just_everyone_listview);
		list.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, friendList));

	}

}

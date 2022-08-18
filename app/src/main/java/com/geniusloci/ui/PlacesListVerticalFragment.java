package com.geniusloci.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geniusloci.R;
import com.geniusloci.binding.PlacesListAdapter;
import com.geniusloci.db.AppDatabase;
import com.geniusloci.db.DataRepository;

import java.io.IOException;

public class PlacesListVerticalFragment extends Fragment {
	private PlacesListAdapter adapter;
	private DataRepository repository;
	private final String TAG = "PlacesListVFragment";
	protected RecyclerView.LayoutManager mLayoutManager;

	/*
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private String mParam1;
	private String mParam2;
	*/

	public PlacesListVerticalFragment() {

	}

	/*
	public static PlacesListVerticalFragment newInstance(String param1, String param2) {
		PlacesListVerticalFragment fragment = new PlacesListVerticalFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			repository = DataRepository.getInstance(AppDatabase.getInstance(getContext()));
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}


		/*
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_places_list_vertical, container, false);
		mLayoutManager = new LinearLayoutManager(getActivity());
		RecyclerView rv = rootView.findViewById(R.id.places_list);
		adapter = new PlacesListAdapter(repository.loadAllPlaces()); //repository.loadPlacesEmptyList()
		rv.setLayoutManager(mLayoutManager);
		rv.setAdapter(adapter);
		return rootView;
	}

	private MainActivity getMainActivity(){
		return (MainActivity)getActivity();
	}
}
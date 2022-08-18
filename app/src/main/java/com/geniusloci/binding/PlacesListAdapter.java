package com.geniusloci.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geniusloci.R;
import com.geniusloci.db.entities.Place;

import java.util.List;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder> {
	private List<Place> dataset;

	public PlacesListAdapter(List<Place> ds){
		dataset = ds;
	}

	@NonNull
	@Override
	public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
		return new PlaceViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
		final Place p = dataset.get(position);
		holder.getNameTextView().setText(p.getNames());
		holder.getAbstractTextView().setText(p.getAbstracts());
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}

	static class PlaceViewHolder extends RecyclerView.ViewHolder {
		private final TextView nameTextView;
		private final TextView abstractTextView;

		public PlaceViewHolder(View view) {
			super(view);
			nameTextView = view.findViewById(R.id.name);
			abstractTextView = view.findViewById(R.id.abstrct);
		}

		public TextView getNameTextView() {
			return nameTextView;
		}

		public TextView getAbstractTextView() {
			return abstractTextView;
		}
	}
}

package com.geniusloci.binding;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

	@NonNull
	@Override
	public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	static class PlaceViewHolder extends RecyclerView.ViewHolder {
		public PlaceViewHolder(View view) {
			super(view);
		}
	}
}

package com.geniusloci.binding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geniusloci.R;
import com.geniusloci.db.entities.Place;
import com.geniusloci.helpers.StringHelper;

import java.util.List;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder> {
	private final List<Place> dataset;

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
		final String lang = "CZ";
		final String nm = StringHelper.getStringForLanguage(p.getNames(), lang);
		final String abst = StringHelper.getStringForLanguage(p.getAbstracts(), lang);
		holder.getNameTextView().setText(nm);
		holder.getAbstractTextView().setText(abst);
		Bitmap img = BitmapFactory.decodeByteArray(p.getImage(), 0, p.getImage().length);
		holder.getImage().setImageBitmap(img);
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}

	static class PlaceViewHolder extends RecyclerView.ViewHolder {
		private final TextView nameTextView;
		private final TextView abstractTextView;
		private final ImageView imageView;

		public PlaceViewHolder(View view) {
			super(view);
			nameTextView = view.findViewById(R.id.name);
			abstractTextView = view.findViewById(R.id.abstrct);
			imageView = view.findViewById(R.id.imageView);
		}

		public TextView getNameTextView() {
			return nameTextView;
		}

		public TextView getAbstractTextView() {
			return abstractTextView;
		}

		public ImageView getImage() {
			return imageView;
		}
	}
}

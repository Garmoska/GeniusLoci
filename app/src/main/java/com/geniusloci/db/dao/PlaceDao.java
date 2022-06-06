package com.geniusloci.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geniusloci.db.entities.Place;

import java.util.List;

@Dao
public interface PlaceDao {
	@Query("SELECT * FROM places")
	LiveData<List<Place>> loadAll();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(List<Place> places);
}

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

	@Query("SELECT COUNT(id) FROM places")
	int getPlacesCount();

	//TODO set the real request
	@Query("SELECT * FROM places where latitude=:latitude and longitude=:longitude")
	LiveData<List<Place>> loadByCoordinates(float latitude, float longitude);
}

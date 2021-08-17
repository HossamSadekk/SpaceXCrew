package com.example.spacexcrew.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.spacexcrew.response.CrewResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface CrewDao {
    @Query("SELECT * FROM crew")
    Flowable<List<CrewResponse>> getAllData();

    @Insert
    Completable addCrewData(List<CrewResponse> crewList);

    @Query("DELETE FROM crew")
    Completable deleteCrewData();
}

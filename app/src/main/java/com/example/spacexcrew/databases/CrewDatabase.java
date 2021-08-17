package com.example.spacexcrew.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.spacexcrew.response.CrewResponse;

@TypeConverters(Converter.class)
@Database(entities = {CrewResponse.class}, version = 1)
public abstract class CrewDatabase extends RoomDatabase {
    private static CrewDatabase crewDatabase;

    public static synchronized CrewDatabase getCrewDatabase(Context context) {
        if (crewDatabase == null) {
            crewDatabase = Room
                    .databaseBuilder(context, CrewDatabase.class, "crew_db")
                    .build();
        }
        return crewDatabase;
    }

    public abstract CrewDao crewDao();
}

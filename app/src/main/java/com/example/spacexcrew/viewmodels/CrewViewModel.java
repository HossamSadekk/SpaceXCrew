package com.example.spacexcrew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.spacexcrew.response.CrewResponse;
import com.example.spacexcrew.repositories.CrewRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class CrewViewModel extends AndroidViewModel {

    private CrewRepository crewRepository;

    public CrewViewModel(Application application)
    {
        super(application);
        crewRepository = new CrewRepository(application);
    }

    public LiveData<List<CrewResponse>> getCrew()
    {
       return crewRepository.getCrew();
    }

    public Flowable<List<CrewResponse>> loadCrewData()
    {
        return crewRepository.loadCrewData();
    }

    public Completable addCrewData(List<CrewResponse> crewList)
    {
        return crewRepository.addCrewData(crewList);
    }

    public Completable deleteCrewData()
    {
        return crewRepository.deleteCrewData();
    }

}

package com.example.spacexcrew.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.spacexcrew.databases.CrewDatabase;
import com.example.spacexcrew.response.CrewResponse;
import com.example.spacexcrew.network.ApiClient;
import com.example.spacexcrew.network.ApiService;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CrewRepository {
    public static String TAG = "Crew";
    private ApiService apiService;
    private CrewDatabase crewDatabase;

    public CrewRepository(Application application) {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        crewDatabase = CrewDatabase.getCrewDatabase(application);
    }


    public LiveData<List<CrewResponse>> getCrew() {
        final MutableLiveData<List<CrewResponse>> crewData = new MutableLiveData<>();

        Single<List<CrewResponse>> observable = apiService.getCrew()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        SingleObserver<List<CrewResponse>> singleObserver = new SingleObserver<List<CrewResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<CrewResponse> crewResponseList) {
                crewData.setValue(crewResponseList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error-->" + e);
            }
        };
        observable.subscribe(singleObserver);

        return crewData;
    }

    public Flowable<List<CrewResponse>> loadCrewData() {
        return crewDatabase.crewDao().getAllData();
    }

    public Completable addCrewData(List<CrewResponse> crewList) {
        return crewDatabase.crewDao().addCrewData(crewList);
    }

    public Completable deleteCrewData() {
        return crewDatabase.crewDao().deleteCrewData();
    }
}

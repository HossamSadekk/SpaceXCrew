package com.example.spacexcrew.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.spacexcrew.R;
import com.example.spacexcrew.adapters.CrewAdapter;
import com.example.spacexcrew.databinding.ActivityMainBinding;
import com.example.spacexcrew.dialogs.CheckInternetDialog;
import com.example.spacexcrew.listeners.CrewListener;
import com.example.spacexcrew.response.CrewResponse;
import com.example.spacexcrew.utlis.NetworkChangeReciever;

import com.example.spacexcrew.viewmodels.CrewViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements CrewListener {

    private ActivityMainBinding mainBinding;
    private CrewViewModel crewViewModel;
    private CrewAdapter crewAdapter;
    private NetworkChangeReciever broadcastReceiver;
    private CompositeDisposable compositeDisposable =  new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Handle Delete Button
        mainBinding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compositeDisposable.add(
                        crewViewModel.deleteCrewData()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(()->{Log.d("Crew","done delete");}
                                )
                            );
                mainBinding.setDelete(false);
            }
        });

        doIntialization();

    }

    private void doIntialization() {
        // View Model
        crewViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CrewViewModel.class);
        // Broadcast
        broadcastReceiver = new NetworkChangeReciever();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        //provide list to adapter
        crewAdapter = new CrewAdapter(this);
        //provide adapter to recycler view
        mainBinding.recyclerView.setAdapter(crewAdapter);

    }

    private void getCrewFromAPI() {
        mainBinding.setIsLoading(true);

        crewViewModel.getCrew().observe(this, new Observer<List<CrewResponse>>() {
            @Override
            public void onChanged(List<CrewResponse> crewResponseList) {
                if (crewResponseList.size() != 0) {
                    mainBinding.setIsLoading(false);
                    crewAdapter.submitList(crewResponseList);
                    compositeDisposable.add(
                            crewViewModel.addCrewData(crewResponseList).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(() -> {
                                                Log.d("Crew", "done add");
                                            }
                                            , e -> Log.d("Crew", "add" + e))
                    );
                }
            }
        });
    }

    @Override
    public void onClicked(CrewResponse crewResponse) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(crewResponse.getWikipedia()));
        startActivity(i);
    }

    // when device is connected to internet
    @Override
    public void connected() {
        if (crewAdapter.getCurrentList().size() == 0) {
            getCrewFromAPI();
        }
    }

    // when device is disconnected to internet
    @Override
    public void disconnected() {
        CheckInternetDialog checkInternetDialog = new CheckInternetDialog();
        checkInternetDialog.show(getSupportFragmentManager(),"dialog_created");
    }

    // refresh button
    @Override
    public void refreshBtn() {
        mainBinding.setDelete(true);
        mainBinding.setIsLoading(true);
        compositeDisposable.add(
                crewViewModel.loadCrewData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(crewResponseList -> {
                            mainBinding.setIsLoading(false);
                            crewAdapter.submitList(crewResponseList);
                        }
                )
        );
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
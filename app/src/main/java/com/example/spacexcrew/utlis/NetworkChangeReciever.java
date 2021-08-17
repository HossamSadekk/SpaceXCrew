package com.example.spacexcrew.utlis;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.spacexcrew.R;
import com.example.spacexcrew.databinding.CheckInternetDialogBinding;
import com.example.spacexcrew.listeners.CrewListener;

public class NetworkChangeReciever extends BroadcastReceiver {
    private CheckInternetDialogBinding checkInternetDialogBinding;
    private CrewListener crewListener;
    private AlertDialog dialog;
    @Override
    public void onReceive(Context context, Intent intent) {
        crewListener = (CrewListener) context;
        if(!CheckInternet.isConnectedToInternet(context))
        {
           crewListener.disconnected();
            Toast.makeText(context,"You Are Offline",Toast.LENGTH_LONG).show();
        }
        else
            {
                crewListener.connected();
                Toast.makeText(context,"You Are Online",Toast.LENGTH_LONG).show();
            }
    }
}

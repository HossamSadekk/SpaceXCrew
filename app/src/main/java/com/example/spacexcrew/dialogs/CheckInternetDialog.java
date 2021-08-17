package com.example.spacexcrew.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.example.spacexcrew.R;
import com.example.spacexcrew.databinding.CheckInternetDialogBinding;
import com.example.spacexcrew.listeners.CrewListener;

public class CheckInternetDialog extends AppCompatDialogFragment {
    private CrewListener crewListener;
    private CheckInternetDialogBinding checkInternetDialogBinding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        checkInternetDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.check_internet_dialog, null, false);
        crewListener = (CrewListener) getActivity();
        builder.setView(checkInternetDialogBinding.getRoot());
        final AlertDialog dialog = builder.create();
        checkInternetDialogBinding.refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                crewListener.refreshBtn();
            }
        });
        return dialog;

    }
}

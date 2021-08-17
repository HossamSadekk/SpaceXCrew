package com.example.spacexcrew.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spacexcrew.R;
import com.example.spacexcrew.databinding.CrewItemBinding;
import com.example.spacexcrew.listeners.CrewListener;
import com.example.spacexcrew.response.CrewResponse;


public class CrewAdapter extends ListAdapter<CrewResponse,CrewAdapter.CrewViewHolder> {
private CrewListener crewListener;

    public CrewAdapter(CrewListener crewListener) {
        super(DIFF_CALLBACK);
        this.crewListener = crewListener;
    }

    private static final DiffUtil.ItemCallback<CrewResponse> DIFF_CALLBACK = new DiffUtil.ItemCallback<CrewResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull CrewResponse oldItem, @NonNull CrewResponse newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CrewResponse oldItem, @NonNull CrewResponse newItem) {
            return oldItem.getName().equals(newItem.getName())
                    && oldItem.getAgency().equals(newItem.getAgency())
                    && oldItem.getImage().equals(newItem.getImage());
        }
    };

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CrewItemBinding crewItemBinding = DataBindingUtil.inflate(inflater, R.layout.crew_item,parent,false);
        return new CrewViewHolder(crewItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class CrewViewHolder extends RecyclerView.ViewHolder {
        private CrewItemBinding crewItemBinding;
        public CrewViewHolder(@NonNull CrewItemBinding crewItemBinding) {
            super(crewItemBinding.getRoot());
            this.crewItemBinding = crewItemBinding;
        }
        public void bind(CrewResponse crewResponse)
        {
            crewItemBinding.setCrew(crewResponse);
            crewItemBinding.executePendingBindings();
            crewItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    crewListener.onClicked(crewResponse);
                }
            });
        }
    }
}

package com.example.spacexcrew.listeners;

import com.example.spacexcrew.response.CrewResponse;

public interface CrewListener {
    void onClicked(CrewResponse crewResponse);

    void refreshBtn();

    void connected();

    void disconnected();
}

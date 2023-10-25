package com.playerApplication.service;


import com.playerApplication.model.Player;

import java.util.Map;

public interface PlayerService {

    Map<String, Player> getAllPlayers();

    Player getPlayerById(String playerId);
}

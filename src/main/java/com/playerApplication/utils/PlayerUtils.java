package com.playerApplication.utils;

import com.playerApplication.model.Player;

public class PlayerUtils {
    public static Player getPlayer(String playerId, String[] row) {
        Player player = new Player();

        player.setPlayerID(playerId);
        player.setBirthYear(row[1]);
        player.setBirthMonth(row[2]);
        player.setBirthDay(row[3]);
        player.setBirthCountry(row[4]);
        player.setBirthState(row[5]);
        player.setBirthCity(row[6]);
        player.setDeathYear(row[7]);
        player.setDeathMonth(row[8]);
        player.setDeathDay(row[9]);
        player.setDeathCountry(row[10]);
        player.setDeathState(row[11]);
        player.setDeathCity(row[12]);
        player.setNameFirst(row[13]);
        player.setNameLast(row[14]);
        player.setNameGiven(row[15]);
        player.setWeight(row[16]);
        player.setHeight(row[17]);
        player.setBats(row[18]);
        player.setPlayerThrows(row[19]);
        player.setDebut(row[20]);
        player.setFinalGame(row[21]);
        player.setRetroID(row[22]);
        player.setBbrefID(row[23]);
        return player;
    }
}

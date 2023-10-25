package com.playerApplication.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.playerApplication.controller.LoggerController;
import com.playerApplication.error.PlayerNotFoundException;
import com.playerApplication.model.Player;
import com.playerApplication.utils.CsvValidation;
import com.playerApplication.utils.PlayerUtils;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    //This Expression is used to split the row in the csv file considering the last leading empty fields to be store as empty in Array of Strings
    final static String EXPRESSION = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    private Map<String, Player> playersMap = new HashMap<>();

    @Autowired
    LoggerController logger;

    @PostConstruct
    public void init() throws Exception {
        loadPlayersData();
    }

    /**
     * This function responsible to load the player.csv file under resources package once the application starts and use in-memory to store the data
     * @throws Exception & IOException in case of failure during loading the csv file.
     */
    private void loadPlayersData() throws Exception {

        final int NUM_OF_FIELDS = 24;
        AtomicInteger lineCounter = new AtomicInteger(0);

        logger.info("Loading player.csv file is started");

       // try (BufferedReader reader = new BufferedReader(new FileReader("/player.csv"))) {
        try (InputStream inputStream = getClass().getResourceAsStream("/player.csv")) {
            if (inputStream == null) {
                throw new Exception("File is empty or not found");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line;
            boolean isHeader = false;

            while ((line = reader.readLine()) != null) {

                lineCounter.incrementAndGet();

                String[] row = line.split(EXPRESSION,-1);

                //Skip Header
                if (!isHeader) {
                    if(!CsvValidation.isCSVHeaderValid(row, NUM_OF_FIELDS))
                        throw new Exception("File header is not correct");
                    isHeader = true;
                    continue;
                }

                if (!CsvValidation.isCSVDataValid(row, NUM_OF_FIELDS)) {
                    logger.info("Error in file structure line "+ lineCounter.get());
                    throw new Exception("File structure is not correct");
                }

                String playerId = row[0];
                Player player = PlayerUtils.getPlayer(playerId, row);

                playersMap.put(playerId, player);
            }
        } catch (IOException e) {
            logger.info("Error parsing csv file, line: "+lineCounter.get());
            throw new IOException(e.getMessage());
        }

        logger.info("Parsing file is ended successfully");
    }


    @Override
    public Map<String, Player> getAllPlayers() {
        return playersMap;
    }

    @SneakyThrows
    @Override
    public Player getPlayerById(String playerId){

        if(playerId == null || playerId.isEmpty())
            return null;

        Player playerData = playersMap.get(playerId);

        if (playerData == null) {
            logger.info("Player " + playerId + " is not found");
            throw new PlayerNotFoundException("Player not found with id: " + playerId);
        }

        return playerData;
    }
}

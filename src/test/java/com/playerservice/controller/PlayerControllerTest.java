package com.playerservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.playerApplication.controller.LoggerController;
import com.playerApplication.controller.PlayerController;
import com.playerApplication.error.PlayerNotFoundException;
import com.playerApplication.model.Player;
import com.playerApplication.service.PlayerService;
import com.playerApplication.utils.PlayerUtils;
import com.sun.jdi.InternalException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PlayerControllerTest.class})
public class PlayerControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    final String pathALL = "/api/players";
    final String pathPlayer = "/api/player";

    final String missingPath = "/api/player/";
    Map<String,Player> players = new HashMap(0);

    @Mock
    private PlayerService playerService;

    @Mock
    public LoggerController logger;


    @InjectMocks
    private PlayerController playerController;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();


        String[] row1 = new String[] {"abbated01","1877","4","15","USA","PA","Latrobe","1957","1","6","USA","FL","Fort Lauderdale","Ed","Abbaticchio","Edward James","170","71","R","R","1897-09-04","1910-09-15","abbae101","abbated01"};
        Player player1 = PlayerUtils.getPlayer("abbated01",row1);

        String[] row2 = new String[] {"abbbbaaaa","1870","4","15","USA","PA","Latrobe","","","","","","","Ed","Abbaticchio","Edward James","170","71","R","R","1897-09-04","1910-09-15","abbae101","abbbbaaaa"};
        Player player2 = PlayerUtils.getPlayer("abbbbaaaa",row1);

        players.put("abbated01",player1);
        players.put("abbbbaaaa",player2);
    }

    @Test
    public void getAllRecordsSuccess() throws Exception{
        when(playerService.getAllPlayers()).thenReturn((players));
        String responseStr = mockMvc.perform(MockMvcRequestBuilders
                .get(pathALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Map<String, Player> jsonResponse = objectMapper.readValue(responseStr, new TypeReference<Map<String, Player>>() {});

        // Check if all expected keys are present in the JSON response
        for (String expectedKey : players.keySet()) {
            assertTrue(jsonResponse.containsKey(expectedKey), "Key '" + expectedKey + "' is missing in the JSON response.");
        }
    }

    @Test
    public void getPlayerByIdSuccess() throws Exception{
        final String playerId = "abbated01";

        when(playerService.getPlayerById(playerId)).thenReturn(players.get(playerId));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(pathPlayer+"/"+playerId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String responseStr = result.getResponse().getContentAsString();
        Player actualPlayer = objectMapper.readValue(responseStr, Player.class);

        assertNotNull(actualPlayer);
        assertEquals(actualPlayer.getPlayerID(), players.get(playerId).getPlayerID());
        assertEquals(actualPlayer.getBirthYear(), players.get(playerId).getBirthYear());
        assertEquals(actualPlayer.getBirthMonth(), players.get(playerId).getBirthMonth());
        assertEquals(actualPlayer.getBirthDay(), players.get(playerId).getBirthDay());
        assertEquals(actualPlayer.getBirthCountry(), players.get(playerId).getBirthCountry());
        assertEquals(actualPlayer.getBirthState(), players.get(playerId).getBirthState());
        assertEquals(actualPlayer.getBirthCity(), players.get(playerId).getBirthCity());
        assertEquals(actualPlayer.getDeathYear(), players.get(playerId).getDeathYear());
        assertEquals(actualPlayer.getDeathMonth(), players.get(playerId).getDeathMonth());
        assertEquals(actualPlayer.getDeathDay(), players.get(playerId).getDeathDay());
        assertEquals(actualPlayer.getDeathCountry(), players.get(playerId).getDeathCountry());
        assertEquals(actualPlayer.getDeathState(), players.get(playerId).getDeathState());
        assertEquals(actualPlayer.getDeathCity(), players.get(playerId).getDeathCity());
        assertEquals(actualPlayer.getNameFirst(), players.get(playerId).getNameFirst());
        assertEquals(actualPlayer.getNameLast(), players.get(playerId).getNameLast());
        assertEquals(actualPlayer.getNameGiven(), players.get(playerId).getNameGiven());
        assertEquals(actualPlayer.getWeight(), players.get(playerId).getWeight());
        assertEquals(actualPlayer.getHeight(), players.get(playerId).getHeight());
        assertEquals(actualPlayer.getBats(), players.get(playerId).getBats());
        assertEquals(actualPlayer.getPlayerThrows(), players.get(playerId).getPlayerThrows());
        assertEquals(actualPlayer.getDebut(), players.get(playerId).getDebut());
        assertEquals(actualPlayer.getFinalGame(), players.get(playerId).getFinalGame());
        assertEquals(actualPlayer.getRetroID(), players.get(playerId).getRetroID());
        assertEquals(actualPlayer.getBbrefID(), players.get(playerId).getBbrefID());
    }


    @Test
    public void getPlayerByIdNotFound() throws Exception{

        final String playerId = "notfound";

        when(playerService.getPlayerById(playerId)).thenThrow(new PlayerNotFoundException("Player not found with id: " + playerId));

        String responseStr = null;

        try {
               MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                 .get(pathPlayer+"/"+playerId)
                                 .contentType(MediaType.APPLICATION_JSON))
                                 .andExpect(status().isNotFound())
                                 .andReturn();
        } catch (Exception e) {
            //it should throw message to behave as expected
            assertTrue(e.getMessage().contains("Player not found with id: " + playerId));
        }

    }

    @Test
    public void getPlayerByIdInternalError() throws Exception{

        final String playerId = "notfound";

        when(playerService.getPlayerById(playerId)).thenThrow(new InternalException("Required path variable 'id' is not present."));

        String responseStr = null;

        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                            .get(missingPath)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andReturn();
        } catch (Exception e) {
            //it should throw message to behave as expected
            assertTrue(e.getMessage().contains("Required path variable 'id' is not present."));
        }

    }


}

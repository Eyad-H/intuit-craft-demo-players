# **Intuit Player Microservice Assignment**

## **Introduction:**

Implementing microservice which serves the contents of player.csv through REST API.

- <b>Player Service</b> --> Implementation of the busines logic as a serivce layer, moreover, it is loading the player.csv file during program startup using Spring framework in java
- <b>Player Model</b> --> Implementation of the data layer
- <b>Player Controller</b> --> Implementation of the REST APIs as a represantation layer
- <b> Player Map DS</b> --> Used as in-mermory for the player.csv data


## **Assumptions:**
1-File player.csv header structure is fixed and will not be changed, adding/removing headers will lead for error <br>
2-The player.csv file should be loaded once the application starts <br>
3-The format of the output of the REST APIs is JSON <br>
4-Null/Empty values will not be presented in the json response

## Prerequisites:
- Docker 24.0.6
- Maven 3.9.2
- SpringBoot 3.1.4
- Java 17.0.8
- Postman
- Port 8080 to be used

## Run the project throught a Docker using the following steps:
- Open terminal and clone the project:  git clone https://github.com/Eyad-H/intuit-craft-demo-players.git
- cd to the resources folder intuit-craft-demo-players\src\main\resources and add the player.csv file
- Navigate back to intuit-craft-demo-players folder
- run: mvn clean install
- run: docker build -t intuitcraftdemoplayers/playerservice:0.0.1 .
- run: docker images
- run: docker run -p 8080:8080 -it intuitcraftdemoplayers/playerservice:0.0.1  /bin/sh ${IMAGE ID}
 <!-- --> NOTE: replace the ${IMAGE ID} with the value from previouse step.


## Testing the project:

1- Testing getAllPlayers API
- URL: localhost:8080/api/players <br>
  Status: Successfull

2- Testing getPlayerById API
- URL: localhost:8080/api/player/abbated01 <br>
  Status: Successfull return the expected player <br><br>
  
- URL: localhost:8080/api/player/simpsma01 <br>
  Status: Successfull return the expected player without showing the empty fields <br><br>

- URL: localhost:8080/api/player/notfound <br>
  Status: Failed as Not Found <br><br>

- URL: localhost:8080/api/player/ <br>
  Status: Failed as Internal Server Error due to missing id as input <br><br>

3-Testing non existing path
- URL:localhost:8080/api/player <br>
  Status: Failed as path Not Found


## Stop the project through a Docker using the following steps:
- Open terminal
- run: docker ps
- run: docker stop ${CONTAINED ID}
  
  <!-- --> NOTE: replace the ${CONTAINED ID} with the value from previouse step 

## What would be done if I have more time?
- <b>Implementing Security Authorization, Authentication & Encryption</b> - To make my system secured
- <b>Using message Queue (Rabbit)</b> - To push the REST API requests into it and work asynchronizly
- <b> Implementing Consumer Engine</b> -  To consume the messages from the Rabbit message queue 
- <b> Use Relational databse</b> - For example MySQL DB to store the data inside it and support huge files in case the application might required to support huge files



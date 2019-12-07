![alt text](https://github.com/IvanDacquel/LEZDODISBOIS/blob/master/GameFiles/PNG-photos/LOGO.png "GAME")<br />

## Members

+Figueroa, Francis James <br />
+Dacquel, Ivan Amadeus <br />
+Dollentes, Michael Anthony <br />
+Salcedo, Jan Raymond <br />
+Villaro, Paul

## Specifications

Programming language: **Java** <br />
Protocol: **UDP** <br />
Import Socket, ServerSocket in Java <br />
Github: https://github.com/IvanDacquel/LEZDODISBOIS

## Game Layout and Protocol

### I. Basic Game Layout

1. Server will create a game instance that will require n players (min. 3 players, max 13)

2. Once the number of players matches the game quota, the game will start

3. Server will generate n sets of 4 cards, each set containing the same value, but with different suits (ex. 3 of Clubs, 3 of Spades, 3 of Hearts, and 3 of Diamond)

4. Once the server generates all the cards needed, the server will shuffle the cards and distribute them evenly to the clients

5. Once the clients have received their cards, the server will send a signal that indicates that each client must choose a card to pass to the next player

6. A 3-second timer will start, and each client must choose a card to pass during the time limit. If the client fails to choose a card within 3 seconds, the first card in their hand will be chosen as the default card to pass

7. Once the timer is up, the chosen cards will be sent to the server for redistribution. The redistribution process works by giving the card of a client to the one next to the client in a clockwise motion

8. Once the clients see their new set of cards, process 6-7 will repeat until one or more client/s has/have 4 equal cards. Those clients will receive a notification that they should quickly press “Enter”

9. Once a client presses “Enter”, the server will broadcast to the rest of the clients that they should press “Enter” quickly

10. The server will send a list of clients, ranked by who pressed “Enter” quicker

### II. Communication Protocol

1. Data Flow Diagram <br />
![alt text](https://github.com/IvanDacquel/LEZDODISBOIS/blob/master/datadiagramflow.png "Data Flow Diagram")<br />
2. Structure of the Data <br />
--PENDING--

### III. Wireframe
#### Sample of Login UI (Optional)<br />
+When the code is executed, a GUI will pop-up, asking for the user's Name, Sever ID, and Port<br />
+After filling up needed information, the UI will change into the game area<br />
![alt text](https://github.com/IvanDacquel/LEZDODISBOIS/blob/master/gameplay.png "Client log-in")<br />
#### Sample of Player's UI<br />
\*Optional<br />
\*\*Should be included<br />
![alt text](https://github.com/IvanDacquel/LEZDODISBOIS/blob/master/login.png "Game interface")<br />

## Designations
  
#### Signals: <br />
  +SC = Send Cards <br />
  +TM = Timer <br />
  
Player ID: 01,02,03,.....,12,13 <br />

#### Cards: <br />
  +Suites: C (Clubs), S (Spades), H (Hearts), D (Diamond) <br />
  +Values: A (Ace), 2, 3, 4, 5, 6, 7, 8, 9, T (Ten), J (Jack), Q (Queen), K (King) <br />
  +Sample Card Code: AC (Ace of Clubs) <br />

#### Code Pattern: <br />
##### Sample code: SC04KCAC3C2S <br />
+SC - Server Sends Cards <br />
+04 - Player ID <br />
+KCAC3C2S - Cards <br />


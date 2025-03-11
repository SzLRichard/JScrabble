# JScrabble

This is a recreation of the classic board game called [Scrabble](https://en.wikipedia.org/wiki/Scrabble).
It uses Java [swing](https://en.wikipedia.org/wiki/Swing_(Java)) components for visualizing the game's interface.

## How to play
Most of the standard Scrabble rules apply: 
- You must form valid words using the tiles given to you, taking into consideration the scores for each tile and the multipliers on the board.
- When placing a new word, it needs to connect to an existing word on the board.
- You may also decide to swap your tiles for other tiles in the tile bag, but you will not earn any points for that turn.

## Winning the game
When there are no tiles left in the tile bag, you will each get one more turn.
After that the game will end.
The player with the highest score wins. 
You can see the points displayed on the scoreboard.

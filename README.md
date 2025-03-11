# JScrabble

This is a recreation of a board game called [Scrabble](https://en.wikipedia.org/wiki/Scrabble).
It uses Java [swing](https://en.wikipedia.org/wiki/Swing_(Java)) components for visualizing the game's interface.

## How to play
Most of the standard Scrabble rules apply: 
- You have to form valid words using the tiles given to you, taking into consideration the scores the tiles give, and the multipliers on the board.
- When placing a new word, it needs to connect to an existing word on the board.
- You may also decide to swap your tiles for other tiles in the tile bag, but in this case you may not earn any points for this turn.

## Winning the game
When there are no tiles left in the tile bag, you will each get one more turn, after that the game will end.
The player with the most points will win, you can see the points displayed on the scoreboard.

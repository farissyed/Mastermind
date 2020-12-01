# **Mastermind Outline**

The current goal is to create a virtually playable version of the game Mastermind 
in which players can play as the codeguesser and the computer is the codemaker.  The 
program should have a fluid UI with smooth transitions and a playful game environment.  


#### Crucial Tasks:
**For the visual interface:**
1) Pick a grahical interface library
2) Create 3d version of game board
3) Create 3d versions of game pins (code pins and feedback pins) - 8 different colored code pins and 2 feedback pins
4) Menu Screen - The user should be able to pick between allowing duplicates in the code or not
The user should also be able to view their past game results.  
5) In Game Screen - Display a playable screen to player where they can drag the code pins to the area where they are guessing
6) When the user confirms a guess, the computer's feedback should come smoothly and gracefully with animations
7) You win/You lose screens with option to start a new game
8) The player should be able to quit the game
9) Player should be able to pause game and continue later


**For the gameplay:** 
1) Create classes to represent each of the code pins/feedback pins
2) Code to randomly generate a code when the user starts a game based on whether they want doubles or not
3) Code to check the player's guess and decide which feedback pins to display to the player
4) Keep track of the player's previous guesses
5) Store the player's results after the game( how many guesses it took to guess the code, win/lose rate)
6) Handle if player quits the game

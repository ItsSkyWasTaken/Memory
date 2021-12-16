# Simon
This program is a Java recreation of the classic Simon game.  The goal is simply to form as long of a sequence as you can.

## Settings
**Width**:  This sets the width of the grid (3-5; default 3).  
**Height**:  This sets the height of the grid (1-5; default 3).  
**Speed**:  This sets the rate of which the squares flash in BPM (60-240; default 128).  For example, a speed of 60 will give 60 flashes per minute or 1 flash per second, while the maximum speed of 240 will give 4 flashes per second (240 per minute).  
**Label Type**:  This sets the label type used on the squares (Options are "Letters and Numbers", "Numbers", or "No Labels";  default "Numbers").  The "Letters and Numbers" setting displays each square ID as a letter-number coordinate (e.g. "b3").

## Gameplay
After clicking "Start", a grid of the specified size appears.  One of the squares will then briefly turn blue.  The player then clicks on the square that turned blue.  After that, the same square flashes blue again, followed by another flash, possibly, but not necessarily, on the same square.  The player then clicks on the two squares that flashed, in the order in which they flashed (clicking on the same square twice if the square flashed twice).  For each round, one more square is added to the sequence until the player gets it wrong.  The final score is how many rounds the player got correct.

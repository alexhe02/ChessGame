Pieces is the parent class of each piece. 
Each class for a piece exists only to provide the stats and movement patterns.
Most of the code exists in the click method because then we can use the coordinates given by the mouseevent
The paint method alternates between the board and the attack sequence when necessary
First click selects piece to move
Second click moves piece
*Note*
If an invalid move is selected, the piece you wish to move must be selected again

Pawn's special ability does double damage to pawns
Knight's special ability does 1.5* damage to level 1 pieces
Bishop's special ability heals them for 30 life
Rook's special ability allows them to take 25 percent less damage for the entire battle
Queen's special ability allows them to take 25 percent less damage for the entire battle if it is being attacked
King's special ability allows it to charge its attack and deal more damage when it attacks
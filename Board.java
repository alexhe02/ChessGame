
/**
 * Abhinav Chowdavarapu and Alex He
 * 5/6/19
 */

import java.awt.*; 
import java.applet.*; 
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
public class Board extends Applet implements MouseListener {
    public static Pieces[][] boardstate = new Pieces[8][8];
    public static Pieces currentpiece;
    public static int currentx;
    public static int currenty;
    public static int newx;
    public static int newy;
    public static boolean turn;
    
    public static Pieces attackingPiece;
    public static Pieces defendingPiece;
    public static boolean attackTurn;
    public static boolean attacksequence;
    public boolean gamegoing = true;
    public static boolean newAttackSequence = true;
    public void init()
    {
        for (int i = 0; i < boardstate.length; i++)
        {
            boardstate[1][i] = new Pawns(false);
            boardstate[6][i] = new Pawns(true);
        }
        boardstate[0][1] = new Knight(false);
        boardstate[7][1] = new Knight(true);
        boardstate[0][6] = new Knight(false);
        boardstate[7][6] = new Knight(true);
        boardstate[0][0] = new Rook(false);
        boardstate[7][0] = new Rook(true);
        boardstate[0][7] = new Rook(false);
        boardstate[7][7] = new Rook(true);
        boardstate[0][2] = new Bishop(false);    
        boardstate[7][2] = new Bishop(true);
        boardstate[0][5] = new Bishop(false);
        boardstate[7][5] = new Bishop(true);
        boardstate[0][3] = new King(false);
        boardstate[7][3] = new King(true);
        boardstate[0][4] = new Queen(false);
        boardstate[7][4] = new Queen(true);
        addMouseListener(this);
        turn = false;
        attackTurn = false;
        attacksequence = false;
    }

    public void mouseClicked(MouseEvent m)
    {
        if(!attacksequence)
        {
            if(currentpiece == null)
            {
                currentx = m.getX()/60;
                currenty = m.getY()/60;
                currentpiece = boardstate[currentx][currenty];
                if(turn != currentpiece.type)
                {
                    currentpiece = null;
                }
            }
            else
            {
                newx = m.getX()/60;
                newy = m.getY()/60;
                if((boardstate[newx][newy] == null) || (boardstate[newx][newy] != null && boardstate[newx][newy].type != currentpiece.type))
                {
                    if(currentpiece instanceof Pawns)
                    {
                        if(!currentpiece.type)//checks type of pawn
                        {
                            if(boardstate[newx][newy] == null)//checks if moves to empty space
                            {
                                if((currentx == 1 && (newx - 2) == currentx && newy == currenty && boardstate[2][currenty] == null))//double move for first move
                                {
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else if(((newx - 1) == currentx && newy == currenty))//single move otherwise
                                {
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else//resets if invalid
                                {
                                    currentpiece = null;
                                }
                            }
                            else//if trying to take a piece
                            {
                                if(currenty == 0)//checks if top piece
                                {
                                    if((newx - 1) == currentx && (newy - 1) == currenty)//attack once ready
                                    {//checks if piece is diagonal below to take
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {//resets otherwise
                                        currentpiece = null;
                                    }
                                }
                                else if (currenty == 7)//checks if bottom piece
                                {
                                    if((newx - 1) == currentx && (newy + 1) == currenty)//attack once ready
                                    {//checks if piece is diagonal above to take
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {//resets otherwise
                                        currentpiece = null;
                                    }
                                }
                                else//if not top or bottom pawn
                                {
                                    if(((newx - 1) == currentx && (newy + 1) == currenty) || ((newx - 1) == currentx && (newy - 1) == currenty))//attack once ready
                                    {//checks if piece is diagonal above to take
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {//resets otherwise
                                        currentpiece = null;
                                    }
                                }
                            }
                        }
                        else
                        {//repeats with black pieces
                            if(boardstate[newx][newy] == null)
                            {
                                if((currentx == 6 && (newx + 2) == currentx && newy == currenty) || ((newx + 1) == currentx && newy == currenty))
                                {
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else
                                {
                                    currentpiece = null;
                                }
                            }
                            else
                            {
                                if(currenty == 0)
                                {
                                    if((newx + 1) == currentx && (newy - 1) == currenty)//attack once ready
                                    {
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {
                                        currentpiece = null;
                                    }
                                }
                                else if (currenty == 7)
                                {
                                    if((newx + 1) == currentx && (newy + 1) == currenty)//attack once ready
                                    {
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {
                                        currentpiece = null;
                                    }
                                }
                                else
                                {
                                    if(((newx + 1) == currentx && (newy + 1) == currenty) || ((newx + 1) == currentx && (newy - 1) == currenty))
                                    {
                                        attacksequence = true;
                                        boardstate[currentx][currenty] = null;
                                        
                                        turn = !turn;
                                    }
                                    else
                                    {
                                        currentpiece = null;
                                    }
                                }
                            }
                        }
                    }
                    else if(currentpiece instanceof Knight)
                    {
                        if((Math.abs(currentx-newx) == 2 && Math.abs(currenty-newy) == 1) || (Math.abs(currentx-newx) == 1 && Math.abs(currenty - newy) == 2))
                        {//checks if knight is moving in an l shape
                            if(boardstate[newx][newy] == null)//just moves if empty
                            {
                                boardstate[newx][newy] = currentpiece;
                                boardstate[currentx][currenty] = null;
                                currentpiece = null;
                                turn = !turn;
                            }
                            else//attack once ready
                            {
                                attacksequence = true;
                                boardstate[currentx][currenty] = null;
                                
                                turn = !turn;
                            }
                        }
                        else
                        {//resets otherwise
                            currentpiece = null;
                        }
                    }
                    else if(currentpiece instanceof Bishop)
                    {
                        if((Math.abs(currentx-newx) == Math.abs(currenty-newy)))
                        {
                            boolean unobstructed = true;
                            //next couple loops check if the bishop is unobstructed
                            if(currentx < newx && currenty < newy)
                            {
                                for(int i = currentx + 1, j = currenty + 1; i < newx; i++, j++)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currentx > newx && currenty > newy)
                            {
                                for(int i = currentx - 1, j = currenty - 1; i > newx; i--, j--)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currentx > newx && currenty < newy)
                            {
                                for(int i = currentx - 1, j = currenty + 1; i > newx; i--, j++)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currentx < newx && currenty > newy)
                            {
                                for(int i = currentx + 1, j = currenty - 1; i < newx; i++, j--)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            if(unobstructed)
                            {
                                if(boardstate[newx][newy] == null)
                                {//moves to square
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else
                                {//attack once ready
                                    attacksequence = true;
                                    boardstate[currentx][currenty] = null;
                                    
                                    turn = !turn;
                                }
                            }
                            else
                            {//resets if obstructed
                                currentpiece = null;
                            }
                        }
                        else
                        {//resets otherwise
                            currentpiece = null;
                        }
                    }

                    else if(currentpiece instanceof Rook)
                    {
                        if(Math.abs(currentx-newx) == 0 || Math.abs(currenty-newy) == 0)
                        {//checks if moving horizontal or vertical
                            boolean unobstructed = true;
                            //next loops check if piece is unobstructed
                            if(currentx > newx)
                            {
                                for(int i = currentx - 1; i > newx; i--)
                                {
                                    if(boardstate[i][currenty] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currentx < newx)
                            {
                                for(int i = currentx + 1; i < newx; i++)
                                {
                                    if(boardstate[i][currenty] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currenty > newy)
                            {
                                for(int i = currenty - 1; i > newy; i--)
                                {
                                    if(boardstate[currentx][i] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(currenty < newy)
                            {
                                for(int i = currenty + 1; i < newy; i++)
                                {
                                    if(boardstate[currentx][i] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            if(unobstructed)
                            {
                                if(boardstate[newx][newy] == null)
                                {
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else
                                {//attack once ready
                                    attacksequence = true;
                                    boardstate[currentx][currenty] = null;
                                    
                                    turn = !turn;
                                }
                            }
                            else
                            {
                                currentpiece = null;
                            }
                        }
                        else
                        {//resets otherwise
                            currentpiece = null;
                        }
                    }
                    else if(currentpiece instanceof Queen)
                    {
                        if(newx == currentx || newy == currenty || (Math.abs(newx-currentx) == Math.abs(newy-currenty)))
                        {
                            boolean unobstructed = true;
                            if(newx == currentx && newy > currenty)
                            {
                                for(int i = currenty + 1; i < newy; i++)
                                {
                                    if(boardstate[currentx][i] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            } 
                            else if(newx == currentx && newy < currenty)
                            {
                                for(int i = currenty - 1; i > newy; i--)
                                {
                                    if(boardstate[currentx][i] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newy == currenty && newx < currentx)
                            {
                                for(int i = currentx - 1; i > newx; i--)
                                {
                                    if(boardstate[i][currenty] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newy == currenty && newx > currentx)
                            {
                                for(int i = currentx + 1; i < newx; i++)
                                {
                                    if(boardstate[i][currenty] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newx > currentx && newy > currenty)
                            {
                                for(int i = currentx + 1, j = currenty + 1; i < newx; i++,j++)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newx < currentx && newy < currenty)
                            {
                                for(int i = currentx - 1, j = currenty - 1; i > newx; i--,j--)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newx > currentx && newy < currenty)
                            {
                                for(int i = currentx + 1, j = currenty - 1; i < newx; i++,j--)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            else if(newx < currentx && newy > currenty)
                            {
                                for(int i = currentx - 1, j = currenty + 1; i > newx; i--, j++)
                                {
                                    if(boardstate[i][j] != null)
                                    {
                                        unobstructed = false;
                                    }
                                }
                            }
                            if(unobstructed)
                            {
                                if(boardstate[newx][newy] == null)
                                {
                                    boardstate[newx][newy] = currentpiece;
                                    boardstate[currentx][currenty] = null;
                                    currentpiece = null;
                                    turn = !turn;
                                }
                                else
                                {//attack once ready
                                    attacksequence = true;
                                    boardstate[currentx][currenty] = null;
                                    
                                    turn = !turn;
                                }
                            }
                            else
                            {
                                currentpiece = null;
                            }
                        }
                        else
                        {
                            currentpiece = null;
                        }
                    }
                    else if(currentpiece instanceof King)
                    {
                        if(Math.abs(currentx-newx) <= 1 && Math.abs(currenty-newy) <= 1)
                        {
                            if(boardstate[newx][newy] == null)
                            {
                                boardstate[newx][newy] = currentpiece;
                                boardstate[currentx][currenty] = null;
                                currentpiece = null;
                                turn = !turn;
                            }
                            else
                            {//attack once ready
                                attacksequence = true;
                                boardstate[currentx][currenty] = null;
                                
                                turn = !turn;
                            }
                        }
                        else
                        {
                            currentpiece = null;
                        }
                    }
                }
                else
                {//resets if trying to take own piece
                    currentpiece = null;
                }
            }
            repaint();
        }
        else
        {
            attackingPiece = currentpiece;
            defendingPiece = boardstate[newx][newy];
            if(newAttackSequence)
                attackTurn = attackingPiece.type;   
            boolean defended;
            if(defendingPiece instanceof Queen)
            {
                defended = true;
            }
            else
            {
                defended = false;
            }
            //replace currentpiece with clicks instead and check inside the dimensions of the buttons during attacksequence
            if(attackingPiece.dead == false && defendingPiece.dead == false)
            {
                int clickX = m.getX();
                int clickY = m.getY();
                if(attackTurn == attackingPiece.type)
                {
                    if((clickX > 15 && clickX < 120) && (clickY > 335 && clickY < 400))
                    {
                        attackingPiece.attack(defendingPiece,defended);
                        defendingPiece.die(attackingPiece);
                        attackTurn = !attackTurn;
                    }
                    else if((clickX > 125 && clickX < 230) && (clickY > 335 && clickY < 400))
                    {
                        attackingPiece.block();
                        attackTurn = !attackTurn;
                    }
                    else if((clickX > 35 && clickX < 210) && (clickY > 405 && clickY < 465))
                    {
                        attackingPiece.special(defendingPiece);
                        defendingPiece.die(attackingPiece);
                        attackTurn = !attackTurn;
                    }
                    else
                    {
                        attackTurn = attackTurn;
                    }
                    newAttackSequence = false;
                }
                else
                {
                    if((clickX > 250 && clickX < 355) && (clickY > 335 && clickY < 400))
                    {
                        defendingPiece.attack(attackingPiece,defended);
                        attackingPiece.die(defendingPiece);
                        attackTurn = !attackTurn;
                    }
                    else if((clickX > 360 && clickX < 465) && (clickY > 335 && clickY < 400))
                    {
                        defendingPiece.block();
                        attackTurn = !attackTurn;
                    }
                    else if((clickX > 270 && clickX < 445) && (clickY > 405 && clickY < 465))
                    {
                        defendingPiece.special(attackingPiece);
                        attackingPiece.die(defendingPiece);
                        attackTurn = !attackTurn;
                    }
                    else
                    {
                        attackTurn = attackTurn;
                    }
                    newAttackSequence = false;
                }
            }
            if(attackingPiece.dead == true)
            {
                if(attackingPiece instanceof King)
                {
                    gamegoing = false;
                }
                boardstate[currentx][currenty] = null;
                currentpiece = null;
                attacksequence = false;
                defendingPiece.fortified = false;
                newAttackSequence = true;
                defendingPiece.levelUp();
            }
            if(defendingPiece.dead == true)
            {
                if(defendingPiece instanceof King)
                {
                    gamegoing = false;
                }
                boardstate[newx][newy] = currentpiece;
                boardstate[currentx][currenty] = null;
                currentpiece = null;
                attacksequence = false;
                attackingPiece.fortified = false;
                newAttackSequence = true;
                attackingPiece.levelUp();
            }
            repaint();
        }
    }

    public void mousePressed(MouseEvent m)
    {
    }

    public void mouseReleased(MouseEvent m)
    {
    }

    public void mouseEntered(MouseEvent m)
    {
    }

    public void mouseExited(MouseEvent m)
    {
    }
    //painting the board copied from https://www.geeksforgeeks.org/draw-a-chessboard-in-java-applet/
    //shortened code a little, changed the number of rows and columns to 8, and changed the size of the rectangles
    public void paint(Graphics g) 
    { 
        if(!attacksequence && gamegoing)
        {
            int x, y; 
            for (int row = 0; row < 8; row++) { 

                for (int col = 0; col < 8; col++) { 
                    x = row * 60; 
                    y = col * 60; 
                    if ((row % 2 == 0) == (col % 2 == 0)) 
                    {
                        if(boardstate[row][col] == null)
                        {
                            Color Black = new Color(13,13,13);
                            g.setColor(Black);
                        }
                        else
                        {
                            Color Black = new Color(13,13,13);
                            g.setColor(Black);
                            g.drawImage(boardstate[row][col].getSprite(),row*60,col*60,null);
                        }
                    }
                    else
                    {
                        if(boardstate[row][col] == null)
                        {
                            g.setColor(Color.GRAY); 
                            g.fillRect(x, y, 60, 60);
                        }
                        else
                        {
                            g.setColor(Color.GRAY); 
                            g.fillRect(x, y, 60, 60);
                            g.drawImage(boardstate[row][col].getSprite(),row*60,col*60,null);
                        }
                    }
                } 
            }
        }

        else if(attacksequence && gamegoing)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,480,480);
            g.drawImage(currentpiece.getSpriteUp(),50,200, null);
            g.drawImage(boardstate[newx][newy].getSpriteDown(),300,75,null);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,318,480,162);
            g.setColor(new Color(193, 165, 71));
            g.fillRect(0,320,480,160);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(7,327,466,146);
            g.setColor(new Color(23, 87, 147));
            g.fillRect(10,330,225,140);
            g.fillRect(245,330,225,140);

            
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(15,335,105,65);//attack
            g.fillRect(125,335,105,65);//block
            g.fillRect(250,335,105,65);
            g.fillRect(360,335,105,65);
            g.fillRect(35,405,175,60);//special
            g.fillRect(270,405,175,60);
            
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Monospaced",Font.BOLD,15));
            g.drawString("HEALTH:" + (currentpiece.health - currentpiece.damage) + "/" + currentpiece.health, 70,330);//first character
            g.drawString("HEALTH:" + (boardstate[newx][newy].health - boardstate[newx][newy].damage) + "/" + boardstate[newx][newy].health, 305,330);//second character
            g.setFont(new Font("Monospaced",Font.BOLD,20));
            g.drawString("ATTACK",30,360);
            g.drawString("BLOCK",145,360);
            g.drawString("ATTACK",265,360);
            g.drawString("BLOCK",380,360);

            g.drawString(currentpiece.getSpecialAttack(),70,430);
            g.drawString(boardstate[newx][newy].getSpecialAttack(),305,430);
        }
        else
        {
            boolean blackkingalive = false;
            boolean whitekingalive = false;
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(boardstate[i][j] instanceof King && boardstate[i][j].type)
                    {
                        blackkingalive = true;
                    }
                    else if(boardstate[i][j] instanceof King && !boardstate[i][j].type)
                    {
                        whitekingalive = true;
                    }
                }
            }
            if(blackkingalive)
            {
                g.setFont(new Font("Monospaced",Font.BOLD,20));
                g.drawString("BLACK WINS",200,360);
            }
            else
            {
                g.setFont(new Font("Monospaced",Font.BOLD,20));
                g.drawString("WHITE WINS",200,360);
            }
        }
    } 
}
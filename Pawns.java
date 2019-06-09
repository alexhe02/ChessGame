
/**
 * Abhinav Chowdavarapu and Alex He
 * 5/2/19
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.applet.*;
import java.lang.*;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
public class Pawns extends Pieces
{
    private int enPassantdamage;
    public ImageIO thisguy;
    public Pawns(boolean input)
    {
        super(3,75,30,1,input);
        enPassantdamage = 2;
        setSprite(input);
        setBattleSpriteUp(input);
        setBattleSpriteDown(input);
        specialAttack = "ENPASSANT";
    }

    /**
     * With help from Henry
     */
    public void setSprite(boolean input)
    {
        try
        {
            if(input)
            {
                sprite = ImageIO.read(new File("Images/Black Pawn Board.png"));
            }
            else
            {
                sprite = ImageIO.read(new File("Images/White Pawn Board.png"));
            }
        }catch (IOException e){
        }

    }

    public void setBattleSpriteUp(boolean input)
    {
        try
        {
            if(input)
            {
                battleSpriteUp = ImageIO.read(new File("Images/Pawn/Black Pawn Face Up.jpg"));
            }
            else
            {
                battleSpriteUp = ImageIO.read(new File("Images/Pawn/White Pawn Face Up.jpg"));
            }
        }catch (IOException e){
        }
    }

    public void setBattleSpriteDown(boolean input)
    {
        try
        {
            if(input)
            {
                battleSpriteDown = ImageIO.read(new File("Images/Pawn/Black Pawn Face Down.jpg"));
            }
            else
            {
                battleSpriteDown = ImageIO.read(new File("Images/Pawn/White Pawn Face Down.jpg"));
            }
        }catch (IOException e){
        }
    }
    
    public void attack(Pieces other,boolean defended)
    {
        super.attack(other,defended);
    }

    public void block()
    {
        super.block();
    }

    public void special(Pieces other)
    {
        super.special(other);
    }

    public void levelUp()
    {
        while (experience >= levelcost)
        {
            experience -= levelcost;
            level += 1;
            attack += 1;
            health += 1;
            enPassantdamage += 1;
        }
    }

    public void die(Pieces other)
    {
        super.die(other);
    }
}

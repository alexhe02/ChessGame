

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
public class Rook extends Pieces
{
    public Rook(boolean input)
    {
        super(5,150,40,5,input);
        setSprite(input);
        setBattleSpriteUp(input);
        setBattleSpriteDown(input);
        specialAttack = "FORTIFY";
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
                sprite = ImageIO.read(new File("Images/Black Rook Board.png"));
            }
            else
            {
                sprite = ImageIO.read(new File("Images/White Rook Board.png"));
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
                battleSpriteUp = ImageIO.read(new File("Images/Rook/Black Rook Face Up.jpg"));
            }
            else
            {
                battleSpriteUp = ImageIO.read(new File("Images/Rook/White Rook Face Up.jpg"));
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
                battleSpriteDown = ImageIO.read(new File("Images/Rook/Black Rook Face Down.jpg"));
            }
            else
            {
                battleSpriteDown = ImageIO.read(new File("Images/Rook/White Rook Face Down.jpg"));
            }
        }catch (IOException e){
        }
    }
    
    public void attack (Pieces other,boolean defended)
    {
        super.attack(other,defended);
    }
    
    public void block()
    {
        super.block();
    }
    
    public void levelUp()
    {
        while(experience >= levelcost)
        {
            experience -= levelcost;
            level += 1;
            attack += 1;
            health += 1;
        }
    }
    
    public void die(Pieces other)
    {
        super.die(other);
    }
}

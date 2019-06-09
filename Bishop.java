

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
public class Bishop extends Pieces
{
    private int healamount;
    public Bishop(boolean input)
    {
        super(3,100,30,3,input);
        setSprite(input);
        setBattleSpriteUp(input);
        setBattleSpriteDown(input);
        healamount = 3;
        specialAttack = "HEAL";
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
                sprite = ImageIO.read(new File("Images/Black Bishop Board.png"));
            }
            else
            {
                sprite = ImageIO.read(new File("Images/White Bishop Board.png"));
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
                battleSpriteUp = ImageIO.read(new File("Images/Bishop/Black Bishop Face Up.jpg"));
            }
            else
            {
                battleSpriteUp = ImageIO.read(new File("Images/Bishop/White Bishop Face Up.jpg"));
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
                battleSpriteDown = ImageIO.read(new File("Images/Bishop/Black Bishop Face Down.jpg"));
            }
            else
            {
                battleSpriteDown = ImageIO.read(new File("Images/Bishop/White Bishop Face Down.jpg"));
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
    
    public void heal(Pieces other)
    {
        super.special(other);
    }
    
    public void levelUp()
    {
        while (experience >= levelcost)
        {
            level += 1;
            experience -= levelcost;
            attack += 1;
            health += 1;
        }
    }
    
    public void die(Pieces other)
    {
        super.die(other);
    }
}

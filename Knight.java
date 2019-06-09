

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
public class Knight extends Pieces
{
    private int trampledamage;
    public Knight(boolean input)
    {
        super(5,75,40,3,input);
        trampledamage = 2;
        setSprite(input);
        setBattleSpriteUp(input);
        setBattleSpriteDown(input);
        specialAttack = "TRAMPLE";
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
                sprite = ImageIO.read(new File("Images/Black Knight Board.png"));
            }
            else
            {
                sprite = ImageIO.read(new File("Images/White Knight Board.png"));
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
                battleSpriteUp = ImageIO.read(new File("Images/Knight/Black Knight Face Up.jpg"));
            }
            else
            {
                battleSpriteUp = ImageIO.read(new File("Images/Knight/White Knight Face Up.jpg"));
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
                battleSpriteDown = ImageIO.read(new File("Images/Knight/Black Knight Face Down.jpg"));
            }
            else
            {
                battleSpriteDown = ImageIO.read(new File("Images/Knight/White Knight Face Down.jpg"));
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
            trampledamage += 1;
        }
    }
    
    public void die(Pieces other)
    {
        super.die(other);
    }
}

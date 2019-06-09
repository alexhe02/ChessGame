

/**
 * Abhinav Chowdavarapu and Alex He
 * 5/3/19
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.applet.*;
import java.lang.*;
import javax.swing.*;
import java.io.IOException;
import java.io.File;
public class King extends Pieces
{
    private double damageReduction;
    private int tempattack;
    public King(boolean input)
    {
        super(16,300,30,2,input);
        damageReduction = 0.1;
        tempattack = attack;
        setSprite(input);
        setBattleSpriteUp(input);
        setBattleSpriteDown(input);
        specialAttack = "CHARGE ATTACK";
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
                sprite = ImageIO.read(new File("Images/Black King Board.png"));
            }
            else
            {
                sprite = ImageIO.read(new File("Images/White King Board.png"));
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
                battleSpriteUp = ImageIO.read(new File("Images/King/Black King Face Up.jpg"));
            }
            else
            {
                battleSpriteUp = ImageIO.read(new File("Images/King/White King Face Up.jpg"));
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
                battleSpriteDown = ImageIO.read(new File("Images/King/Black King Face Down.jpg"));
            }
            else
            {
                battleSpriteDown = ImageIO.read(new File("Images/King/White King Face Down.jpg"));
            }
        }catch (IOException e){
        }
    }
    
    public void block()
    {
        super.block();
    }
    
    public void special(Pieces other)
    {
        super.special(other);
    }
    
    public void attack(Pieces other,boolean defended)
    {
        super.attack(other,defended);
    }
    
    public void levelUp()
    {
        while (experience >= levelcost)
        {
            experience -= levelcost;
            level += 1;
            attack += 1;
            health += 1;
            damageReduction += 0.05;
        }
    }
}

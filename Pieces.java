

/**
 * Abhinav Chowdavarapu and Alex He
 * 5/2/19
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
public abstract class Pieces
{
    protected int levelcost;
    protected int level;
    protected int experience;
    protected int health;
    protected int damage;
    protected int attack;
    protected int experienceondeath;
    protected boolean blocking;
    protected int numblocks;
    protected boolean dead;
    protected Image sprite;
    protected Image battleSpriteUp;
    protected Image battleSpriteDown;
    protected boolean type;
    protected String specialAttack;
    protected int numheals = 0;
    protected boolean fortified = false;
    protected int originalattack;
    public Pieces(int levelcost, int health, int attack, int experienceondeath, boolean type)
    {
        //stores the information that the subclasses will give it
        level = 1;
        experience = 0;
        this.levelcost = levelcost;
        this.health = health;
        this.damage = 0;
        this.attack = attack;
        this.experienceondeath = experienceondeath;
        blocking = false;
        numblocks = 0;
        dead = false;
        this.type = type;
        originalattack = attack;
    }
    
    public boolean getType()
    {
        return type;
    }
    
    public void attack(Pieces other, boolean defended)
    {
        //deals damage if not blocking and removes block if blocking
        if (!other.blocking)
        {
            if(other.fortified)
            {
                other.damage += attack*0.95;
                this.attack = originalattack;
            }
            else if(defended)
            {
                other.damage += attack*0.75;
                this.attack = originalattack;
            }
            else
            {
                other.damage += attack;
                this.attack = originalattack;
            }
       }
        else
        {
            other.blocking = false;
            this.attack = originalattack;
        }
    }
    
    public void block()
    {
        //blocks and adds one to numblocks if numblocks < 6
        if (numblocks < 6)
        {
            blocking = true;
            numblocks += 1;
        }
    }
    
    public void levelUp()
    {
    }
    
    public void die(Pieces other)
    {
        //checks if dead
        //if true, dies and gives experienceondeath to the piece it was fighting
        if (damage >= health)
        {
            this.dead = true;
            other.experience += this.experienceondeath;
        }
    }
    
    public void special(Pieces other)
    {
        if(this instanceof Pawns)
        {
            if (other instanceof Pawns)
            {
                if (!other.blocking)
                {
                    other.damage += this.attack*2;
                }
                else
                {
                    other.blocking = false;
                }
            }
            else
            {
                if (!other.blocking)
                {
                    other.damage += this.attack;
                }
                else
                {
                    other.blocking = false;
                }
            }
        }
        if(this instanceof Bishop)
        {
                if (numheals < 4)
            {
                if (damage < 30)
                {
                    damage = 0;
                }
                else
                {
                    damage -= 30;
                }
                numheals += 1;
            }
        }
        if(this instanceof Knight)
        {
                if (other.blocking)
            {
                other.blocking = false;
            }
            else if (other.level == 1)
            {
                other.damage += this.attack*1.5;
            }
            else
            {
                other.damage += this.attack/2;
            }
        }
        if(this instanceof Rook)
        {
            fortified = true;
        }
        if(this instanceof King)
        {
            this.attack *= 1.25;
        }
    }
    
    public Image getSprite()
    {
        return sprite;
    }
    
    public Image getSpriteUp()
    {
        return battleSpriteUp;
    }
    
    public Image getSpriteDown()
    {
        return battleSpriteDown;
    }
    
    public String getSpecialAttack()
    {
        return specialAttack;
    }
}

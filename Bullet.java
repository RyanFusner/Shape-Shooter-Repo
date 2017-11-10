import greenfoot.*;

public class Bullet extends Actor
{
    public int Damage;
    public int Range;
    
    public Bullet(int damage, int range)
    {
        Damage = damage;
        Range = range;
    }
    
    public void act() 
    {
        if(Range <= 0 || isAtEdge())
        {
            getWorld().removeObject(this);
        } 
        else 
        {
            move(10);
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            if (enemy != null) 
            {
                getWorld().removeObject(this);
                enemy.hit(Damage);
            }
            else 
            {
                Range -= 10;
            }
        }
    }
}

import greenfoot.*;
import java.util.List;

public class Enemy extends Actor
{
    /*
     * Medium
     *      Image = Square
     *      Health = 75
     *      Speed = 2
     *      Damage = 20
     *      ("Square.png", 50, 2, 20)
     * Light
     *      Image = Circle
     *      Health = 20
     *      Speed = 4
     *      Damage = 10
     *      ("Circle.png", 20, 4, 10)
     * Heavy
     *      Image = Hexagon
     *      Health = 200
     *      Speed = 1
     *      Damage = 50
     *      ("Hexagon.png", 200, 1, 50)
     */
    private int Speed;
    public int Health;
    private int Damage;
    
    public Enemy(String image, int health, int speed, int damage)
    {
        Speed = speed;
        Health = health;
        Damage = damage;
        setImage(image);
    }
    
    public void act() 
    {
        faceCharater();
        move(Speed);
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) 
        {
            SSWorld world = (SSWorld)getWorld();
            world.EnemiesRemaining--;
            world.ShowEnemies();
            world.removeObject(this);
            player.hit(Damage);
        }
    }
    
    private void faceCharater()
    {
        try
        {
            List<Player> players = getWorld().getObjects(Player.class);
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();
            setRotation(pointTo(playerX,playerY));
        }
        catch(IndexOutOfBoundsException e)
        {
            Speed = 0;
        }
    }
    
    private int pointTo(int x, int y)
    {   
        int dX = x - getX();
        int dY = y - getY();
        double rotation = Math.atan2(dY, dX); 
        rotation = Math.toDegrees(rotation); 
        return (int)rotation;
    }
    
    public void hit(int damage)
    {
        Health = Health - damage;
        Greenfoot.playSound("Damage.wav");
        if(Health <= 0)
        {
            SSWorld world = (SSWorld)getWorld();
            world.EnemiesRemaining--;
            world.ShowEnemies();
            Greenfoot.playSound("Death.wav");
            world.removeObject(this);
        }
    }
}

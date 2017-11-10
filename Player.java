import greenfoot.*;

public class Player extends Actor
{
    private int Speed = 3;
    private int Health;
    private int RegenDelay = 20;
    private int RegenTimer;
    private int RecoveryDelay = 200;
    private int RecoveryTimer;
    private int mouseX;
    private int mouseY;
    private Weapon EquipedWeapon;
    private int ReloadTimer;
    private boolean FirePressed;
    //(Damage, FireRate, Spread, Range, Projectiles, FireMode)
    private Weapon Pistol = new Weapon(50, 10, 10, 300, 1, 1);
    private Weapon Assault = new Weapon(25, 8, 10, 500, 1, 2);
    private Weapon SMG = new Weapon(20, 5, 20, 250, 1, 2);
    private Weapon Shotgun = new Weapon(25, 30, 40, 200, 7, 1);
    private Weapon Sniper = new Weapon(100, 40, 2, 800, 1, 1);
    
    public Player()
    {
        Health = 100;
        RegenTimer = RegenDelay;
        RecoveryTimer = RecoveryDelay;
        EquipedWeapon = Pistol;
        ReloadTimer = EquipedWeapon.FireRate;
        FirePressed = false;
    }
    
    public void act() 
    {
        faceMouse();
        checkKeys();
        Regen();
        ReloadTimer++;
    }
    
    private void checkKeys()
    {
        swapWeapons();
        if(ReloadTimer >= EquipedWeapon.FireRate && !Greenfoot.isKeyDown("space"))
        {
            FirePressed = false;
        }
        if(Greenfoot.isKeyDown("space"))
        {
            fire(EquipedWeapon.FireMode);
            FirePressed = true;
        }
        if(Greenfoot.isKeyDown("w"))
        {
            setLocation(getX(), getY() - Speed);
        }
        if(Greenfoot.isKeyDown("a"))
        {
            setLocation(getX() - Speed, getY());
        }
        if(Greenfoot.isKeyDown("s"))
        {
            setLocation(getX(), getY() + Speed);
        }
        if(Greenfoot.isKeyDown("d"))
        {
            setLocation(getX() + Speed, getY());
        }
    }
    
    private void faceMouse()
    {
        if(Greenfoot.mouseMoved(null) || Greenfoot.mouseDragged(null)) 
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            mouseX = mouse.getX();
            mouseY = mouse.getY();
        }
        setRotation(pointTo(mouseX,mouseY));
    }
    
    private int pointTo(int x, int y)
    {   
        int dX = x - getX();
        int dY = y - getY();
        double rotation = Math.atan2(dY, dX); 
        rotation = Math.toDegrees(rotation); 
        return (int)rotation;
    }
    
    private void fire(int mode)
    {
        if(mode == 1)
        {
            if(ReloadTimer >= EquipedWeapon.FireRate && !FirePressed)
            {
                Greenfoot.playSound("Weapon.wav");
                for (int i = 0; i < EquipedWeapon.Projectiles; i++)
                {
                    Bullet b = new Bullet(EquipedWeapon.Damage, EquipedWeapon.Range);
                    b.setRotation(getRotation() + (Greenfoot.getRandomNumber(EquipedWeapon.Spread) - (EquipedWeapon.Spread / 2)));
                    getWorld().addObject(b, getX(), getY());
                    ReloadTimer = 0;
                }
            }
        }
        if(mode == 2)
        {
            if(ReloadTimer >= EquipedWeapon.FireRate)
            {
                Greenfoot.playSound("Weapon.wav");
                for (int i = 0; i < EquipedWeapon.Projectiles; i++)
                {
                    Bullet b = new Bullet(EquipedWeapon.Damage, EquipedWeapon.Range);
                    b.setRotation(getRotation() + (Greenfoot.getRandomNumber(EquipedWeapon.Spread) - (EquipedWeapon.Spread / 2)));
                    getWorld().addObject(b, getX(), getY());
                    ReloadTimer = 0;
                }
            }
        }
    }
    
    private void swapWeapons()
    {
        if(Greenfoot.isKeyDown("1") && EquipedWeapon != Pistol)
        {
            EquipedWeapon = Pistol;
        }
        if(Greenfoot.isKeyDown("2") && EquipedWeapon != Assault)
        {
            EquipedWeapon = Assault;
        }
        if(Greenfoot.isKeyDown("3") && EquipedWeapon != SMG)
        {
            EquipedWeapon = SMG;
        }
        if(Greenfoot.isKeyDown("4") && EquipedWeapon != Shotgun)
        {
            EquipedWeapon = Shotgun;
        }
        if(Greenfoot.isKeyDown("5") && EquipedWeapon != Sniper)
        {
            EquipedWeapon = Sniper;
        }
    }
    
    public void hit(int damage)
    {
        Health = Health - damage;
        SSWorld world = (SSWorld) getWorld();
        world.PlayerHealth = Health;
        world.ShowHealth();
        RecoveryTimer = 0;
        Greenfoot.playSound("Damage.wav");
        if(Health <= 0)
        {
            Health = 0;
            world.PlayerHealth = Health;
            world.ShowHealth();
            world.ShowEndMessage();
            Greenfoot.playSound("Death.wav");
            getWorld().removeObject(this);
            Greenfoot.stop();
        }
    }
    
    private void Regen()
    {
        RecoveryTimer++;
        if(RecoveryTimer >= RecoveryDelay && Health < 100)
        {
            if(RegenTimer >= RegenDelay)
            {
                Health++;
                SSWorld world = (SSWorld) getWorld();
                world.PlayerHealth = Health;
                world.ShowHealth();
                RegenTimer = 0;
            }
            RegenTimer++;
        }
    }
}

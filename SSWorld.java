import greenfoot.*;

public class SSWorld extends World
{
    private int Wave;
    private int Enemies;
    public int EnemiesRemaining;
    private double SpawnDelay;
    private int SpawnTimer;
    private int WaveDelay = 200;
    private int WaveTimer;
    private int[] SpawnsX = {450, 885, 450, 15};
    private int[] SpawnsY = {15, 450, 885, 450};
    private int SpawnLocation;
    private int SpawnType;
    public int PlayerHealth;
    
    public SSWorld()
    {    
        super(900, 900, 1); 
        prepare();
        Wave = 0;
        Enemies = 0;
        SpawnDelay = 50;
        SpawnTimer = 0;
        WaveTimer = 200;
        PlayerHealth = 100;
        EnemiesRemaining = Enemies;
        ShowWave();
        ShowEnemies();
        ShowWeapons();
        ShowHealth();
        setPaintOrder(Door.class, Border.class, Player.class, Enemy.class, Bullet.class);
    }

    public void act()
    {
        SpawnEnemies();
        NextWave();
    }
    
    private void SpawnEnemies()
    {
        if(SpawnTimer >= SpawnDelay && Enemies > 0)
        {
            SpawnType = Greenfoot.getRandomNumber(100);
            if(SpawnType < 10)
            {
                SpawnLocation = Greenfoot.getRandomNumber(4);
                addObject(new Enemy("Circle.png", 20, 4, 10), SpawnsX[SpawnLocation], SpawnsY[SpawnLocation]);
            }
            if(SpawnType > 9 && SpawnType < 90)
            {
                SpawnLocation = Greenfoot.getRandomNumber(4);
                addObject(new Enemy("Square.png", 50, 2, 20), SpawnsX[SpawnLocation], SpawnsY[SpawnLocation]);
            }
            if(SpawnType > 89)
            {
                SpawnLocation = Greenfoot.getRandomNumber(4);
                addObject(new Enemy("Hexagon.png", 200, 1, 50), SpawnsX[SpawnLocation], SpawnsY[SpawnLocation]);
            }
            SpawnTimer = 0;
            Enemies--;
        }
        SpawnTimer++;
    }
    
    private void NextWave()
    {
        if(Enemies <= 0 && EnemiesRemaining <= 0)
        {
            WaveTimer++;
            if(WaveTimer >= WaveDelay)
            {
                Wave++;
                ShowWave();
                Enemies = Wave * 20;
                SpawnDelay = 0.95 * SpawnDelay;
                EnemiesRemaining = Enemies;
                ShowEnemies();
                WaveTimer = 0;
            }
        }
    }
    
    private void ShowWave()
    {
        showText("Wave: " + Wave, 80, 20);
    }
    
    public void ShowEnemies()
    {
        showText("Enemies: " + EnemiesRemaining, 80, 40);
    }
    
    private void ShowWeapons()
    {
        showText("[1] Pistol", 820, 20);
        showText("[2] Assault Rifle", 820, 40);
        showText("[3] SMG", 820, 60);
        showText("[4] Shotgun", 820, 80);
        showText("[5] Sniper Rifle", 820, 100);
    }
    
    public void ShowHealth()
    {
        showText("Health: " + PlayerHealth + "%", 80, 60);
    }
    
    public void ShowEndMessage()
    {
        showText("Game Over\nYou Survived " + (Wave - 1) + " waves.", 450, 450);
    }
    
    private void prepare()
    {
        Player player = new Player();
        addObject(player,450,450);
        player.turn(-90);
        Border border = new Border();
        addObject(border,450,2);
        Border border2 = new Border();
        addObject(border2,450,898);
        Border border3 = new Border();
        addObject(border3,2,450);
        border3.turn(90);
        Border border4 = new Border();
        addObject(border4,898,450);
        border4.turn(90);
        Door door = new Door();
        addObject(door,450,4);
        Door door2 = new Door();
        addObject(door2,450,896);
        Door door3 = new Door();
        addObject(door3,4,450);
        door3.turn(90);
        Door door4 = new Door();
        addObject(door4,896,450);
        door4.turn(90);
    }
}

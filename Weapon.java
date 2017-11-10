public class Weapon  
{
    /*
     * Pistol
     *      Damage = 25
     *      Fire Rate = Fast
     *      Spread = Low
     *      Range = Short
     *      Projectiles = 1
     *      Mode = 1
     *      (25, 10, 10, 200, 1, 1)
     * Assault Rifle
     *      Damage = 50
     *      Fire Rate = Medium
     *      Spread = Low
     *      Range = Medium
     *      Projectiles = 1
     *      Mode = 2
     *      
     * SMG
     *      Damage = 20
     *      Fire Rate = Very Fast
     *      Spread = Medium
     *      Range = Short
     *      Projectiles = 1
     *      Mode = 2
     *      
     * Shotgun
     *      Damage = 25
     *      Fire Rate = Slow
     *      Spread = High
     *      Range = Very Short
     *      Projectiles = 7
     *      Mode = 1
     *      
     * Sniper Rifle
     *      Damage = 100
     *      Fire Rate = Slow
     *      Spread = None
     *      Range = Long
     *      Projectiles = 1
     *      Mode = 1
     *      
     */
    public int Damage;
    public int FireRate;
    public int Spread;
    public int Range;
    public int Projectiles;
    public int FireMode;
    
    public Weapon(int damage, int fireRate, int spread, int range, int projectiles, int fireMode)
    {
        Damage = damage;
        FireRate = fireRate;
        Spread = spread;
        Range = range;
        Projectiles = projectiles;
        FireMode = fireMode;
    }
}

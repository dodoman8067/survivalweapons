package kro.dodoworld.survivalweapons.util.code;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleUtil {

    public static void spawnLaser(Location basis, Location target, Color color){
        World world = basis.getWorld();
        Validate.isTrue(target.getWorld().equals(world));
        double dis = basis.distance(target);
        Vector pos1 = basis.toVector();
        Vector pos2 = target.toVector();

        Vector vector = pos2.clone().subtract(pos1).normalize().multiply(0.1);
        double cover = 0;
        for(; cover < dis; pos1.add(vector)){
            Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);
            world.spawnParticle(Particle.REDSTONE, pos1.getX(), pos1.getY(), pos1.getZ(), 1, dustOptions);
            cover += 0.1;
        }
    }
}
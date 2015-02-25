package ru.xAveR.FireArrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

public class FireArrow extends JavaPlugin implements Listener{
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable(){
		
	}
	 @SuppressWarnings("unused")
	@EventHandler
	  public void onProjectileHitEvent(ProjectileHitEvent event)
	  {
	    if (((event.getEntity().getShooter() instanceof Player)) && 
	      ((event.getEntity() instanceof Arrow)))
	    {
		Player player = (Player)event.getEntity().getShooter();
	      Projectile arrow = event.getEntity();
	      Location arrowIn = event.getEntity().getLocation();
	      if ((arrow.getFireTicks() > 0))
	      {
	        BlockIterator iterator = new BlockIterator(event
	          .getEntity().getWorld(), arrow.getLocation()
	          .toVector(), arrow.getVelocity().normalize(), 0.0D, 4);
	        Block arrowTouch = null;
	        while (iterator.hasNext())
	        {
	          arrowTouch = iterator.next();
	          if (arrow.getLocation().getBlock() != null) {
	            break;
	          }
	        }
	        BlockFace relative = arrowIn.getBlock().getFace(arrowTouch);
	        arrow.getLocation().getBlock().getRelative(relative).setType(Material.FIRE);
	        }
	      }
	    }
	  
	}
package ru.xAveR.FireArrow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

public class FireArrow extends JavaPlugin implements Listener{
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable(){
		
	}
	@EventHandler
	  public void onProjectileHitEvent(ProjectileHitEvent event)
	  {
	    if (((event.getEntity().getShooter() instanceof Player)) &&  ((event.getEntity() instanceof Arrow))){
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
	  @EventHandler
	  public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	  {
	    Entity entity = event.getEntity();
	    Entity damager = event.getDamager();
	    if(((entity instanceof Player)) && ((damager instanceof Projectile)) && (damager.getType().equals(EntityType.ARROW)) && (((Player)entity).isBlocking())){
	      ((Player)entity).playSound(entity.getLocation(), Sound.ANVIL_LAND, 2.5F, 1.0F);
	      event.setCancelled(true);
	    }
	  }
	}
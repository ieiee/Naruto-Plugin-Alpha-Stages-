package org.minecraft.minecraft.listeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;
import org.minecraft.minecraft.DatabaseCommands.skillsSql;
import org.minecraft.minecraft.MyFirstPlugin;
import org.minecraft.minecraft.chakara.chakaraControl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class toolsListeners implements Listener {

    private final HashMap<String, Long> cooldowns = new HashMap<>();
    private final HashMap<String, Integer> checkMap = new HashMap<>();
    public MyFirstPlugin plugin = MyFirstPlugin.getPlugin();


    //for cooldowns
    public boolean checkCooldown(String playerName){
        if (!cooldowns.containsKey(playerName)) return false;

        long currentTime = System.currentTimeMillis();
        long lastThrowTime = cooldowns.get(playerName);
        long cooldownTimeMillis = 2000;
        // in this calculation last throw time is when players name is added in the HashMap and if this calculation returns negative values it mean the cooldown is still there and not over
        long timeRemaining = lastThrowTime + cooldownTimeMillis - currentTime;
        return timeRemaining > 0;
    }
    //enters players name in cooldown
    public void setCooldown(String playerName){
        cooldowns.put(playerName, System.currentTimeMillis());
    }

    //event Listener for FireStyle weapon
    @EventHandler
    public void onFireStyle(PlayerInteractEvent event){

        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null){
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "FireStyle: " + ChatColor.GREEN +  "Fireball Jutsu")) {
            Vector direction = player.getLocation().getDirection();
            Location particleLocation = player.getEyeLocation().add(direction);
            player.getWorld().spawnParticle(Particle.FLAME, particleLocation, 35, 0.5, 0.5, 0.5, 0.2);

            for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(5);
                    entity.setFireTicks(100);
                }
            }
        }
    }

    // event Listener for fireball zutsu
    @EventHandler
    public void onFireball(PlayerInteractEvent event) {

       if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null){
           return;
       }

        try{
            Player player = event.getPlayer();
            if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FireBall")) {
                event.setCancelled(true);
                if (checkCooldown(player.getName())) {
                    event.getPlayer().sendMessage(ChatColor.RED + "You must wait 2 sec before throwing this again");
                    return;
                }
                setCooldown(player.getName());
                Location location = player.getEyeLocation();
                Vector direction = location.getDirection();
                double rotation = (player.getLocation().getYaw() - 90) % 360;

                if (rotation < 0){
                    rotation += 360;
                }

                Location locationRight;
                Location locationLeft;
                double magnitudeRight = direction.length();
                double magnitudeLeft = direction.length();
                double angleRight = Math.atan2(direction.getZ(), direction.getX());
                double angleLeft = Math.atan2(direction.getZ(), direction.getX());
                angleRight += Math.toRadians(0);
                angleLeft += Math.toRadians(0);
                double RX = magnitudeRight * Math.cos(angleRight);
                double RZ = magnitudeRight * Math.sin(angleRight);
                double LX = magnitudeLeft * Math.cos(angleLeft);
                double LZ = magnitudeLeft * Math.sin(angleLeft);
                Vector Right;
                Vector Left;
                Fireball fireball = (Fireball) player.getWorld().spawnEntity(location.add(direction), EntityType.FIREBALL);
                if ((0 <= rotation && rotation <45 || 315 <= rotation && rotation < 360) || (135 <= rotation && rotation < 225))
                {

                    locationRight = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 0.5);
                    locationLeft = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 0.5);

                    Right = new Vector(RX, direction.getY(), RZ + 0.5);
                    Left = new Vector(LX, direction.getY(), LZ - 0.5);

                    Fireball fireballRight = (Fireball) player.getWorld().spawnEntity(locationRight.add(Right), EntityType.FIREBALL);
                    Fireball fireballLeft = (Fireball) player.getWorld().spawnEntity(locationLeft.add(Left), EntityType.FIREBALL);
                    fireball.setDirection(direction);
                    fireballRight.setDirection(Right);
                    fireballLeft.setDirection(Left);

                }else {

                    locationRight = new Location(location.getWorld(), location.getX() + 0.5, location.getY(), location.getZ());
                    locationLeft = new Location(location.getWorld(), location.getX() - 0.5, location.getY(), location.getZ());

                    Right = new Vector(RX + 0.5, direction.getY(), RZ);
                    Left = new Vector(LX - 0.5, direction.getY(), LZ);

                    Fireball fireballRight = (Fireball) player.getWorld().spawnEntity(locationRight.add(Right), EntityType.FIREBALL);
                    Fireball fireballLeft = (Fireball) player.getWorld().spawnEntity(locationLeft.add(Left), EntityType.FIREBALL);
                    fireball.setDirection(direction);
                    fireballRight.setDirection(Right);
                    fireballLeft.setDirection(Left);

                }
                player.getWorld().playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
                player.getWorld().spawnParticle(Particle.FLAME, location, 50, 0.5, 0.5, 0.5, 0.1);
                player.getWorld().spawnParticle(Particle.SMOKE_LARGE, location, 50, 0.5, 0.5, 0.5, 0.1);
            }
        }catch (Exception e){
            System.out.println(ChatColor.YELLOW + "Event canceled due to player was too hasty");
        }
    }

    //for snow hit create explosion used in rasengan
    @EventHandler
    public void onSnowHits(ProjectileHitEvent event){
        try {
            if (event.getEntity() instanceof Snowball) {
                Snowball snowball = (Snowball) event.getEntity();
                if (snowball.getShooter() instanceof Player) {
                    Player player = (Player) snowball.getShooter();
                    if (Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "WindStyle: " + ChatColor.AQUA +  "Rasenshuriken")) {
                        if (!skillsSql.checkAirBalloon(player)) {
                            player.sendMessage(ChatColor.RED + "You didn't mastered the requirements to perform rasenshurikan");
                            return;
                        }
                        if (checkMap.get(player.getName()) >= 60) {
                            Location hitLocation = snowball.getLocation();
                            Objects.requireNonNull(hitLocation.getWorld()).createExplosion(hitLocation, 8.0F, true);
                        } else if (checkMap.get(player.getName()) < 60 && checkMap.get(player.getName()) >= 20) {
                            Location hitLocation = snowball.getLocation();
                            Objects.requireNonNull(hitLocation.getWorld()).createExplosion(hitLocation, 4.0F, true);
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("An error occurred in snow hit event");
        }
    }

    // used in rasengan to make fire like particle effects
    @EventHandler
    public void particles(ProjectileLaunchEvent event) throws SQLException {
        if (event.getEntity() instanceof Snowball) {
            Snowball ball = (Snowball) event.getEntity();
            if (Objects.requireNonNull(ball.getItem().getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "WindStyle: " + ChatColor.AQUA +  "Rasenshuriken")) {
                Player player = (Player) event.getEntity().getShooter();
                assert player != null;
                if (!skillsSql.checkAirBalloon(player)) {
                    player.sendMessage(ChatColor.RED + "You didn't mastered the requirements to perform rasenshurikan");
                    event.setCancelled(true);
                    return;
                }
                if (chakaraControl.realTimeChakaraMap.get(player.getName()) < 20){
                    player.sendMessage(ChatColor.RED + "You didn't have much chakara left for this move");
                    player.sendMessage(ChatColor.YELLOW + "(Requires 60 Chakara Level)");
                    event.setCancelled(true);
                    return;
                }

                if (chakaraControl.realTimeChakaraMap.get(player.getName()) >= 60) {
                    chakaraControl.reduceChakaraLevel(player, 60);
                    if (checkMap.containsKey(player.getName())){
                        checkMap.replace(player.getName(), 60);
                    }else{
                        checkMap.put(player.getName(), 60);
                    }
                    Location staticLocation = ball.getLocation();
                    Objects.requireNonNull(staticLocation.getWorld()).spawnParticle(Particle.SMOKE_LARGE, staticLocation, 50, 0.5, 0.5, 0.5, 0.1);
                    plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                        if (ball.isOnGround() || ball.isDead()) {
                            return;
                        }
                        Location location = ball.getLocation();
                        Vector velocity = ball.getVelocity();
                        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.SOUL_FIRE_FLAME, location, 1, 0, 0, 0, 0, null);
                        location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 50, 0.5, 0.5, 0.5, 0.1);
                        location.add(velocity);
                    }, 0L, 1L);
                } else if (chakaraControl.realTimeChakaraMap.get(player.getName()) <60 && chakaraControl.realTimeChakaraMap.get(player.getName()) >= 20) {
                    chakaraControl.reduceChakaraLevel(player, 20);
                    if (checkMap.containsKey(player.getName())){
                        checkMap.replace(player.getName(), 20);
                    }else{
                        checkMap.put(player.getName(), 20);
                    }
                    Location staticLocation = ball.getLocation();
                    Objects.requireNonNull(staticLocation.getWorld()).spawnParticle(Particle.SMOKE_LARGE, staticLocation, 25, 0.5, 0.5, 0.5, 0.1);
                    plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                        if (ball.isOnGround() || ball.isDead()) {
                            return;
                        }
                        Location location = ball.getLocation();
                        Vector velocity = ball.getVelocity();
                        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.SOUL_FIRE_FLAME, location, 1, 0, 0, 0, 0, null);
                        location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 25, 0.5, 0.5, 0.5, 0.1);
                        location.add(velocity);
                    }, 0L, 1L);
                }
            }
        }
    }

    // water balloon drills
    @EventHandler
    public void onSkillEggs(PlayerInteractEvent event){

        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null){
            return;
        }

        try {
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            Player player = event.getPlayer();
            Random random = new Random();
            if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "WaterBalloon")) {
                int chance = random.nextInt(10) + 1;
                event.setCancelled(true);
                if (skillsSql.checkWaterBalloon(player)) {
                    player.sendMessage(ChatColor.GREEN + "You have already mastered WaterBalloon Drill");
                    return;
                }
                if (chance == 5) {
                    skillsSql.giveWaterBalloonSkill(player);
                } else {
                    player.sendMessage(ChatColor.DARK_AQUA + "Failed, don't loose hope until you reach your destini, To Become A Great Ninja, BelieveIt :)");

                }
            } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "RubberBalloon")) {
                int chance = random.nextInt(14) + 1;
                event.setCancelled(true);
                if (skillsSql.checkRubberBalloon(player)) {
                    player.sendMessage(ChatColor.GREEN + "You have already mastered RubberBalloon Drill");
                    return;
                } else if (!skillsSql.checkWaterBalloon(player)) {
                    player.sendMessage(ChatColor.RED + "You haven't mastered WaterBalloon drill");
                    return;
                }
                if (chance == 7) {
                    skillsSql.giveRubberBalloonSkill(player);
                } else {
                    player.sendMessage(ChatColor.DARK_AQUA + "Failed, don't loose hope until you reach your destini, To Become A Great Ninja, BelieveIt :)");
                }
            } else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "AirBalloon")) {
                int chance = random.nextInt(18) + 1;
                event.setCancelled(true);
                if (skillsSql.checkAirBalloon(player)) {
                    player.sendMessage(ChatColor.GREEN + "You have already mastered AirBalloon Drill");
                    return;
                } else if (!skillsSql.checkWaterBalloon(player)) {
                    player.sendMessage(ChatColor.RED + "You have to first master WaterBalloon");
                    return;
                } else if (!skillsSql.checkRubberBalloon(player)) {
                    player.sendMessage(ChatColor.RED + "You have to first master RubberBalloon");
                }
                if (chance == 9) {
                    skillsSql.giveAirBalloonSkill(player);
                } else {
                    player.sendMessage(ChatColor.DARK_AQUA + "Failed, don't loose hope until you reach your destini, To Become A Great Ninja, BelieveIt :)");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred in eggsMaster");
        }
    }

    // when toads bread is consumed
    @EventHandler
    public void onBreadConsume(PlayerItemConsumeEvent event){
        ItemStack item = event.getItem();
        if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Toad's Bread")){
            for (PotionEffect i : event.getPlayer().getActivePotionEffects()){
                event.getPlayer().removePotionEffect(i.getType());
            }
        }
    }
}

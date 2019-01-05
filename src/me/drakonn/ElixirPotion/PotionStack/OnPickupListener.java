package me.drakonn.ElixirPotion.PotionStack;

import me.drakonn.ElixirPotion.ElixirPotion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.HashMap;
import java.util.UUID;

public class OnPickupListener implements Listener
{
    private HashMap<UUID, Boolean> autoStack;

    private PotionStack potionStack;
    private ElixirPotion plugin;
    public OnPickupListener(ElixirPotion plg) {
        plugin = plg;
        autoStack = plugin.autoStack;
        potionStack = new PotionStack();
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (autoStack.get(player.getUniqueId()) == null)
            return;

        if(!autoStack.get(player.getUniqueId()))
            return;

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->
        {
            potionStack.replacePotions(player.getInventory());
            player.updateInventory();
        }, 2);
    }



}

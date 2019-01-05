package me.drakonn.ElixirPotion;

import me.drakonn.ElixirPotion.PotionCombine.PotionCombineInvListener;
import me.drakonn.ElixirPotion.PotionStack.OnPickupListener;
import me.drakonn.ElixirPotion.PotionStack.PotionStackCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class ElixirPotion extends JavaPlugin
{
    private OnPickupListener opl = new OnPickupListener(this);
    private PotionCombineInvListener pcil = new PotionCombineInvListener();
    private Storage storage = new Storage(this);
    public HashMap<UUID, Boolean> autoStack = new HashMap<>();

    public void onEnable()
    {
        saveDefaultConfig();
        storage.setData();
        registerCommand();
        registerListeners();
    }


    private void registerCommand()
    {
        this.getCommand("pot").setExecutor (new PotionStackCommand(this));
    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(opl, this);
        getServer().getPluginManager().registerEvents(pcil, this);
    }
}

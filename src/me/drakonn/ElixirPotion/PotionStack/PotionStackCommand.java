package me.drakonn.ElixirPotion.PotionStack;

import me.drakonn.ElixirPotion.ElixirPotion;
import me.drakonn.ElixirPotion.Storage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PotionStackCommand implements CommandExecutor
{
    private String noPermission;

    private HashMap<UUID, Boolean> autoStack;
    private PotionStack potionStack;
    public PotionStackCommand(ElixirPotion plugin)
    {
        noPermission = Storage.getNoPermission();
        autoStack = plugin.autoStack;
        potionStack = new PotionStack();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
            return true;

        Player player = ((Player) sender);

        if (args.length > 0 && args[0].equalsIgnoreCase("help"))
        {
            for (String helpMessage : Storage.getHelp())
            {
                player.sendMessage(helpMessage);
            }
            return true;
        }

        if (!sender.hasPermission("elixirpotions.stack"))
        {
            sender.sendMessage(noPermission);
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("auto"))
        {
            if (!sender.hasPermission("elixirpotions.stack.auto"))
            {
                sender.sendMessage(noPermission);
                return true;
            }

            if (autoStack.get(player.getUniqueId()) != null)
            {
                autoStack.put(player.getUniqueId(), !autoStack.get(player.getUniqueId()));
            }
            else
            {
                autoStack.put(player.getUniqueId(), true);
            }

            if (autoStack.get(player.getUniqueId()))
            {
                potionStack.replacePotions(player.getInventory());
            }

            sender.sendMessage(Storage.getPotionToggle().replace("{{state}}", autoStack.get(player.getUniqueId()) ? "enabled" : "disabled"));
            return true;
        }

        if (args.length > 0)
        {
            sender.sendMessage(Storage.getUsage());
            return true;
        }

        if(args.length == 0)
        {
            potionStack.replacePotions(player.getInventory());
            player.sendMessage(Storage.getPotionsStacked());
        }

        return true;
    }



}

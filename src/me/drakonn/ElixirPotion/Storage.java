package me.drakonn.ElixirPotion;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;


public class Storage
{
    private static String noPermission;
    private static String potionToggle;
    private static String potionsStacked;
    private static String usage;
    private static String potionsCombined;
    private static String effectLimitReached;
    private static String elixirName;
    private static int maxStack;
    private static int effectLimit;
    private static List<String> help = new ArrayList<>();

    private ElixirPotion plugin;
    public Storage(ElixirPotion plugin)
    {
        this.plugin = plugin;
    }

    public void setData() {
        noPermission = ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("message.no-permission"));
        potionToggle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.toggle-potion-stack"));
        potionsStacked = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.potions-stacked"));
        usage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.usage"));
        potionsCombined = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.potions-combined"));
        effectLimitReached = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.effect-limit-reached"));
        elixirName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("elixir-name"));
        maxStack = plugin.getConfig().getInt("max");
        effectLimit = plugin.getConfig().getInt("effect-limit");
        help.add(ChatColor.translateAlternateColorCodes('&', "&a/pot &8- &fStack all potions in your inventory"));
        help.add(ChatColor.translateAlternateColorCodes('&', "&a/pot auto &8- &fAutomatically stack recieved potions in your inventory, along with the current potions you have."));
    }

    public static List<String> getHelp() {
        return help;
    }

    public static String getPotionsCombined() {
        return potionsCombined;
    }

    public static String getEffectLimitReached() {
        return effectLimitReached;
    }

    public static String getElixirName() {
        return elixirName;
    }

    public static int getEffectLimit() {
        return effectLimit;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static String getPotionToggle() {
        return potionToggle;
    }

    public static String getPotionsStacked() {
        return potionsStacked;
    }

    public static String getUsage() {
        return usage;
    }

    public static int getMaxStack() {
        return maxStack;
    }
}

package me.drakonn.ElixirPotion.PotionCombine;

import java.util.ArrayList;
import java.util.Collection;
import me.drakonn.ElixirPotion.Storage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionCombineInvListener implements Listener
{
    private String elixirName;
    private String effectLimitReached;
    private String potionsCombined;
    private int effectLimit;

    @EventHandler
    public void onInventorryClickEvent(InventoryClickEvent event)
    {
        if ((event.getCursor() == null) || (event.getCurrentItem() == null) || (event.getCursor().getType() != Material.POTION) || (event.getCurrentItem().getType() != Material.POTION))
            return;
        Player player = (Player)event.getWhoClicked();
        if (!player.hasPermission("elixirpotions.combine"))
            return;

        elixirName = Storage.getElixirName();
        effectLimitReached = Storage.getEffectLimitReached();
        potionsCombined = Storage.getPotionsCombined();
        effectLimit = Storage.getEffectLimit();

        Potion pot1 = Potion.fromItemStack(event.getCurrentItem());
        Potion pot2 = Potion.fromItemStack(event.getCursor());

        Collection<PotionEffectType> effects = new ArrayList<>();
        PotionMeta pot1Meta = (PotionMeta) event.getCurrentItem().getItemMeta();
        PotionMeta pot2Meta = (PotionMeta) event.getCursor().getItemMeta();

        for(PotionEffect effectOne : pot1.getEffects()) {
            PotionEffectType typeOne = effectOne.getType();
            effects.add(typeOne);
        }

        for (PotionEffect customEffectOne : pot1Meta.getCustomEffects()) {
            PotionEffectType customTypeOne = customEffectOne.getType();
            effects.add(customTypeOne);
        }

        for(PotionEffect effectTwo : pot2.getEffects()) {
            PotionEffectType typeTwo = effectTwo.getType();
            if(effects.contains(typeTwo)) {
                return;
            }
        }

        for(PotionEffect customEffectTwo : pot2Meta.getCustomEffects()) {
            PotionEffectType customTypeTwo = customEffectTwo.getType();
            if(effects.contains(customTypeTwo)) {
                return;
            }
        }


        if ((pot1.isSplash() != pot2.isSplash()))
            return;

        event.setCancelled(true);

        Collection<PotionEffect> pot1Effects = getEffects(event.getCurrentItem());
        Collection<PotionEffect> pot2Effects = getEffects(event.getCursor());
        Collection<PotionEffect> newPotEffects = new ArrayList();

        newPotEffects.addAll(pot1Effects);
        newPotEffects.addAll(pot2Effects);

        int newPotionAmount = MathFunctions.newPotAmount(event.getCursor().getAmount(), event.getCurrentItem().getAmount());


        ItemStack newPotion = new ItemStack(Material.POTION, newPotionAmount);
        PotionMeta newPotMeta = (PotionMeta)newPotion.getItemMeta();

        newPotMeta.setDisplayName(elixirName);

        if (newPotEffects.size() <= effectLimit)
        {
            for (PotionEffect effect : newPotEffects)
            {
                newPotMeta.addCustomEffect(effect, true);
            }
        }
        if(newPotEffects.size() > effectLimit)
        {
            Bukkit.broadcastMessage("to many effects");
            player.sendMessage(effectLimitReached);
            if(player.getGameMode() == GameMode.CREATIVE)
            {
                player.getInventory().addItem(event.getCursor());
            }
            return;
        }

        if(event.getCursor().getAmount() != event.getCurrentItem().getAmount())
        {
            int cursorAmount = MathFunctions.cursorAmount(event.getCursor().getAmount(), event.getCurrentItem().getAmount());
            int currentAmount = MathFunctions.currentAmount(event.getCursor().getAmount(), event.getCurrentItem().getAmount());

            if (currentAmount != 0) {
                ItemStack currentItem = event.getCurrentItem();
                currentItem.setAmount(currentAmount);
                player.getInventory().addItem(currentItem);
                player.updateInventory();
            }

            if (cursorAmount != 0) {
                ItemStack cursorItem = event.getCursor();
                cursorItem.setAmount(cursorAmount);
                player.getInventory().addItem(cursorItem);
                player.updateInventory();
            }
        }

        Potion pot = new Potion(1);

        if (pot1.isSplash())
        {
            pot.setSplash(true);
        }
        pot.setType(PotionType.getByEffect(PotionEffectType.WATER_BREATHING));
        pot.apply(newPotion);
        newPotion.setItemMeta(newPotMeta);

        PotionMeta newPotionMeta = (PotionMeta)newPotion.getItemMeta();
        newPotionMeta.removeCustomEffect(PotionEffectType.WATER_BREATHING);
        newPotion.setItemMeta(newPotionMeta);

        event.setCursor(null);
        event.setCurrentItem(newPotion);
        player.updateInventory();
        player.sendMessage(potionsCombined);

    }


    public Collection<PotionEffect> getEffects(ItemStack potion)
    {
        Collection<PotionEffect> effects = new ArrayList();
        PotionMeta potionMeta = (PotionMeta)potion.getItemMeta();
        Potion pot = Potion.fromItemStack(potion);
        if (!potionMeta.getCustomEffects().isEmpty())
        {
            effects.addAll(potionMeta.getCustomEffects());
            return effects;
        }
        if(!pot.getEffects().isEmpty())
        {
            PotionEffectType potionEffectType = pot.getType().getEffectType();
            Integer potAmp = PotionData.getPotionAmplifier(potionEffectType, pot.getLevel());
            int potionDuration = PotionData.getPotionDuration(potionEffectType, pot.hasExtendedDuration(), pot.getLevel(), pot.isSplash());
            PotionEffect potionEffect = new PotionEffect(potionEffectType, potionDuration * 20, potAmp);
            effects.add(potionEffect);
            return effects;
        }
        return effects;
    }
}
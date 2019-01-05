package me.drakonn.ElixirPotion.PotionStack;

import me.drakonn.ElixirPotion.Storage;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class PotionStack
{
	private int maxStack = Storage.getMaxStack();
	private String elixirName = Storage.getElixirName();

	public Inventory replacePotions(Inventory inv)
	{
		Map<Short, Integer> normal = new HashMap<>();
		Map<Collection<PotionEffect>, Integer> combined = new HashMap<>();

		ItemStack[] c = inv.getContents();
		for (ItemStack stack : c)
		{
			if (stack != null && stack.getType() == Material.POTION)
			{
				Short du = stack.getDurability();
                PotionMeta effectmeta = (PotionMeta) stack.getItemMeta();
				Collection<PotionEffect> effects = effectmeta.getCustomEffects();

                if (!effects.isEmpty() && effectmeta.getDisplayName() != null && effectmeta.getDisplayName().equals(elixirName))
                {
                    if (!combined.isEmpty())
                    {
                        int breakPoint = combined.keySet().size();
                        int i = 0;
                        for (Collection<PotionEffect> storedEffects : combined.keySet())
                        {
                            i++;
                            if(storedEffects.size() == effects.size() && storedEffects.containsAll(effects))
                            {
                                combined.replace(storedEffects, combined.get(storedEffects) + stack.getAmount());
                                break;
                            }
                            else
                            {
                                if(i == breakPoint)
                                {
                                    combined.put(effects, stack.getAmount());
                                }
                            }
                        }
                    }
                    else
                    {
                        combined.put(effects, stack.getAmount());
                    }
                    inv.remove(stack);
                }
                else
                {
                    if (normal.containsKey(du))
                    {
                        normal.replace(du, normal.get(du) + stack.getAmount());
                    }
                    else
                    {
                        normal.put(du, stack.getAmount());
                    }
                    inv.remove(stack);
                }
			}
		}

		for (Short s : normal.keySet())
		{
			if (normal.get(s) > maxStack)
			{
				int timesOver = (int) Math.floor(normal.get(s) / maxStack);
				for (int i = 0; i < timesOver; i++) inv.addItem(new ItemStack(Material.POTION, maxStack, s));
				int leftOver = normal.get(s) - (timesOver * maxStack);
				if (leftOver > 0) inv.addItem(new ItemStack(Material.POTION, leftOver, s));
			} else inv.addItem(new ItemStack(Material.POTION, normal.get(s), s));
		}

        for (Collection<PotionEffect> effects : combined.keySet())
        {
            ItemStack potionItem = new ItemStack(Material.POTION, 1);
            PotionMeta potionMeta = (PotionMeta) potionItem.getItemMeta();
            potionMeta.setDisplayName(elixirName);
            for(PotionEffect effect : effects)
            {
                potionMeta.addCustomEffect(effect, true);
            }
            Potion potion = Potion.fromItemStack(potionItem);
            for (ItemStack stack : c)
            {
                if(stack != null && stack.getType() == Material.POTION)
                {
                    Potion pot = Potion.fromItemStack(stack);
                    PotionMeta potMeta = (PotionMeta) stack.getItemMeta();
                    if (pot.isSplash() && potMeta.getCustomEffects().size() == effects.size() && potMeta.getCustomEffects().containsAll(effects))
                    {
                        potion.setSplash(true);
                    }
                }
            }

            potion.apply(potionItem);
            potionItem.setItemMeta(potionMeta);
            if (combined.get(effects) > maxStack)
            {
                int timesOver = (int) Math.floor(combined.get(effects) / maxStack);
                potionItem.setAmount(maxStack);
                for (int i = 0; i < timesOver; i++) inv.addItem(potionItem);
                int leftOver = combined.get(effects) - (timesOver * maxStack);
                potionItem.setAmount(leftOver);
                if (leftOver > 0) inv.addItem(potionItem);
            }
            else
            {
                potionItem.setAmount(combined.get(effects));
                inv.addItem(potionItem);
            }
        }

		normal.clear();
		combined.clear();

		return inv;
	}

}

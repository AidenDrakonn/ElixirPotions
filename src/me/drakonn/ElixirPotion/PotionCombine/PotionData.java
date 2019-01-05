package me.drakonn.ElixirPotion.PotionCombine;

import org.bukkit.potion.PotionEffectType;

public class PotionData
{
    public static int getPotionDuration (PotionEffectType effect, Boolean isExtended, Integer level, Boolean isSplash)
    {
        if(effect.equals(PotionEffectType.REGENERATION))
        {
            if(isSplash)
            {
                if (level >= 2)
                {
                    return 17;
                }
                if (isExtended)
                {
                    return  90;
                }
                if (!isExtended)
                {
                    return 34;
                }
            }
            if (level >= 2)
            {
                return 23;
            }
            if (isExtended)
            {
                return 120;
            }
            if (!isExtended)
            {
                return 45;
            }

        }

        if(effect.equals(PotionEffectType.SPEED))
        {
            if(isSplash)
            {
                if (level >= 2)
                {
                    return 68;
                }
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if (level >= 2)
            {
                return 90;
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.FIRE_RESISTANCE))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.NIGHT_VISION))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.INCREASE_DAMAGE))
        {
            if(isSplash)
            {
                if (level >= 2)
                {
                    return 68;
                }
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }

            if (level >= 2)
            {
                return 90;
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.JUMP))
        {
            if(isSplash)
            {
                if (level >= 2)
                {
                    return 68;
                }
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if (level >= 2)
            {
                return 90;
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.WATER_BREATHING))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.INVISIBILITY))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 360;
                }
                if (!isExtended)
                {
                    return 135;
                }
            }
            if(isExtended)
            {
                return 480;
            }
            if(!isExtended)
            {
                return 180;
            }
        }

        if(effect.equals(PotionEffectType.POISON))
        {
            if(isSplash)
            {
                if (level >= 2)
                {
                    return 17;
                }
                if (isExtended)
                {
                    return 90;
                }
                if (!isExtended)
                {
                    return 34;
                }
            }

            if (level >= 2)
            {
                return 22;
            }
            if(isExtended)
            {
                return 120;
            }
            if(!isExtended)
            {
                return 45;
            }
        }

        if(effect.equals(PotionEffectType.WEAKNESS))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 180;
                }
                if (!isExtended)
                {
                    return 68;
                }
            }
            if(isExtended)
            {
                return 240;
            }
            if(!isExtended)
            {
                return 90;
            }
        }

        if(effect.equals(PotionEffectType.SLOW))
        {
            if(isSplash)
            {
                if (isExtended)
                {
                    return 180;
                }
                if (!isExtended)
                {
                    return 68;
                }
            }
            if(isExtended)
            {
                return 240;
            }
            if(!isExtended)
            {
                return 90;
            }
        }

      return 1;
    }

    public static int getPotionAmplifier(PotionEffectType effect, Integer potionLevel)
    {
        if(effect == PotionEffectType.REGENERATION)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.SPEED)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.FIRE_RESISTANCE)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.NIGHT_VISION)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.INCREASE_DAMAGE)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.JUMP)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.WATER_BREATHING)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.INVISIBILITY)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.POISON)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.WEAKNESS)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.SLOW)
        {
            if (potionLevel.equals(2))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.HARM)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        if(effect == PotionEffectType.HEAL)
        {
            if (potionLevel.equals(2))
            {
                return 1;
            }
            if (potionLevel.equals(1))
            {
                return 0;
            }
        }

        return 0;
    }
}

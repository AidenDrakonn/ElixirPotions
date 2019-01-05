package me.drakonn.ElixirPotion.PotionCombine;

public class MathFunctions
{

    public static int compareInt (Integer a, Integer b)
    {
        if (b > a )
        {
            return b;
        }

        if (a > b)
        {
            return a;
        }

        if (a.equals(b))
        {
            return 0;
        }

        return 1000;

    }


  public static int newPotAmount (Integer cursorAmount, Integer currentAmount)
  {
      if(MathFunctions.compareInt(cursorAmount,currentAmount) == 0)
      {
          return currentAmount;
      }

      if(MathFunctions.compareInt(cursorAmount, currentAmount) == cursorAmount)
      {
          return currentAmount;
      }

      if(MathFunctions.compareInt(cursorAmount, currentAmount) == currentAmount)
      {
          return cursorAmount;
      }

      return 0;
  }



    public static int cursorAmount (Integer cursorAmount, Integer currentAmount)
    {
        if(MathFunctions.compareInt(cursorAmount,currentAmount) == 0)
        {
            return 0;
        }

        if(MathFunctions.compareInt(cursorAmount, currentAmount) == cursorAmount)
        {
            return cursorAmount - currentAmount;
        }

        if(MathFunctions.compareInt(cursorAmount, currentAmount) == currentAmount)
        {
            return 0;
        }

        return 0;
    }





    public static int currentAmount (Integer cursorAmount, Integer currentAmount)
    {
        if(MathFunctions.compareInt(cursorAmount,currentAmount) == 0)
        {
            return 0;
        }

        if(MathFunctions.compareInt(cursorAmount, currentAmount) == cursorAmount)
        {
            return 0;
        }

        if(MathFunctions.compareInt(cursorAmount, currentAmount) == currentAmount)
        {
            return currentAmount - cursorAmount;
        }

        return 0;
    }

}

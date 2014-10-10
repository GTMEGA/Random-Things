package lumien.randomthings.Handler;

import net.minecraft.client.Minecraft;
import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.Handler.Bloodmoon.ClientBloodmoonHandler;
import lumien.randomthings.Handler.Bloodmoon.ServerBloodmoonHandler;

public class LightmapHandler
{
	static final float sinMax = (float) (Math.PI / 12000d);

	public static int manipulateRed(int originalValue)
	{
		if (VanillaChanges.HARDCORE_DARKNESS)
		{
			return Math.max(originalValue - 14, 0);
		}
		else
		{
			return originalValue;
		}
	}

	public static int manipulateGreen(int originalValue)
	{
		if (ClientBloodmoonHandler.INSTANCE.isBloodmoonActive() || VanillaChanges.HARDCORE_DARKNESS)
		{
			if (VanillaChanges.HARDCORE_DARKNESS)
			{
				originalValue -= 14;
			}

			if (ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBloodmoonHandler.INSTANCE.lightSub;
			}

			return Math.max(originalValue, 0);
		}
		else
		{
			return originalValue;
		}
	}

	public static int manipulateBlue(int originalValue)
	{
		if (ClientBloodmoonHandler.INSTANCE.isBloodmoonActive() || VanillaChanges.HARDCORE_DARKNESS)
		{
			if (VanillaChanges.HARDCORE_DARKNESS)
			{
				originalValue -= 14;
			}

			if (ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBloodmoonHandler.INSTANCE.lightSub;
			}

			return Math.max(originalValue, 0);
		}
		else
		{
			return originalValue;
		}
	}
}

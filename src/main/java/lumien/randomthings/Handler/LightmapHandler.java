package lumien.randomthings.Handler;

import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.Handler.RTMoonHandler.Bloodmoon.ClientBloodmoonHandler;
import lumien.randomthings.Handler.RTMoonHandler.Bluemoon.ClientBluemoonHandler;

public class LightmapHandler
{
	static final float sinMax = (float) (Math.PI / 12000d);

	public static int manipulateRed(int originalValue)
	{
		if (ClientBluemoonHandler.INSTANCE.isBluemoonActive() || VanillaChanges.HARDCORE_DARKNESS)
		{
			if (VanillaChanges.HARDCORE_DARKNESS)
			{
				originalValue -= 14;
			}

			if (Settings.BLUEMOON_VISUAL_BLUELIGHT && ClientBluemoonHandler.INSTANCE.isBluemoonActive() && !ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBluemoonHandler.INSTANCE.lightSub*1.9f;
			}

			if (Settings.BLOODMOON_VISUAL_REDLIGHT && Settings.BLUEMOON_VISUAL_BLUELIGHT && ClientBluemoonHandler.INSTANCE.isBluemoonActive() && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBluemoonHandler.INSTANCE.lightSub*1.5f;
			}

			return Math.max(originalValue, 0);
		}
		else
		{
			return originalValue;
		}
	}

	public static int manipulateGreen(int originalValue)
	{
		if (ClientBloodmoonHandler.INSTANCE.isBloodmoonActive() || ClientBluemoonHandler.INSTANCE.isBluemoonActive() || VanillaChanges.HARDCORE_DARKNESS)
		{
			if (VanillaChanges.HARDCORE_DARKNESS)
			{
				originalValue -= 14;
			}

			if (Settings.BLOODMOON_VISUAL_REDLIGHT && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive() && !ClientBluemoonHandler.INSTANCE.isBluemoonActive())
			{
				originalValue -= ClientBloodmoonHandler.INSTANCE.lightSub;
			}

			if (Settings.BLUEMOON_VISUAL_BLUELIGHT && ClientBluemoonHandler.INSTANCE.isBluemoonActive() && !ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBluemoonHandler.INSTANCE.lightSub*1.5f;
			}

			if (Settings.BLOODMOON_VISUAL_REDLIGHT && Settings.BLUEMOON_VISUAL_BLUELIGHT && ClientBluemoonHandler.INSTANCE.isBluemoonActive() && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBluemoonHandler.INSTANCE.lightSub*1.9f;
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

			if (Settings.BLOODMOON_VISUAL_REDLIGHT && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive() && !ClientBluemoonHandler.INSTANCE.isBluemoonActive())
			{
				originalValue -= ClientBloodmoonHandler.INSTANCE.lightSub*1.9f;
			}

			if (Settings.BLOODMOON_VISUAL_REDLIGHT && Settings.BLUEMOON_VISUAL_BLUELIGHT && ClientBluemoonHandler.INSTANCE.isBluemoonActive() && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive())
			{
				originalValue -= ClientBluemoonHandler.INSTANCE.lightSub*1.5f;
			}

			return Math.max(originalValue, 0);
		}
		else
		{
			return originalValue;
		}
	}
}

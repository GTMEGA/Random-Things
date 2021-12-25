package lumien.randomthings.Handler.RTMoonHandler.Bluemoon;

import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.RandomThings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ClientBluemoonHandler
{
	public static final ClientBluemoonHandler INSTANCE = new ClientBluemoonHandler();
	boolean blueMoon;

	public static final float sinMax = (float) (Math.PI / 12000d);

	public float lightSub = 150;
	public float fogRemove;
	private float skyColorAdd;
	private float moonColorBlue;

	static float d = 1f / 15000f;
	static int difTime = 0;

	private ClientBluemoonHandler()
	{
		blueMoon = false;
	}

	public void tick(World worldObj)
	{
		if (blueMoon)
		{
			if (Minecraft.getMinecraft().theWorld != null)
			{
				difTime = (int) (Minecraft.getMinecraft().theWorld.getWorldTime() % 24000) - 12000;
				lightSub = (float) Math.max(Math.sin(difTime * sinMax) * 150f, 0);
				skyColorAdd = (float) (Math.sin(difTime * sinMax) * 0.1f);
				moonColorBlue = (float) (Math.sin(difTime * sinMax) * 0.7f);

				fogRemove = (float) (Math.sin(difTime * sinMax) * d * 6000f);

				if (worldObj.provider.dimensionId != 0)
				{
					blueMoon = false;
				}
			}
		}
	}

	public static void moonColorHook()
	{
		if (Settings.BLUEMOON_VISUAL_BLUEMOON && ClientBluemoonHandler.INSTANCE.blueMoon)
		{
			if(RandomThings.proxy.isBloodmoon()){
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			} else {
				GL11.glColor3f(0, 0, 0.8f);
			}
		}
	}

	public static void skyColorHook(Vec3 color)
	{
		if (Settings.BLUEMOON_VISUAL_BLUESKY && ClientBluemoonHandler.INSTANCE.blueMoon)
		{
			color.zCoord += INSTANCE.skyColorAdd;
		}
	}

	public void setBluemoon(boolean blueMoon)
	{
		this.blueMoon = blueMoon;
	}

	public boolean isBluemoonActive()
	{
		return blueMoon;
	}
}

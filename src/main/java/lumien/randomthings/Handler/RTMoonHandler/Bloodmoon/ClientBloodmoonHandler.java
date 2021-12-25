package lumien.randomthings.Handler.RTMoonHandler.Bloodmoon;

import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.RandomThings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class ClientBloodmoonHandler
{
	public static final ClientBloodmoonHandler INSTANCE = new ClientBloodmoonHandler();
	boolean bloodMoon;

	public static final float sinMax = (float) (Math.PI / 12000d);

	public float lightSub = 150;
	public float fogRemove;
	private float skyColorAdd;
	private float moonColorRed;

	static float d = 1f / 15000f;
	static int difTime = 0;

	private ClientBloodmoonHandler()
	{
		bloodMoon = false;
	}

	public void tick(World worldObj)
	{
		if (bloodMoon)
		{
			if (Minecraft.getMinecraft().theWorld != null)
			{
				difTime = (int) (Minecraft.getMinecraft().theWorld.getWorldTime() % 24000) - 12000;
				lightSub = (float) Math.max(Math.sin(difTime * sinMax) * 150f, 0);
				skyColorAdd = (float) (Math.sin(difTime * sinMax) * 0.1f);
				moonColorRed = (float) (Math.sin(difTime * sinMax) * 0.7f);

				fogRemove = (float) (Math.sin(difTime * sinMax) * d * 6000f);

				if (worldObj.provider.dimensionId != 0)
				{
					bloodMoon = false;
				}
			}
		}
	}

	public static void moonColorHook()
	{
		if (Settings.BLOODMOON_VISUAL_REDMOON && ClientBloodmoonHandler.INSTANCE.bloodMoon)
		{
			if(RandomThings.proxy.isBluemoon()){
				GL11.glColor3f(0.1f, 0.1f, 0.1f);
			} else {
				GL11.glColor3f(0.8f, 0, 0);
			}
		}
	}

	public static void skyColorHook(Vec3 color)
	{
		if (Settings.BLOODMOON_VISUAL_REDSKY && ClientBloodmoonHandler.INSTANCE.bloodMoon)
		{
			color.xCoord += INSTANCE.skyColorAdd;
		}
	}

	public void setBloodmoon(boolean bloodMoon)
	{
		this.bloodMoon = bloodMoon;
	}

	public boolean isBloodmoonActive()
	{
		return bloodMoon;
	}
}

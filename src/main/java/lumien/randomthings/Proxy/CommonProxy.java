package lumien.randomthings.Proxy;

import java.util.ArrayList;

import lumien.randomthings.Entity.EntitySoul;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommonProxy
{
	public int itemCollectorRenderType;
	private static final MinecraftServer server = MinecraftServer.getServer();

	public boolean canBeCollidedWith(EntitySoul soul)
	{
		return false;
	}
	
	public void setContainerProperty(int index,int value)
	{
		
	}

	public void registerRenderers()
	{

	}

	public void spawnPfeilParticle(double x, double y, double z, double motionX, double motionY, double motionZ)
	{

	}

	public void spawnColoredDust(double x, double y, double z, double motionX, double motionY, double motionZ, float red, float green, float blue)
	{

	}

	public void registerTickHandler()
	{

	}

	public ArrayList<String> getUsernameList()
	{
		ArrayList<String> players = new ArrayList<String>();
		for (int i = 0; i < server.getConfigurationManager().playerEntityList.size(); ++i)
		{
			players.add(((EntityPlayerMP) server.getConfigurationManager().playerEntityList.get(i)).getCommandSenderName());
		}

		return players;
	}

	public void preInit()
	{
		registerTickHandler();
	}

	public void init()
	{
		registerRenderers();
	}

	public void postInit()
	{

	}
}

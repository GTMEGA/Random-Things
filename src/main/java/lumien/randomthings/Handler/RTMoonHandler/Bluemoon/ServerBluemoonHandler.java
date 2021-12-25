package lumien.randomthings.Handler.RTMoonHandler.Bluemoon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.Entity.EntityBlueWolf;
import lumien.randomthings.Handler.RTMoonHandler.Bloodmoon.ServerBloodmoonHandler;
import lumien.randomthings.Library.PotionEffects;
import lumien.randomthings.Library.RTMobSpawner;
import lumien.randomthings.Network.Messages.MessageBluemoon;
import lumien.randomthings.Network.PacketHandler;
import lumien.randomthings.RandomThings;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;

import java.util.List;

public class ServerBluemoonHandler extends WorldSavedData
{
	public static ServerBluemoonHandler INSTANCE;

	private RTMobSpawner blueMoonSpawner;

	boolean blueMoon;
	boolean forceBlueMoon;

	public ServerBluemoonHandler()
	{
		super("Bluemoon");
		blueMoonSpawner = new RTMobSpawner();
		blueMoon = false;
		forceBlueMoon = false;
	}

	public ServerBluemoonHandler(String name)
	{
		super("Bluemoon");
		blueMoonSpawner = new RTMobSpawner();
		blueMoon = false;
		forceBlueMoon = false;
	}

	public void playerJoinedWorld(EntityPlayer player)
	{
		if (blueMoon)
		{
			PacketHandler.INSTANCE.sendTo(new MessageBluemoon(blueMoon), (EntityPlayerMP) player);
		}
	}

	public void endWorldTick(World world)
	{
		if (world.provider.dimensionId == 0)
		{
			int time = (int) (world.getWorldTime() % 24000);
			if (blueMoon)
			{
				if (!Settings.BLUEMOON_RESPECT_GAMERULE || world.getGameRules().getGameRuleBooleanValue("doMobSpawning"))
				{
					for (int i = 0; i < Settings.BLUEMOON_SPAWNSPEED; i++)
					{
						blueMoonSpawner.findChunksForMobSpawning((WorldServer) world,  EntityBlueWolf.class, entityBlueWolf -> {
									if(Math.random() >= 0.8) {
										entityBlueWolf.addPotionEffect(new PotionEffect(PotionEffects.INVISIBILITY, 12000, 1, false));
									}
								},
								world.getTotalWorldTime() % 40L == 0, Settings.BLUEMOON_SPAWNLIMIT_MULTIPLIER, Settings.BLUEMOON_SPAWNRANGE, Settings.BLUEMOON_VANISH, "bluemoonSpawned");
					}
				}

				if (time >= 0 && time < 12000)
				{
					setBluemoon(false);
				}
			}
			else
			{
				if (time == 12000)
				{
					//TODO Handle Dreadmoonm
					if (forceBlueMoon || Math.random() < Settings.BLUEMOON_CHANCE && world.getCurrentMoonPhaseFactor() > 0.9f)
					{
						forceBlueMoon = false;
						setBluemoon(true);

						if (Settings.BLUEMOON_MESSAGE)
						{
							for (EntityPlayer player : ((List<EntityPlayer>) world.playerEntities))
							{
								player.addChatMessage(new ChatComponentTranslation("text.bluemoon.notify", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE)));
							}
						}
					}
				}
			}
		}
	}

	private void setBluemoon(boolean blueMoon)
	{
		if (this.blueMoon != blueMoon)
		{
			PacketHandler.INSTANCE.sendToDimension(new MessageBluemoon(blueMoon), 0);
			this.markDirty();
		}
		this.blueMoon = blueMoon;
	}

	public void force()
	{
		forceBlueMoon = true;
		this.markDirty();
	}

	public boolean isBluemoonActive()
	{
		return blueMoon;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		this.blueMoon = nbt.getBoolean("blueMoon");
		this.forceBlueMoon = nbt.getBoolean("forceBlueMoon");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("blueMoon", blueMoon);
		nbt.setBoolean("forceBlueMoon", forceBlueMoon);
	}

	public boolean isBluemoonScheduled()
	{
		return forceBlueMoon;
	}
}

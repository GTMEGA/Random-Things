package lumien.randomthings.Handler;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import lumien.randomthings.Configuration.RTBlockConfiguration;
import lumien.randomthings.Configuration.RTSettingsConfiguration;
import lumien.randomthings.TileEntities.TileEntityWirelessLever;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CoreHandler
{
	static Random rng = new Random();

	public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
	{
		if (RTSettingsConfiguration.FASTER_LEAFDECAY)
		{
			worldObj.scheduleBlockUpdate(posX, posY, posZ, block, RTSettingsConfiguration.DECAY_SPEED + (RTSettingsConfiguration.DECAY_FUZZ > 0 ? rng.nextInt(RTSettingsConfiguration.DECAY_FUZZ) : 0));
			return;
		}
	}

	public static boolean isBlockIndirectlyGettingPowered(World worldObj, int posX, int posY, int posZ)
	{
		if (RTBlockConfiguration.wirelessLever)
		{
			return TileEntityWirelessLever.isPowered(worldObj, posX, posY, posZ);
		}
		else
		{
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public static int getColorFromItemStack(ItemStack is, int renderPass)
	{
		if (is.stackTagCompound != null)
		{
			if (is.stackTagCompound.hasKey("customRTColor"))
			{
				return is.stackTagCompound.getInteger("customRTColor");
			}
		}
		return 16777215;
	}
}

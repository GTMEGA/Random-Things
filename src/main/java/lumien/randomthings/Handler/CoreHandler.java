package lumien.randomthings.Handler;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import lumien.randomthings.Configuration.ConfigBlocks;
import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.TileEntities.TileEntityWirelessLever;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CoreHandler
{
	static Random rng = new Random();

	public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
	{
		if (VanillaChanges.FASTER_LEAVEDECAY)
		{
			worldObj.scheduleBlockUpdate(posX, posY, posZ, block, Settings.DECAY_SPEED + (Settings.DECAY_FUZZ > 0 ? rng.nextInt(Settings.DECAY_FUZZ) : 0));
			return;
		}
	}

	public static boolean isBlockIndirectlyGettingPowered(World worldObj, int posX, int posY, int posZ)
	{
		if (ConfigBlocks.wirelessLever)
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

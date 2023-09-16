package lumien.randomthings.Blocks.Spectre;

import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import lumien.randomthings.Blocks.BlockBase;

public class BlockSpectreGlass extends BlockBase
{
	public BlockSpectreGlass()
	{
		super("spectreGlass", Material.ground);

		this.setStepSound(soundTypeGlass);
		this.setHardness(0.3f);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public int getRenderColor(int meta)
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		return 16777215;
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
	{
		AxisAlignedBB axisalignedbb1 = this.getCollisionBoundingBoxFromPool(worldIn, x, y, z);

		if (axisalignedbb1 != null && mask.intersectsWith(axisalignedbb1) && (entity instanceof EntityPlayer))
		{
			list.add(axisalignedbb1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess ba, int x, int y, int z, int side)
	{
		Block block = ba.getBlock(x, y, z);

		if (block == this)
		{
			return false;
		}
		else return ba.isAirBlock(x, y, z) || !block.isOpaqueCube();
	}
}

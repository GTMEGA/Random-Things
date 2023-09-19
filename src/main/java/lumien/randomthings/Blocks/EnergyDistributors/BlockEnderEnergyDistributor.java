package lumien.randomthings.Blocks.EnergyDistributors;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Blocks.BlockContainerBase;
import lumien.randomthings.Library.GuiIds;
import lumien.randomthings.TileEntities.EnergyDistributors.TileEntityEnderEnergyDistributor;
import lumien.randomthings.TileEntities.EnergyDistributors.TileEntityEnergyDistributor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockEnderEnergyDistributor extends BlockContainerBase
{
	@SideOnly(Side.CLIENT)
	IIcon top;
	@SideOnly(Side.CLIENT)
	IIcon sides;

	public BlockEnderEnergyDistributor()
	{
		super("enderEnergyDistributor", Material.rock);

		this.blockHardness = 3.0F;
	}

	@Override
	protected <T extends TileEntity> Class getTileEntityClass()
	{
		return TileEntityEnderEnergyDistributor.class;
	}

	@Override
	public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta)
	{
		TileEntityEnderEnergyDistributor distributor = (TileEntityEnderEnergyDistributor) worldIn.getTileEntity(x, y, z);

		if (distributor != null)
		{
			distributor.dropItems();
			worldIn.func_147453_f(x, y, z, blockBroken);
		}
		super.breakBlock(worldIn, x, y, z, blockBroken, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		top = ir.registerIcon("RandomThings:distributorEnder");
		sides = ir.registerIcon("RandomThings:distributorEnder_In");
	}

	@Override
	public boolean onBlockActivated(World worldObj, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (!worldObj.isRemote)
		{
			player.openGui(RandomThings.instance, GuiIds.ENDER_ENERGY_DISTRIBUTOR, worldObj, posX, posY, posZ);
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ba, int posX, int posY, int posZ, int side)
	{
		if (side == 1)
		{
			return top;
		}
		else
		{
			return sides;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1)
		{
			return top;
		}
		else
		{
			return sides;
		}
	}
}

package lumien.randomthings.Blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Blocks.ItemBlocks.ItemBlockWirelessLever;
import lumien.randomthings.Library.RenderIds;
import lumien.randomthings.TileEntities.TileEntityWirelessLever;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWirelessLever extends BlockLever implements ITileEntityProvider
{
	public BlockWirelessLever()
	{
		this.setCreativeTab(RandomThings.creativeTab);
		this.setBlockName("wirelessLever");
		this.isBlockContainer = true;

		this.setHardness(0.5f);
		this.setStepSound(soundTypeWood);

		this.setBlockTextureName("RandomThings:wirelessLever");
		GameRegistry.registerBlock(this, ItemBlockWirelessLever.class, "wirelessLever");
	}

	@Override
	public int getRenderType()
	{
		return RenderIds.WIRELESS_LEVER;
	}

	@Override
	public boolean onBlockActivated(World worldObj, int posX, int posY, int posZ, EntityPlayer player, int side, float subX, float subY, float subZ)
	{
		boolean activate = super.onBlockActivated(worldObj, posX, posY, posZ, player, side, subX, subY, subZ);

		TileEntityWirelessLever te = (TileEntityWirelessLever) worldObj.getTileEntity(posX, posY, posZ);
		if (te != null)
			te.updateTarget();

		return activate;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, int x, int y, int z, int side)
	{
		return 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess worldIn, int x, int y, int z, int side)
	{
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityWirelessLever();
	}

	@Override
	public void breakBlock(World worldObj, int posX, int posY, int posZ, Block block, int metadata)
	{
		TileEntityWirelessLever te = (TileEntityWirelessLever) worldObj.getTileEntity(posX, posY, posZ);
		if (te != null)
		{
			ItemStack is = new ItemStack(this, 1, 0);
			is.stackTagCompound = new NBTTagCompound();
			is.stackTagCompound.setBoolean("hasTarget", true);
			is.stackTagCompound.setInteger("targetX", te.getTargetX());
			is.stackTagCompound.setInteger("targetY", te.getTargetY());
			is.stackTagCompound.setInteger("targetZ", te.getTargetZ());
			this.dropBlockAsItem(worldObj, posX, posY, posZ, is);
			super.breakBlock(worldObj, posX, posY, posZ, block, metadata);
			worldObj.removeTileEntity(posX, posY, posZ);
		}
	}

	@Override
	public boolean onBlockEventReceived(World worldIn, int x, int y, int z, int eventId, int eventData)
	{
		super.onBlockEventReceived(worldIn, x, y, z, eventId, eventData);
		TileEntity tileentity = worldIn.getTileEntity(x, y, z);
		return tileentity != null ? tileentity.receiveClientEvent(eventId, eventData) : false;
	}
}

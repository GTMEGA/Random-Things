package lumien.randomthings.Blocks;

import java.util.Random;

import lumien.randomthings.RandomThings;
import lumien.randomthings.Configuration.RTSettingsConfiguration;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFertilizedDirt extends BlockBase
{
	boolean tilled;

	protected BlockFertilizedDirt(boolean tilled)
	{
		super("fertilizedDirt" + (tilled ? "_tilled" : ""), Material.ground);

		this.tilled = tilled;
		this.setTickRandomly(true);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGravel);

		if (tilled)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
			this.setLightOpacity(255);
		}

		if (!tilled)
		{
			this.setCreativeTab(RandomThings.creativeTab);
		}
		else
		{
			this.setCreativeTab(null);
		}

		this.setBlockTextureName("RandomThings:fertilizedDirt");
	}
	
	@Override
    public boolean renderAsNormalBlock()
    {
        return !tilled;
    }

	@Override
	public String getUnlocalizedName()
	{
		return "tile.fertilizedDirt";
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
	{
		return AxisAlignedBB.getBoundingBox(x + 0, y + 0, z + 0, x + 1, y + 1, z + 1);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(ModBlocks.fertilizedDirt);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return !tilled;
	}

	@Override
	public boolean isFertile(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

		switch (plantType)
		{
			case Desert:
				return !tilled;
			case Nether:
				return false;
			case Crop:
				return tilled;
			case Cave:
				return !tilled;
			case Plains:
				return !tilled;
			case Water:
				return false;
			case Beach:
				return !tilled;
		}

		return false;
	}

	@Override
	public void updateTick(World par1World, int posX, int posY, int posZ, Random rng)
	{
		if (!par1World.isRemote)
		{
			Block toBoost = par1World.getBlock(posX, posY + 1, posZ);
			if (toBoost != null && toBoost != Blocks.air && toBoost instanceof IPlantable)
			{
				if (RTSettingsConfiguration.FERTILIZED_DIRT_GROWTH_INDICATOR)
				{
					par1World.playAuxSFX(2005, posX, posY + 1, posZ, 0);
				}
			}
			for (int i = 0; i < RTSettingsConfiguration.FERTILIZED_DIRT_GROWTH; i++)
			{
				toBoost = par1World.getBlock(posX, posY + 1, posZ);
				if (toBoost != null && toBoost != Blocks.air && toBoost instanceof IPlantable)
				{
					toBoost.updateTick(par1World, posX, posY + 1, posZ, rng);
				}
			}
		}
	}

}

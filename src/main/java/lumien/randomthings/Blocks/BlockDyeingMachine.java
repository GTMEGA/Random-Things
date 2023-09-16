package lumien.randomthings.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Library.GuiIds;
import lumien.randomthings.TileEntities.TileEntityDyeingMachine;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDyeingMachine extends BlockContainerBase
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconSide;

	public BlockDyeingMachine()
	{
		super("dyeingMachine", Material.wood);

		this.setBlockTextureName("RandomThings:dyeingMachine/dyeingMachine");
		this.setStepSound(soundTypeWood);
		this.setHardness(0.7F);
	}

	@Override
	protected <T extends TileEntity> Class getTileEntityClass()
	{
		return TileEntityDyeingMachine.class;
	}

	@Override
	public boolean onBlockActivated(World worldObj, int poxX, int poxY, int poxZ, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (!worldObj.isRemote)
		{
			par5EntityPlayer.openGui(RandomThings.instance, GuiIds.DYEING_MACHINE, worldObj, poxX, poxY, poxZ);
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? this.iconTop : (side == 0 ? Blocks.planks.getIcon(side, 1) : (side != 2 && side != 4 ? this.iconSide : this.iconFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.iconSide = reg.registerIcon(this.getTextureName() + "_side");
		this.iconTop = reg.registerIcon(this.getTextureName() + "_top");
		this.iconFront = reg.registerIcon(this.getTextureName() + "_front");
	}
}

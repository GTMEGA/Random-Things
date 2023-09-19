package lumien.randomthings.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import lumien.randomthings.RandomThings;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.world.World;


public class BlockBloodmoonSensor extends BlockDaylightDetector
{
	public BlockBloodmoonSensor()
	{
		this.setCreativeTab(RandomThings.creativeTab);
		
		this.setBlockName("bloodMoonSensor");
		
		GameRegistry.registerBlock(this,"bloodMoonSensor");
		this.setBlockTextureName("RandomThings:bloodMoonSensor");
		
		this.setHardness(0.7f);
	}

	@Override
	public void func_149957_e(World worldObj, int posX, int posY, int posZ)
	{

	}
}

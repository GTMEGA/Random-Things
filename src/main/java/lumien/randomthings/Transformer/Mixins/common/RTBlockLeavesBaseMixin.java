package lumien.randomthings.Transformer.Mixins.common;

import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(BlockLeavesBase.class)
public abstract class RTBlockLeavesBaseMixin extends Block {
    protected RTBlockLeavesBaseMixin(Material materialIn) {
        super(materialIn);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        CoreHandler.handleLeaveDecay(worldIn, x, y, z, (Block)(Object)this);
    }
}

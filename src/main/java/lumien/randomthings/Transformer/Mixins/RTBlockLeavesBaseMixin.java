package lumien.randomthings.Transformer.Mixins;

import com.sun.xml.internal.bind.v2.TODO;
import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockLeavesBase.class)
public abstract class RTBlockLeavesBaseMixin {
    //TODO: doesnt work
    @Intrinsic()
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
        CoreHandler.handleLeaveDecay(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, (Block)(Object)this);
    }
}

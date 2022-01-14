package lumien.randomthings.Transformer.Mixins;

import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class RTWorldMixin {
    @Inject(method = "isBlockIndirectlyGettingPowered(III)Z",
            at = @At("HEAD"),
            cancellable = true,
            require = 1)
    public void isBlockIndirectlyGettingRTPowered(int p_72864_1_, int p_72864_2_, int p_72864_3_, CallbackInfoReturnable<Boolean> cir){
        World world = (World) (Object) this;
        if(CoreHandler.isBlockIndirectlyGettingPowered(world, p_72864_1_, p_72864_2_, p_72864_3_)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    @ModifyVariable(method = "getSunBrightnessBody",
                    at = @At(value = "STORE",
                             ordinal = 5),
                    remap = false,
                    ordinal = 2,
                    require = 1)
    //f2 = (float)((double)f2 * (1.0D - (double)(this.getWeightedThunderStrength(p_72971_1_) * 5.0F) / 16.0D)); <-modifies this line, right before the return statement
    private float rtHardcoreDarkness(float f2) {
        return VanillaChanges.HARDCORE_DARKNESS ? ((f2 - 0.2F) / 0.8F) : f2;
    }
}
package lumien.randomthings.Transformer.Mixins;

import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

    @Inject(method = "getSunBrightnessBody(F)F",
            at = @At("TAIL"),
            cancellable = true,
            remap = false,
            require = 1)
    private void rtHardcoreDarkness(float var, CallbackInfoReturnable<Float> cir) {
        if (VanillaChanges.HARDCORE_DARKNESS) {
            cir.setReturnValue((var - 0.2f) / 0.8f);
            cir.cancel();
        }
    }
}
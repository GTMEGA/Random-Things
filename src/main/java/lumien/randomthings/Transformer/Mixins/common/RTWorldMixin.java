package lumien.randomthings.Transformer.Mixins.common;

import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class RTWorldMixin {
    @Inject(method = "isBlockIndirectlyGettingPowered(III)Z", at = @At("HEAD"), cancellable = true, require = 1)
    public void isBlockIndirectlyGettingRTPowered(int x, int y, int z, CallbackInfoReturnable<Boolean> cir){
        World world = (World) (Object) this;
        if(CoreHandler.isBlockIndirectlyGettingPowered(world, x, y, z)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    /*@Inject(method = "getSunBrightnessBody(F)F", at = @At("TAIL"), cancellable = true, remap = false, require = 1, locals = LocalCapture.CAPTURE_FAILHARD)
    private void rtHardcoreDarkness(float var, CallbackInfoReturnable<Float> cir, float f1, float f2) {

        int mode = RTHDConfiguration.HD_MODE;
        float ret = f2 * 0.8F + 0.2F;
        switch (mode) {
            case 0:
                ret = f2;
                break;
            case 2:
                float var2 =  0.7F + (10.0F - Minecraft.getMinecraft().theWorld.getCurrentMoonPhaseFactorBody() * 10.0F) * 0.03F;
                float var3 = 1.0F - var2;
                ret = f2 * var2 + var3;
                break;
        }

        cir.setReturnValue(ret);
        cir.cancel();
    }*/
}
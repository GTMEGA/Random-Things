package lumien.randomthings.Transformer.Mixins;

import lumien.randomthings.Configuration.VanillaChanges;
import lumien.randomthings.Handler.LightmapHandler;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderer.class)
public class RTEntityRendererMixin {
        @ModifyVariable(method = "updateLightmap",
                at = @At(value = "STORE"),
                ordinal = 1
        )
        private int rtManipulateRed(int val) {
                return LightmapHandler.manipulateRed(val);
        }
        @ModifyVariable(method = "updateLightmap",
                at = @At(value = "STORE"),
                ordinal = 2
        )
        private int rtManipulateGreen(int val) {
                return LightmapHandler.manipulateGreen(val);
        }
        @ModifyVariable(method = "updateLightmap",
                at = @At(value = "STORE"),
                ordinal = 3
        )
        private int rtManipulateBlue(int val) {
                return LightmapHandler.manipulateBlue(val);
        }

        @ModifyVariable(method = "updateLightmap",
                at = @At(value = "STORE"),
                ordinal = 1)
        private float rtHardcoreDarkness(float value) {
                if (VanillaChanges.HARDCORE_DARKNESS) {
                        return (value - 0.05F) / 0.95F;
                } else {
                        return value;
                }
        }
}

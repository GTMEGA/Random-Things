package lumien.randomthings.Transformer.Mixins;

import lumien.randomthings.Handler.Bloodmoon.ClientBloodmoonHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public class RTRenderGlobalMixin {
    @Redirect(method = "renderSky",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;getSkyColor(Lnet/minecraft/entity/Entity;F)Lnet/minecraft/util/Vec3;"),
            require = 1)
    private Vec3 rtSkyColorHook(WorldClient theWorld, Entity renderViewEntity, float v) {
        Vec3 vec3 = theWorld.getSkyColor(renderViewEntity, v);
        ClientBloodmoonHandler.skyColorHook(vec3);
        return vec3;
    }

    @Inject(method="renderSky",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V", ordinal = 2, shift = At.Shift.AFTER),
            require = 1)
    private void rtMoonColorHook(float tessellator, CallbackInfo ci){
        ClientBloodmoonHandler.moonColorHook();
    }
}


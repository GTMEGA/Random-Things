package lumien.randomthings.Transformer.Mixins.common;

import lumien.randomthings.Handler.CoreHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class RTItemMixin {
@Inject(method = "getColorFromItemStack", at = @At("TAIL"), cancellable = true, require = 1)
public void getRTColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_, CallbackInfoReturnable<Integer> cir)
{
    cir.setReturnValue(CoreHandler.getColorFromItemStack(p_82790_1_, p_82790_2_));
}
}



package lumien.randomthings.Transformer;

import com.falsepattern.lib.mixin.IMixin;
import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.*;

@RequiredArgsConstructor
public enum Mixin implements IMixin {

    RTBLOCKLEAVESBASEMIXIN(Side.COMMON, always(), "RTBlockLeavesBaseMixin"),
    RTITEMMIXIN(Side.CLIENT, always(), "RTItemMixin"),
    RTWORLDMIXIN(Side.COMMON, always(), "RTWorldMixin");


    @Getter
    public final Side side;
    @Getter
    public final Predicate<List<ITargetedMod>> filter;
    @Getter
    public final String mixin;
}
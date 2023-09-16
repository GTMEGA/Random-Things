package lumien.randomthings.Transformer;

import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@RequiredArgsConstructor
public enum TargetedMod implements ITargetedMod {

    //
    // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
    // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded classes!
    // Exception: Tags.java, as long as it is used for Strings only!
    //

    // Replace with your injected mods here:
    ;

    @Getter
    public final String modName;
    @Getter
    public final boolean loadInDevelopment;
    @Getter
    public final Predicate<String> condition;
}
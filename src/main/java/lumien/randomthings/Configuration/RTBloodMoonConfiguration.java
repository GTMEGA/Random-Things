package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lumien.randomthings.Library.Reference;

@Config.Synchronize
@Config(modid = Reference.MOD_ID, category = "bloodmoon")
public class RTBloodMoonConfiguration {
    @Config.DefaultInt(3)
    @Config.Comment("How much faster enemies spawn on a bloodmoon (0=Vanilla)")
    @Config.LangKey("config.randomthings.bloodmoon_spawnspeed")
    @Config.NoSync
    public static int BLOODMOON_SPAWNSPEED;

    @Config.DefaultInt(3)
    @Config.Comment("With which number should the default entity limit be multiplied on a blood moon")
    @Config.LangKey("config.randomthings.bloodmoon_spawnlimit_multiplier")
    @Config.NoSync
    public static int BLOODMOON_SPAWNLIMIT_MULTIPLIER;

    @Config.DefaultInt(4)
    @Config.Comment("How close can enemies spawn next to the player on a bloodmoon in blocks? (Vanilla=24)")
    @Config.LangKey("config.randomthings.bloodmoon_spawnrange")
    @Config.NoSync
    public static int BLOODMOON_SPAWNRANGE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether players are NOT allowed to sleep during a bloodmoon")
    @Config.LangKey("config.randomthings.bloodmoon_nosleep")
    public static boolean BLOODMOON_NOSLEEP;

    @Config.DefaultDouble(0.05)
    @Config.Comment("The chance of a bloodmoon happening (0=Never;1=Every full moon;0.05=5% of all full moons)")
    @Config.LangKey("config.randomthings.bloodmoon_chance")
    @Config.NoSync
    public static double BLOODMOON_CHANCE;

    @Config.DefaultBoolean(false)
    @Config.Comment("Should monsters spawned by a bloodmoon vanish at dawn?")
    @Config.LangKey("config.randomthings.bloodmoon_vanish")
    @Config.NoSync
    public static boolean BLOODMOON_VANISH;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the Bloodmoon should respect the doMobSpawning gamerule")
    @Config.LangKey("config.randomthings.bloodmoon_respect_gamerule")
    @Config.NoSync
    public static boolean BLOODMOON_RESPECT_GAMERULE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether players in the overworld should receive a message at the beginning of the bloodmoon")
    @Config.LangKey("config.randomthings.")
    public static boolean BLOODMOON_MESSAGE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the moon will be red on a Bloodmoon")
    @Config.LangKey("config.randomthings.bloodmoon_visual_redmoon")
    public static boolean BLOODMOON_VISUAL_REDMOON;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether light will be tinted red on a Bloodmoon")
    @Config.LangKey("config.randomthings.bloodmoon_visual_redlight")
    public static boolean BLOODMOON_VISUAL_REDLIGHT;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether fog will turn black on a Bloodmoon to look better with the red sky")
    @Config.LangKey("config.randomthings.bloodmoon_visual_blackfog")
    public static boolean BLOODMOON_VISUAL_BLACKFOG;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the sky will turn red on a Bloodmoon")
    @Config.LangKey("config.randomthings.bloodmoon_visual_redsky")
    public static boolean BLOODMOON_VISUAL_REDSKY;

    static {
        ConfigurationManager.selfInit();
    }
}

package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lumien.randomthings.Library.Reference;

@Config.Synchronize
@Config(modid = Reference.MOD_ID, category = "bluemoon")
public class RTBlueMoonConfiguration {
    @Config.DefaultInt(2)
    @Config.Comment("How much faster enemies spawn on a bluemoon (0=Vanilla)")
    @Config.LangKey("config.randomthings.bluemoon_spawnspeed")
    @Config.NoSync
    public static int BLUEMOON_SPAWNSPEED;

    @Config.DefaultInt(2)
    @Config.Comment("With which number should the default entity limit be multiplied on a blood moon")
    @Config.LangKey("config.randomthings.bluemoon_spawnlimit_multiplier")
    @Config.NoSync
    public static int BLUEMOON_SPAWNLIMIT_MULTIPLIER;

    @Config.DefaultInt(4)
    @Config.Comment("How close can enemies spawn next to the player on a bluemoon in blocks? (Vanilla=24)")
    @Config.LangKey("config.randomthings.bluemoon_spawnrange")
    @Config.NoSync
    public static int BLUEMOON_SPAWNRANGE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether players are NOT allowed to sleep during a bluemoon")
    @Config.LangKey("config.randomthings.bluemoon_nosleep")
    public static boolean BLUEMOON_NOSLEEP;

    @Config.DefaultDouble(0.05)
    @Config.Comment("The chance of a bluemoon happening (0=Never;1=Every full moon;0.05=5% of all full moons)")
    @Config.LangKey("config.randomthings.bluemoon_chance")
    @Config.NoSync
    public static double BLUEMOON_CHANCE;

    @Config.DefaultBoolean(false)
    @Config.Comment("Should monsters spawned by a bluemoon vanish at dawn?")
    @Config.LangKey("config.randomthings.bluemoon_vanish")
    @Config.NoSync
    public static boolean BLUEMOON_VANISH;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the Bluemoon should respect the doMobSpawning gamerule")
    @Config.LangKey("config.randomthings.bluemoon_respect_gamerule")
    @Config.NoSync
    public static boolean BLUEMOON_RESPECT_GAMERULE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether players in the overworld should receive a message at the beginning of the bluemoon")
    @Config.LangKey("config.randomthings.")
    public static boolean BLUEMOON_MESSAGE;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the moon will be blue on a Bluemoon")
    @Config.LangKey("config.randomthings.bluemoon_visual_bluemoon")
    public static boolean BLUEMOON_VISUAL_BLUEMOON;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether light will be tinted blue on a Bluemoon")
    @Config.LangKey("config.randomthings.bluemoon_visual_bluelight")
    public static boolean BLUEMOON_VISUAL_BLUELIGHT;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether fog will turn black on a Bluemoon to look better with the blue sky")
    @Config.LangKey("config.randomthings.bluemoon_visual_blackfog")
    public static boolean BLUEMOON_VISUAL_BLACKFOG;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the sky will turn blue on a Bluemoon")
    @Config.LangKey("config.randomthings.bluemoon_visual_bluesky")
    public static boolean BLUEMOON_VISUAL_BLUESKY;

    static {
        ConfigurationManager.selfInit();
    }
}

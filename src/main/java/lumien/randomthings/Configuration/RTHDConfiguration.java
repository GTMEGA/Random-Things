package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lumien.randomthings.Library.Reference;

@Config.Synchronize
@Config(modid = Reference.MOD_ID, category = "hardcore darkness")
public class RTHDConfiguration {

    @Config.DefaultInt(0)
    @Config.Comment({"Hardcore Darkness mode",
                    "-1: disabled, 0: No minimum sky & block light, ",
                    "1: No minimum block light, 2: Skylight is dependent on moon phase"})
    @Config.LangKey("config.randomthings.hd_mode")
    public static int HD_MODE = 0;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the Nether is also supposed to have its minimum light removed")
    @Config.LangKey("config.randomthings.hd_dark_nether")
    public static boolean HD_DARK_NETHER = true;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether the End is also supposed to have its minimum light removed")
    @Config.LangKey("config.randomthings.hd_dark_end")
    public static boolean HD_DARK_END = false;

    @Config.DefaultBoolean(true)
    @Config.Comment("Whether it should be dark in the Twilight Forest")
    @Config.LangKey("config.randomthings.hd_dark_twilight")
    public static boolean HD_DARK_TWILIGHT = false;

    @Config.DefaultBoolean(true)
    @Config.Comment("Attempts to remove the blue sky light that occurs when using mode 1 or 2")
    @Config.LangKey("config.randomthings.hd_remove_blue")
    public static boolean HD_REMOVE_BLUE = true;

    @Config.DefaultString("")
    @Config.Comment({"A list of dimension ids in which Hardcore Darkness will be completely disabled",
                    "Example: S:\"Dimension Blacklist\"=-1,1\""})
    @Config.LangKey("config.randomthings.hd_dimension_blacklist")
    public static String HD_DIMENSION_BLACKLIST = "";

    @Config.DefaultInt(100)
    @Config.Comment({"Gamma value HD is gonna lock to (in percentage, 100 would be same as 1.0 in options.txt)",
                    "Set to 100 to let user configure this setting in-game."})
    @Config.LangKey("config.randomthings.hd_locked_gamma")
    public static int HD_LOCKED_GAMMA = 100;

    static {
        ConfigurationManager.selfInit();
    }
}

package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lumien.randomthings.Library.Reference;

@Config(modid = Reference.MOD_ID, category = "blocks")
public class RTBlockConfiguration
{
	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.playerInterface")
	public static boolean playerInterface;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.fluidDisplay")
	public static boolean fluidDisplay;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.fertilizedDirt")
	public static boolean fertilizedDirt;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.itemCollector")
	public static boolean itemCollector;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.onlineDetector")
	public static boolean onlineDetector;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.moonSensor")
	public static boolean moonSensor;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.notificationInterface")
	public static boolean notificationInterface;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.lapisLamp")
	public static boolean lapisLamp;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.wirelessLever")
	public static boolean wirelessLever;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.dyeingMachine")
	public static boolean dyeingMachine;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.spectreGlass")
	public static boolean spectreGlass;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.energyDistributor")
	public static boolean energyDistributor;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.enderEnergyDistributor")
	public static boolean enderEnergyDistributor;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.bloodMoonSensor")
	public static boolean bloodMoonSensor;

	static {
		ConfigurationManager.selfInit();
	}
}

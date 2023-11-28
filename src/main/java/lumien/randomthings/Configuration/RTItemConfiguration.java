package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lombok.SneakyThrows;
import lumien.randomthings.Library.Reference;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Transformer.MCPNames;
import net.minecraft.potion.Potion;
import org.apache.logging.log4j.Level;

import java.util.Arrays;

@Config(modid = Reference.MOD_ID, category = "items")
public class RTItemConfiguration
{
	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.biomeCapsule")
	public static boolean biomeCapsule;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.biomePainter")
	public static boolean biomePainter;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.whitestone")
	public static boolean whitestone;

	//TODO: this item is not done yet. Work on it confirming ideas with Houston.
	// So idea is to make this like single use TPA to your friends
	// Also add an admin version that can teleport to any player (incl logged off)
	@Config.DefaultBoolean(false)
	@Config.LangKey("config.randomthings.magneticForce")
	public static boolean magneticForce;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.creativeSword")
	public static boolean creativeSword;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.creativeGrower")
	public static boolean creativeGrower;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.voidStone")
	public static boolean voidStone;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.dropFilter")
	public static boolean dropFilter;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.enderLetter")
	public static boolean enderLetter;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.creativeChestGenerator")
	public static boolean creativeChestGenerator;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.spectreKey")
	public static boolean spectreKey;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.soundRecorder")
	public static boolean soundRecorder;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.spectreArmor")
	public static boolean spectreArmor;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.spectreSword")
	public static boolean spectreSword;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.spiritBinder")
	public static boolean spiritBinder;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.imbue")
	public static boolean imbue;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.opSpectreKey")
	public static boolean opSpectreKey;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.ginto")
	public static boolean ginto;

	@Config.DefaultBoolean(true)
	@Config.LangKey("config.randomthings.bloodStone")
	public static boolean bloodStone;

	static {
		ConfigurationManager.selfInit();
	}
}
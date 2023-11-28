package lumien.randomthings.Potions;

import java.util.Arrays;

import lombok.SneakyThrows;
import lumien.randomthings.Configuration.RTSettingsConfiguration;
import org.apache.logging.log4j.Level;

import lumien.randomthings.RandomThings;
import lumien.randomthings.Transformer.MCPNames;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Property;

import static lumien.randomthings.Configuration.RTSettingsConfiguration.validatePotionID;

public class ModPotions
{
	public static Potion imbuePoison;
	public static Potion imbueExperience;
	public static Potion imbueFire;
	public static Potion imbueWither;
	public static Potion imbueWeakness;
	public static Potion imbueSpectre;

	public static int[] imbueColors = new int[] { 10145074, 14214234, 16729344, 3881787, 16777215, 12578815 };

	public static void init()
	{
		imbuePoison = getImbue("poison", imbueColors[0]);
		imbueExperience = getImbue("experience", imbueColors[1]);
		imbueFire = getImbue("fire", imbueColors[2]);
		imbueWither = getImbue("wither", imbueColors[3]);
		imbueWeakness = getImbue("weakness", imbueColors[4]);
		imbueSpectre = getImbue("spectre", imbueColors[5]);
	}

	@SneakyThrows
	private static PotionImbue getImbue(String name, int color)
	{
		return new PotionImbue("imbue." + name, validatePotionID(("IMBUE_" + name).toUpperCase()), color, new ResourceLocation("randomthings:textures/gui/imbueEffects/" + name + ".png"));
	}
}

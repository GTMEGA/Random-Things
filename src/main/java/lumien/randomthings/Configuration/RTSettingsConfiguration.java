package lumien.randomthings.Configuration;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import lombok.SneakyThrows;
import lumien.randomthings.Library.Reference;
import lumien.randomthings.RandomThings;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.DimensionManager;
import org.apache.logging.log4j.Level;
import scala.annotation.meta.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Config.Synchronize
@Config(modid = Reference.MOD_ID, category = "settings")
public class RTSettingsConfiguration {
    @Config.DefaultBoolean(false)
    @Config.Comment("Bonemeal particles will appear whenever fertilized dirt boosts the plant")
    @Config.LangKey("config.randomthings.fertilized_dirt_growth_indicator")
    @Config.NoSync
    public static boolean FERTILIZED_DIRT_GROWTH_INDICATOR;

    @Config.DefaultDouble(0.02)
    @Config.Comment("The chance of a spirit spawning when you don't use a Spectre Sword (0-1)")
    @Config.LangKey("config.randomthings.spirit_chance")
    @Config.NoSync
    public static double SPIRIT_CHANCE;

    @Config.DefaultDouble(0.1)
    @Config.Comment("The chance of a spirit spawning when you have a spirit binder in your inventory and kill the entity with a spectre sword. (0-1)")
    @Config.LangKey("config.randomthings.spirit_chance_sword")
    @Config.NoSync
    public static double SPIRIT_CHANCE_SWORD;

    @Config.DefaultDouble(0.1)
    @Config.Comment("The chance of phasing through an attack while being imbued with spectre (0-1)")
    @Config.LangKey("config.randomthings.spectre_imbue_chance")
    @Config.NoSync
    public static double SPECTRE_IMBUE_CHANCE;

    @Config.DefaultInt(3)
    @Config.Comment("The chance of finding whitestone in dungeon loot")
    @Config.LangKey("config.randomthings.whitestone_chance")
    @Config.NoSync
    public static int WHITESTONE_CHANCE;

    @Config.DefaultDouble(10)
    @Config.Comment("The range of a wireless lever in blocks")
    @Config.LangKey("config.randomthings.wireless_lever_range")
    @Config.NoSync
    public static double WIRELESS_LEVER_RANGE = 10;

    @Config.DefaultInt(-1)
    @Config.Comment({"The Dimension ID of the spectre World.",
                    "On first run and when you set this setting to -1 RandomThings will try to find a dimensionID itself"})
    @Config.LangKey("config.randomthings.spectre_dimension_id")
    @Config.NoSync
    public static int SPECTRE_DIMENSON_ID;

    @Config.DefaultString("")
    @Config.Comment({"If this is not empty the options background will not be random but the one specified here.",
                    "This has to be a Resource Location, for more information visit the Curse Forge Page of Custom Backgrounds"})
    @Config.LangKey("config.randomthings.fixed_background")
    @Config.NoSync
    public static String FIXED_BACKGROUND = "";

    @Config.DefaultBoolean(true)
    @Config.LangKey("config.randomthings.faster_leafdecay")
    @Config.Comment("Whether or not enable faster leaf decay. Decay speed is controlled by Decay Speed and Fuzz")
    @Config.NoSync
    public static boolean FASTER_LEAFDECAY;

    @Config.DefaultInt(7)
    @Config.Comment("The amount of ticks every leave needs to decay (Lower is faster)")
    @Config.LangKey("config.randomthings.decay_speed")
    @Config.NoSync
    public static int DECAY_SPEED = 7;

    @Config.DefaultInt(5)
    @Config.Comment({"A random number from 0 to (this config value) will be added to the decay speed for every leaf block.",
            "Setting this to 0 will decay leaves rather linear while higher numbers will let the whole thing look more natural"})
    @Config.LangKey("config.randomthings.decay_fuzz")
    @Config.NoSync
    public static int DECAY_FUZZ = 5;

    @Config.DefaultBoolean(true)
    @Config.LangKey("config.randomthings.throwables_motion")
    @Config.Comment("Whether or not to add throwers/shooters movement velocity to throwable/arrow they throw/shoot")
    @Config.NoSync
    public static boolean THROWABLES_MOTION;

    @Config.DefaultBoolean(true)
    @Config.LangKey("config.randomthings.spectre_dimension_fog")
    public static boolean SPECTRE_DIMENSION_FOG;

    @Config.DefaultInt(3)
    @Config.Comment("How often should Fertilize Dirt tick the plant above it when it's ticked itself?")
    @Config.LangKey("config.randomthings.fertilized_dirt_growth")
    @Config.NoSync
    public static int FERTILIZED_DIRT_GROWTH;

    @Config.DefaultInt(200)
    @Config.Comment("How long does it take to make an imbue (in ticks)")
    @Config.LangKey("config.randomthings.imbuing_length")
    @Config.NoSync
    public static int IMBUING_LENGTH;

    @Config.DefaultInt(6000)
    @Config.Comment("The duration of imbues (in ticks)")
    @Config.LangKey("config.randomthings.imbue_duration")
    @Config.NoSync
    public static int IMBUE_DURATION;

    @Config.DefaultInt(20)
    @Config.Comment("The amount of ticks the biome capsule needs to collect 1 charge")
    @Config.LangKey("config.randomthings.biome_charge_time")
    @Config.NoSync
    public static int BIOME_CHARGE_TIME;

    @Config.DefaultDouble(10)
    @Config.LangKey("config.randomthings.demorpher_range")
    @Config.NoSync
    public static double DEMORPHER_RANGE;

    @Config.DefaultInt(400)
    @Config.Comment("Amount of RF distributed by Energy Distributor per tick.")
    @Config.LangKey("config.randomthings.energy_distributor_pertick")
    @Config.NoSync
    public static int ENERGY_DISTRIBUTOR_PERTICK;

    @Config.DefaultInt(60)
    @Config.Comment("Amount of RF distributed by Energy Distributor per machine.")
    @Config.LangKey("config.randomthings.energy_distributor_permachine")
    @Config.NoSync
    public static int ENERGY_DISTRIBUTOR_PERMACHINE  = 60;

    @Config.DefaultInt(15)
    @Config.Comment("Amount of machines that can be connected to Energy Distributor.")
    @Config.LangKey("config.randomthings.energy_distributor_maxmachines")
    @Config.NoSync
    public static int ENERGY_DISTRIBUTOR_MAXMACHINES;

    @Config.DefaultInt(1000000)
    @Config.Comment("Amount of RF Energy Distributor can hold in its internal buffer.")
    @Config.LangKey("config.randomthings.energy_distributor_buffersize")
    @Config.NoSync
    public static int ENERGY_DISTRIBUTOR_BUFFERSIZE;

    @Config.DefaultInt(800)
    @Config.Comment("Amount of RF distributed by Ender Energy Distributor per tick.")
    @Config.LangKey("config.randomthings.ender_energy_distributor_pertick")
    @Config.NoSync
    public static int ENDER_ENERGY_DISTRIBUTOR_PERTICK;

    @Config.DefaultInt(100)
    @Config.Comment("Amount of RF distributed by Ender Energy Distributor per machine.")
    @Config.LangKey("config.randomthings.ender_energy_distributor_permachine")
    @Config.NoSync
    public static int ENDER_ENERGY_DISTRIBUTOR_PERMACHINE;

    @Config.DefaultInt(1500000)
    @Config.Comment("Amount of RF Ender Energy Distributor can hold in its internal buffer.")
    @Config.LangKey("config.randomthings.ender_energy_distributor_buffersize")
    @Config.NoSync
    public static int ENDER_ENERGY_DISTRIBUTOR_BUFFERSIZE;

    @Config.DefaultInt(6)
    @Config.Comment("Energy Distributor range.")
    @Config.LangKey("config.randomthings.ender_energy_distributor_range")
    @Config.NoSync
    public static int ENDER_ENERGY_DISTRIBUTOR_RANGE;

    @Config.DefaultInt(30)
    @Config.Comment("Amount of machines that can be connected to Ender Energy Distributor.")
    @Config.LangKey("config.randomthings.ender_energy_distributor_maxmachines")
    @Config.NoSync
    public static int ENDER_ENERGY_DISTRIBUTOR_MAXMACHINES;

    @Config.DefaultInt(-1)
    @Config.Comment("Applies poison to mobs you attack")
    @Config.LangKey("config.randomthings.imbue_poison")
    @Config.NoSync
    public static int IMBUE_POISON;

    @Config.DefaultInt(-1)
    @Config.Comment("Increases xp dropped by mobs (most of the time)")
    @Config.LangKey("config.randomthings.imbue_experience")
    @Config.NoSync
    public static int IMBUE_EXPERIENCE;

    @Config.DefaultInt(-1)
    @Config.Comment("Sets mobs you attack on fire")
    @Config.LangKey("config.randomthings.imbue_fire")
    @Config.NoSync
    public static int IMBUE_FIRE;

    @Config.DefaultInt(-1)
    @Config.Comment("Applies withering to mobs you attack")
    @Config.LangKey("config.randomthings.imbue_wither")
    @Config.NoSync
    public static int IMBUE_WITHER;

    @Config.DefaultInt(-1)
    @Config.Comment("Applies weakness to mobs you attack")
    @Config.LangKey("config.randomthings.imbue_weakness")
    @Config.NoSync
    public static int IMBUE_WEAKNESS;

    @Config.DefaultInt(-1)
    @Config.Comment("Makes attacks phase through you (do no damage). Chance is controlled by separate config entry")
    @Config.LangKey("config.randomthings.imbue_spectre")
    @Config.NoSync
    public static int IMBUE_SPECTRE;


    @SneakyThrows
    public static int validatePotionID(String name) {
        Field field = RTSettingsConfiguration.class.getDeclaredField(name);
        if (field.get(null).equals(-1)) getPotionID(field);
        return field.getInt(null);
    }

    @SneakyThrows
    private static void getPotionID(Field field) {
        int ID = getFreePotionID();
        if (ID != -1) RandomThings.instance.logger.log(Level.INFO, "Auto Resolved " + field.getName() + " ID to " + ID);
        else RandomThings.instance.logger.log(Level.ERROR, "ID for " + field.getName() + " could not been resolved!");

        field.set(null, ID);
        saveConfiguration();
    }

    private static int getFreePotionID() {

        for (int i = 1; i <= Potion.potionTypes.length; i++)
        {
            if (Potion.potionTypes[i] == null)
            {
                return i;
            }
        }

        return -1;
    }

    @SneakyThrows
    public static int getSpectreDimensionID() {
        if (SPECTRE_DIMENSON_ID == -1)
        {
            int dimensionID = DimensionManager.getNextFreeDimId();
            Reference.LOGGER.log(Level.INFO, "Auto Resolved Spectre Dimension ID to " + dimensionID);

            SPECTRE_DIMENSON_ID = dimensionID;

            saveConfiguration();
        }

        return SPECTRE_DIMENSON_ID;
    }

    @SneakyThrows
    private static void saveConfiguration() {
        ConfigurationManager.saveToFile(true, RTSettingsConfiguration.class);
    }

    static {
        ConfigurationManager.selfInit();
    }
}

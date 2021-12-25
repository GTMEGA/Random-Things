package lumien.randomthings.Configuration;

public class Settings
{
	public static boolean FERTILIZEDDIRT_GROWTHINDICATOR = false;

	public static double SPIRIT_CHANCE = 0.02;
	public static double SPIRIT_CHANCE_SWORD = 0.1;

	public static double SPECTRE_IMBUE_CHANCE = 0.1;

	public static double WIRELESSLEVER_RANGE = 10;

	public static int SPECTRE_DIMENSON_ID;
	public static String FIXED_BACKGROUND = "";
	public static boolean SPECTRE_DIMENSION_FOG = true;

	public static int FERTILIZED_DIRT_GROWTH = 3;

	public static float IMBUING_LENGTH = 200;

	public static int IMBUE_DURATION = 20 * 60 * 5;

	public static int BIOME_CHARGE_TIME = 20;

	public static double DEMORPHER_RANGE = 10;

	public static int DECAY_SPEED = 7;

	public static int DECAY_FUZZ = 5;

	//region RT Moons
	public static int BLOODMOON_SPAWNSPEED = 3;
	public static int BLOODMOON_SPAWNLIMIT_MULTIPLIER = 3;
	public static int BLOODMOON_SPAWNRANGE = 4; // 24
	public static boolean BLOODMOON_NOSLEEP = true;
	public static float BLOODMOON_CHANCE = 0.15f;
	public static boolean BLOODMOON_VANISH = false;
	public static boolean BLOODMOON_RESPECT_GAMERULE = true;
	public static boolean BLOODMOON_MESSAGE = true;

	public static boolean BLOODMOON_VISUAL_REDMOON = true;
	public static boolean BLOODMOON_VISUAL_REDLIGHT = true;
	public static boolean BLOODMOON_VISUAL_BLACKFOG = true;
	public static boolean BLOODMOON_VISUAL_REDSKY = true;

	public static int BLUEMOON_SPAWNSPEED = 2;
	public static int BLUEMOON_SPAWNLIMIT_MULTIPLIER = 2;
	public static int BLUEMOON_SPAWNRANGE = 4; // 24
	public static boolean BLUEMOON_NOSLEEP = true;
	public static float BLUEMOON_CHANCE = 0.05f;
	public static boolean BLUEMOON_VANISH = true;
	public static boolean BLUEMOON_RESPECT_GAMERULE = true;
	public static boolean BLUEMOON_MESSAGE = true;

	public static boolean BLUEMOON_VISUAL_BLUEMOON = true;
	public static boolean BLUEMOON_VISUAL_BLUELIGHT = true;
	public static boolean BLUEMOON_VISUAL_BLACKFOG = true;
	public static boolean BLUEMOON_VISUAL_BLUESKY = true;
	//endregion RT Moons

	public static int ENERGY_DISTRIBUTOR_PERTICK = 400;
	public static int ENERGY_DISTRIBUTOR_PERMACHINE = 60;
	public static int ENERGY_DISTRIBUTOR_MAXMACHINES = 15;
	public static int ENERGY_DISTRIBUTOR_BUFFERSIZE = 1000000;

	public static int ENDER_ENERGY_DISTRIBUTOR_PERTICK = 800;
	public static int ENDER_ENERGY_DISTRIBUTOR_PERMACHINE = 100;
	public static int ENDER_ENERGY_DISTRIBUTOR_BUFFERSIZE = 1500000;
	public static int ENDER_ENERGY_DISTRIBUTOR_RANGE = 6;
	public static int ENDER_ENERGY_DISTRIBUTOR_MAXMACHINES = 30;
	public static int ENDER_ENERGY_DISTRIBUTOR_RANGE_SQRD = 36;
}

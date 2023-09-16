package lumien.randomthings.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Configuration.RTItemConfiguration;

public class ModItems
{
	public static ItemFilter filter;
	public static ItemBiomeCapsule biomeCapsule;
	public static ItemBiomePainter biomePainter;
	public static ItemIngredient ingredients;
	public static ItemWhiteStone whitestone;
	public static ItemMagneticForce magneticForce;
	public static ItemVoidStone voidStone;
	public static ItemDropFilter dropFilter;
	public static ItemEnderLetter enderLetter;
	public static ItemSpectreKey spectreKey;
	public static ItemOpSpectreKey opSpectreKey;
	public static ItemSoundRecorder soundRecorder;
	public static ItemImbue imbue;
	public static ItemBloodstone bloodStone;

	public static ItemCreativeSword creativeSword;
	public static ItemCreativeGrower creativeGrower;
	public static ItemCreativeChestGenerator creativeChestGenerator;

	public static ArmorMaterial spectreArmorMaterial = EnumHelper.addArmorMaterial("Spectre", 35, new int[] { 3, 9, 7, 3 }, 17);
	public static ItemSpectreArmor spectreHelmet;
	public static ItemSpectreArmor spectreChestplate;
	public static ItemSpectreArmor spectreLeggings;
	public static ItemSpectreArmor spectreBoots;
	public static ItemSpectreSword spectreSword;
	public static ItemSpiritBinder spiritBinder;
	public static ItemGinto ginto;

	public static void init()
	{
		RandomThings.instance.logger.debug("Initializing Items");

		if (RTItemConfiguration.biomeCapsule)
			biomeCapsule = new ItemBiomeCapsule();
		if (RTItemConfiguration.biomePainter)
			biomePainter = new ItemBiomePainter();
		if (RTItemConfiguration.whitestone)
			whitestone = new ItemWhiteStone();
		if (RTItemConfiguration.magneticForce)
			magneticForce = new ItemMagneticForce();
		if (RTItemConfiguration.voidStone)
			voidStone = new ItemVoidStone();
		if (RTItemConfiguration.dropFilter)
			dropFilter = new ItemDropFilter();
		if (RTItemConfiguration.enderLetter)
			enderLetter = new ItemEnderLetter();
		if (RTItemConfiguration.spectreKey)
			spectreKey = new ItemSpectreKey();
		if (RTItemConfiguration.opSpectreKey)
			opSpectreKey = new ItemOpSpectreKey();
		if (RTItemConfiguration.spectreArmor)
		{
			spectreHelmet = (ItemSpectreArmor) new ItemSpectreArmor(0).setUnlocalizedName("spectreHelmet").setTextureName("RandomThings:armor/spectreHelmet");
			spectreChestplate = (ItemSpectreArmor) new ItemSpectreArmor(1).setUnlocalizedName("spectreChestplate").setTextureName("RandomThings:armor/spectreChestplate");
			spectreLeggings = (ItemSpectreArmor) new ItemSpectreArmor(2).setUnlocalizedName("spectreLeggings").setTextureName("RandomThings:armor/spectreLeggings");
			spectreBoots = (ItemSpectreArmor) new ItemSpectreArmor(3).setUnlocalizedName("spectreBoots").setTextureName("RandomThings:armor/spectreBoots");
			GameRegistry.registerItem(spectreHelmet, "spectreHelmet");
			GameRegistry.registerItem(spectreChestplate, "spectreChestplate");
			GameRegistry.registerItem(spectreLeggings, "spectreLeggings");
			GameRegistry.registerItem(spectreBoots, "spectreBoots");
		}
		if (RTItemConfiguration.spectreSword)
			spectreSword = new ItemSpectreSword();
		if (RTItemConfiguration.spiritBinder)
			spiritBinder = new ItemSpiritBinder();
		if (RTItemConfiguration.imbue)
			imbue = new ItemImbue();
		if (RTItemConfiguration.bloodStone)
			bloodStone = new ItemBloodstone();

		if (RTItemConfiguration.creativeSword)
			creativeSword = new ItemCreativeSword();
		if (RTItemConfiguration.creativeGrower)
			creativeGrower = new ItemCreativeGrower();
		if (RTItemConfiguration.creativeChestGenerator)
			creativeChestGenerator = new ItemCreativeChestGenerator();
		if (RTItemConfiguration.soundRecorder)
			soundRecorder = new ItemSoundRecorder();
		if (RTItemConfiguration.ginto)
			ginto = new ItemGinto();

		ingredients = new ItemIngredient();
		filter = new ItemFilter();
	}
}

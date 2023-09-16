package lumien.randomthings.Blocks;

import lumien.randomthings.RandomThings;
import lumien.randomthings.Blocks.EnergyDistributors.BlockEnderEnergyDistributor;
import lumien.randomthings.Blocks.EnergyDistributors.BlockEnergyDistributor;
import lumien.randomthings.Blocks.Spectre.BlockSpectreBlock;
import lumien.randomthings.Blocks.Spectre.BlockSpectreGlass;
import lumien.randomthings.Configuration.RTBlockConfiguration;

public class ModBlocks
{
	public static BlockPlayerInterface playerInterface;
	public static BlockCreativePlayerInterface creativePlayerInterface;
	public static BlockFluidDisplay fluidDisplay;
	public static BlockFertilizedDirt fertilizedDirt, fertilizedDirtTilled;
	public static BlockItemCollector itemCollector;
	public static BlockAdvancedItemCollector advancedItemCollector;
	public static BlockOnlineDetector onlineDetector;
	public static BlockMoonSensor moonSensor;
	public static BlockBloodmoonSensor bloodMoonSensor;
	public static BlockNotificationInterface notificationInterface;
	public static BlockSpectreBlock spectreBlock;
	public static BlockLapisLamp spectreLamp;
	public static BlockWirelessLever wirelessLever;
	public static BlockDyeingMachine dyeingMachine;
	public static BlockImbuingStation imbuingStation;
	public static BlockAdvancedFluidDisplay advancedFluidDisplay;
	public static BlockSpectreGlass spectreGlass;
	
	public static BlockEnergyDistributor energyDistributor;
	public static BlockEnderEnergyDistributor enderEnergyDistributor;

	public static void init()
	{
		RandomThings.instance.logger.debug("Initializing Blocks");

		if (RTBlockConfiguration.playerInterface)
		{
			playerInterface = new BlockPlayerInterface();
			creativePlayerInterface = new BlockCreativePlayerInterface();
		}
		if (RTBlockConfiguration.fluidDisplay)
		{
			fluidDisplay = new BlockFluidDisplay();
			advancedFluidDisplay = new BlockAdvancedFluidDisplay();
		}
		if (RTBlockConfiguration.fertilizedDirt)
			fertilizedDirt = new BlockFertilizedDirt(false);
			fertilizedDirtTilled = new BlockFertilizedDirt(true);
		if (RTBlockConfiguration.itemCollector)
		{
			itemCollector = new BlockItemCollector();
			advancedItemCollector = new BlockAdvancedItemCollector();
		}
		if (RTBlockConfiguration.onlineDetector)
			onlineDetector = new BlockOnlineDetector();
		if (RTBlockConfiguration.moonSensor)
			moonSensor = new BlockMoonSensor();
		if (RTBlockConfiguration.bloodMoonSensor)
			bloodMoonSensor = new BlockBloodmoonSensor();
		if (RTBlockConfiguration.notificationInterface)
			notificationInterface = new BlockNotificationInterface();
		if (RTBlockConfiguration.lapisLamp)
			spectreLamp = new BlockLapisLamp();
		if (RTBlockConfiguration.wirelessLever)
			wirelessLever = new BlockWirelessLever();
		if (RTBlockConfiguration.dyeingMachine)
			dyeingMachine = new BlockDyeingMachine();
		if (RTBlockConfiguration.spectreGlass)
			spectreGlass = new BlockSpectreGlass();
		if (RTBlockConfiguration.energyDistributor)
			energyDistributor = new BlockEnergyDistributor();
		if (RTBlockConfiguration.enderEnergyDistributor)
			enderEnergyDistributor = new BlockEnderEnergyDistributor();

		// Always there
		spectreBlock = new BlockSpectreBlock();
		imbuingStation = new BlockImbuingStation();
	}
}

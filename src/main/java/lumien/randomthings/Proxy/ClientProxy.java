package lumien.randomthings.Proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lumien.randomthings.Client.Model.ModelBlueWolf;
import lumien.randomthings.Client.Renderer.*;
import lumien.randomthings.Configuration.RTHDConfiguration;
import lumien.randomthings.Entity.*;
import org.apache.logging.log4j.Level;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Client.ClientTickHandler;
import lumien.randomthings.Items.ItemGinto;
import lumien.randomthings.Items.ModItems;
import lumien.randomthings.Library.RenderIds;
import lumien.randomthings.Library.Interfaces.IContainerWithProperties;
import lumien.randomthings.TileEntities.TileEntityAdvancedItemCollector;
import lumien.randomthings.TileEntities.TileEntityItemCollector;
import lumien.randomthings.Transformer.MCPNames;

public class ClientProxy extends CommonProxy
{
	RenderItemCollector renderer;
	private static final Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
	}

	@Override
	public void setContainerProperty(int index, int value)
	{
		Container openContainer = Minecraft.getMinecraft().thePlayer.openContainer;
		if (openContainer != null && openContainer instanceof IContainerWithProperties)
		{
			IContainerWithProperties prop = (IContainerWithProperties) openContainer;
			prop.setValue(index, value);
		}
	}

	@Override
	public boolean canBeCollidedWith(EntitySoul soul)
	{
		ItemStack equipped = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
		if (equipped != null && (equipped.getItem() instanceof ItemGinto) && equipped.getItemDamage() == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void registerRenderers()
	{
		RenderIds.WIRELESS_LEVER = RenderingRegistry.getNextAvailableRenderId();

		renderer = new RenderItemCollector();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemCollector.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedItemCollector.class, renderer);

		RenderingRegistry.registerEntityRenderingHandler(EntitySpirit.class, new RenderSpirit(new ModelSlime(16), new ModelSlime(0), 0.25f));
		RenderingRegistry.registerEntityRenderingHandler(EntityHealingOrb.class, new RenderHealingOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, new RenderSoul());
		RenderingRegistry.registerEntityRenderingHandler(EntityReviveCircle.class, new RenderReviveCircle());
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodmoonCircle.class, new RenderBloodmoonCircle());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlueWolf.class, new RenderBlueWolf(new ModelBlueWolf(), new ModelBlueWolf(), 0.7F));
		
		RenderingRegistry.registerBlockHandler(new RenderWirelessLever());

		RenderRTItem rtRenderer = new RenderRTItem();
		MinecraftForgeClient.registerItemRenderer(ModItems.whitestone, rtRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.bloodStone, rtRenderer);
	}

	@Override
	public void spawnPfeilParticle(double x, double y, double z, double motionX, double motionY, double motionZ)
	{
		World worldObj = Minecraft.getMinecraft().theWorld;

		EntityCritFX particle = new EntityCritFX(worldObj, x, y, z, motionX, motionY, motionZ);
		particle.setRBGColorF(1F / 255F * 198F, 1F / 255F * 246F, 1F / 255F * 252F);
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

	@Override
	public void spawnColoredDust(double x, double y, double z, double motionX, double motionY, double motionZ, float red, float green, float blue)
	{
		EntityReddustFX particle = new EntityReddustFX(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0);
		particle.setRBGColorF(red, green, blue);
		particle.motionY = motionY;
		Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

	@Override
	public ArrayList<String> getUsernameList()
	{
		NetHandlerPlayClient nethandlerplayclient = ClientProxy.mc.thePlayer.sendQueue;
		List<GuiPlayerInfo> list = nethandlerplayclient.playerInfoList;
		ArrayList<String> players = new ArrayList<String>();
		for (GuiPlayerInfo info : list)
		{
			players.add(info.name);
		}

		return players;
	}

	private void lockGamma() {
		GameSettings.Options[] videoOptions = ReflectionHelper.getPrivateValue(GuiVideoSettings.class, null, MCPNames.field("field_146502_i"));
		ArrayList<GameSettings.Options> options = new ArrayList<GameSettings.Options>(Arrays.asList(videoOptions));

		Iterator<GameSettings.Options> iterator = options.iterator();
		while (iterator.hasNext())
		{
			GameSettings.Options option = iterator.next();
			if (option == GameSettings.Options.GAMMA)
			{
				iterator.remove();
			}
		}

		RandomThings.instance.logger.log(Level.INFO, "Removing Gamma from settings... (GammaLock is on)");
		GuiVideoSettings.videoOptions = options.toArray(videoOptions);
	}

	@Override
	public void postInit()
	{
		if (RTHDConfiguration.HD_LOCKED_GAMMA != 100)
		{
			lockGamma();
		}
	}
}

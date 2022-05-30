package lumien.randomthings.Items;

import lumien.randomthings.RandomThings;
import lumien.randomthings.Client.RenderUtils;
import lumien.randomthings.Configuration.Settings;
import lumien.randomthings.Library.GuiIds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

public class ItemOpSpectreKey extends ItemOp
{

	public ItemOpSpectreKey()
	{
		super("opSpectreKey");
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		RenderUtils.enableDefaultBlending();

		return super.getColorFromItemStack(par1ItemStack, par2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		if (player.worldObj.isRemote && count < 20)
		{
			EntityFX particle;
			float t = 1F / 255F;
			if (Minecraft.getMinecraft().thePlayer == player)
			{
				for (int i = 0; i < (40 - count); i++)
				{
					particle = new EntitySmokeFX(player.worldObj, player.posX + Math.random() * 2 - 1, player.posY - 1 + Math.random(), player.posZ + Math.random() * 2 - 1, 0, 0, 0);

					particle.setRBGColorF(t * 172F, t * 7F, t * 7F);
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
			}
			else
			{
				for (int i = 0; i < (40 - count); i++)
				{
					particle = new EntitySmokeFX(player.worldObj, player.posX + Math.random() * 2 - 1, player.posY + 0.5 + Math.random(), player.posZ + Math.random() * 2 - 1, 0, 0, 0);
					particle.setRBGColorF(t * 172F, t * 7F, t * 7F);
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
			}
		}
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			if(par3EntityPlayer.isSneaking())
			{
				RandomThings.instance.spectreHandler.teleportPlayerOutOfSpectreWorld((EntityPlayerMP) par3EntityPlayer);
				par3EntityPlayer.setSneaking(false);
			}
			else
			{
				par3EntityPlayer.openGui(RandomThings.instance, GuiIds.OP_SPECTRE_KEY, par2World, 0, 0, 0);
			}
		}
		return par1ItemStack;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		par3List.add("\u00A7c" + I18n.format("text.miscellaneous.opSpectreSneak"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId == Settings.SPECTRE_DIMENSON_ID;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 40;
	}

	/**
	 * Returns the action that specifies what animation to play when the items
	 * are being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
}

package lumien.randomthings.Items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumien.randomthings.Client.RenderUtils;
import lumien.randomthings.Configuration.RTSettingsConfiguration;
import lumien.randomthings.Library.PotionEffects;
import lumien.randomthings.Potions.ModPotions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemImbue extends ItemBase
{
	String[] names = new String[] { "poison", "experience", "fire", "wither", "weakness", "spectre" };
	IIcon[] icons;

	public ItemImbue()
	{
		super("imbue");
		this.setHasSubtypes(true);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is)
	{
		return EnumAction.drink;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		if (par1ItemStack.getItemDamage() == 1)
		{
			par3List.add("I will work with ANY entity as");
			par3List.add("soon as there are Forge Hooks for me");
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack is, int pass)
	{
		if (pass == 1)
		{
			return 16777215;
		}
		else
		{
			RenderUtils.enableDefaultBlending();
			return ModPotions.imbueColors[is.getItemDamage()];
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item item, CreativeTabs ct, List list)
	{
		for (int i = 0; i < names.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return icons[pass];
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return "item.imbue." + names[is.getItemDamage()];
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World worldObj, EntityPlayer player)
	{
		player.setItemInUse(is, this.getMaxItemUseDuration(is));
		return is;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		icons = new IIcon[2];
		icons[0] = ir.registerIcon("RandomThings:imbues/imbue_overlay");
		icons[1] = ir.registerIcon("RandomThings:imbues/imbue_base");
	}

	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
	{
		return 60;
	}

	private void addImbue(EntityPlayer player, PotionEffect pe)
	{
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_EXPERIENCE);
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_POISON);
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_FIRE);
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_WITHER);
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_WEAKNESS);
		player.removePotionEffect(RTSettingsConfiguration.IMBUE_SPECTRE);

		player.addPotionEffect(pe);
	}

	@Override
	public ItemStack onEaten(ItemStack is, World worldObj, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--is.stackSize;
		}

		int imbueID = PotionEffects.BLINDNESS;
		switch (is.getItemDamage())
		{
			case 0:
				imbueID = RTSettingsConfiguration.IMBUE_POISON;
				break;
			case 1:
				imbueID = RTSettingsConfiguration.IMBUE_EXPERIENCE;
				break;
			case 2:
				imbueID = RTSettingsConfiguration.IMBUE_FIRE;
				break;
			case 3:
				imbueID = RTSettingsConfiguration.IMBUE_WITHER;
				break;
			case 4:
				imbueID = RTSettingsConfiguration.IMBUE_WEAKNESS;
				break;
			case 5:
				imbueID = RTSettingsConfiguration.IMBUE_SPECTRE;
				break;
		}

		PotionEffect pe = new PotionEffect(imbueID, RTSettingsConfiguration.IMBUE_DURATION, 0, false);

		ArrayList<ItemStack> curatives = new ArrayList<ItemStack>();
		pe.setCurativeItems(curatives);

		addImbue(player, pe);

		if (!player.capabilities.isCreativeMode)
		{
			if (is.stackSize <= 0)
			{
				return new ItemStack(Items.glass_bottle);
			}

			player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}
		return is;
	}
}

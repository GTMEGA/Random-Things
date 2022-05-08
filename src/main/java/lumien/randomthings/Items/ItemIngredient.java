package lumien.randomthings.Items;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

public class ItemIngredient extends ItemBase
{
	private class Ingredient
	{
		IIcon icon;
		String iconName;
		String unlocalizedName;
		int maxStackSize;

		private Ingredient(String iconName, String unlocalizedName, int maxStackSize)
		{
			this.iconName = "RandomThings:crafting/" + iconName;
			this.unlocalizedName = unlocalizedName;
			this.maxStackSize = maxStackSize;
		}
	}

	ArrayList<Ingredient> ingredients;

	public ItemIngredient()
	{
		super("ingredient");
		ingredients = new ArrayList<Ingredient>();

		ingredients.add(new Ingredient("playerCore", "playerCore", 64));
		ingredients.add(new Ingredient("obsidianStick", "obsidianStick", 64));
		ingredients.add(new Ingredient("enderFragment", "enderFragment", 16));
		ingredients.add(new Ingredient("ectoplasm", "_ectoplasm", 64));
		ingredients.add(new Ingredient("spectreIron", "spectreIron", 64));
		ingredients.add(new Ingredient("transformationCore", "transformationCore", 1));
		ingredients.add(new Ingredient("enderDiamond", "enderDiamond", 64));
		ingredients.add(new Ingredient("energizedWater", "energizedWater", 16));

		OreDictionary.registerOre("stickObsidian", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("obsidianStick", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("obsidianRod", new ItemStack(this, 1, 1));

		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(this, 1, 4), 1, 1, 3));

		this.setHasSubtypes(true);
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if (par1ItemStack.getItemDamage() == 4)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		return 16777215;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List list)
	{
		for (int i = 0; i < ingredients.size(); i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public IIcon getIconFromDamage(int damage)
	{
		if (ingredients.size() - 1 < damage || damage < 0)
		{
			return ingredients.get(0).icon;
		}
		else
		{
			return ingredients.get(damage).icon;
		}
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		for (Ingredient i : ingredients)
		{
			i.icon = par1IconRegister.registerIcon(i.iconName);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int damage = par1ItemStack.getItemDamage();
		if (damage < 0 || damage > ingredients.size() - 1)
		{
			return "item.error";
		}
		return "item." + ingredients.get(damage).unlocalizedName;
	}

	@Override
	public int getItemStackLimit(ItemStack is)
	{
		int damage = is.getItemDamage();
		if (ingredients.size() - 1 < damage || damage < 0)
		{
			return 64;
		}
		else
		{
			return ingredients.get(damage).maxStackSize;
		}
	}
}

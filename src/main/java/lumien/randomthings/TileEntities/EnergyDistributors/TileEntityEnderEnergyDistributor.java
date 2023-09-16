package lumien.randomthings.TileEntities.EnergyDistributors;

import java.util.HashSet;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import lumien.randomthings.Configuration.RTSettingsConfiguration;
import lumien.randomthings.Items.ItemFilter;
import lumien.randomthings.Library.DimensionCoordinate;
import lumien.randomthings.Library.InventoryUtils;
import lumien.randomthings.Library.WorldUtils;
import lumien.randomthings.Library.Interfaces.IValidator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnderEnergyDistributor extends TileEntity implements IEnergyReceiver
{
	InventoryBasic targetSlots = new InventoryBasic("Targets", false, 8);

	protected HashSet<TileEntity> receiverCache;

	EnergyStorage buffer;

	int energyDistributedLastTick;
	int machinesConnected;

	IValidator validator = new IValidator()
	{
		@Override
		public boolean matches(Object o)
		{
			return o instanceof TileEntity & !(o instanceof TileEntityEnderEnergyDistributor) && o instanceof IEnergyReceiver;
		}
	};

	public TileEntityEnderEnergyDistributor()
	{
		super();
		buffer = new EnergyStorage(RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_BUFFERSIZE);
		receiverCache = new HashSet<TileEntity>();
		machinesConnected = 0;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		InventoryUtils.writeInventoryToNBT(targetSlots, nbt);
		buffer.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		this.buffer.readFromNBT(nbt);
		InventoryUtils.readInventoryFromNBT(targetSlots, nbt);
	}

	public IInventory getInventory()
	{
		return targetSlots;
	}

	public int getMachinesConnected()
	{
		return machinesConnected;
	}

	private void fillReceiverCache()
	{
		receiverCache = new HashSet<TileEntity>();
		for (int i = 0; i < targetSlots.getSizeInventory(); i++)
		{
			ItemStack is = targetSlots.getStackInSlot(i);
			if (is != null)
			{
				DimensionCoordinate dc = ItemFilter.getPosition(is);
				if (dc.dimension == worldObj.provider.dimensionId && dc.getDistanceSqrd(xCoord, yCoord, zCoord) <= (RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_RANGE * RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_RANGE))
				{
					WorldUtils.recConnectedTEs(receiverCache, worldObj, dc.posX, dc.posY, dc.posZ, validator);

					TileEntity te = worldObj.getTileEntity(dc.posX, dc.posY, dc.posZ);
					if (te != null && !te.isInvalid() && te instanceof IEnergyReceiver && !(te instanceof TileEntityEnderEnergyDistributor))
					{
						receiverCache.add(te);
					}
				}
			}
		}
		machinesConnected = receiverCache.size();
		while (receiverCache.size() > RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_MAXMACHINES)
		{
			receiverCache.remove(receiverCache.toArray()[0]);
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if (!worldObj.isRemote)
		{
			if (worldObj.getTotalWorldTime() % 100 == 0)
			{
				fillReceiverCache();
			}

			int limit = RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_PERTICK;
			energyDistributedLastTick = 0;
			for (TileEntity te : receiverCache)
			{
				if (!te.isInvalid())
				{
					IEnergyReceiver er = (IEnergyReceiver) te;
					int consumed = er.receiveEnergy(ForgeDirection.UP, Math.min(limit, Math.min(buffer.getEnergyStored(), RTSettingsConfiguration.ENDER_ENERGY_DISTRIBUTOR_PERMACHINE)), false);
					limit -= consumed;
					buffer.setEnergyStored(buffer.getEnergyStored() - consumed);
					energyDistributedLastTick += consumed;
					if (buffer.getEnergyStored() == 0 || limit == 0)
					{
						break;
					}
				}
			}

		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return !(from == ForgeDirection.UP);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return buffer.getMaxEnergyStored();
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	public int getEnergyDistributedLastTick()
	{
		return energyDistributedLastTick;
	}

	public void dropItems()
	{
		for (int slot = 0; slot < targetSlots.getSizeInventory(); slot++)
		{
			if (targetSlots.getStackInSlot(slot) != null)
			{
				WorldUtils.dropItemStack(worldObj, xCoord, yCoord, zCoord, targetSlots.getStackInSlot(slot));
			}
		}
	}
}

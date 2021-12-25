package lumien.randomthings.Network.Messages;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import lumien.randomthings.Handler.RTMoonHandler.Bluemoon.ClientBluemoonHandler;
import lumien.randomthings.Network.IRTMessage;

public class MessageBluemoon implements IRTMessage
{
	boolean blueMoon;

	public MessageBluemoon()
	{

	}

	public MessageBluemoon(boolean blueMoon)
	{
		this.blueMoon = blueMoon;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.blueMoon = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(blueMoon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onMessage(MessageContext context)
	{
		ClientBluemoonHandler.INSTANCE.setBluemoon(this.blueMoon);
	}
}

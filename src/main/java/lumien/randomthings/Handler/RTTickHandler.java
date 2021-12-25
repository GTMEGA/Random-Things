package lumien.randomthings.Handler;

import lumien.randomthings.Handler.RTMoonHandler.Bluemoon.ClientBluemoonHandler;
import lumien.randomthings.Handler.RTMoonHandler.Bluemoon.ServerBluemoonHandler;
import net.minecraft.client.Minecraft;
import lumien.randomthings.RandomThings;
import lumien.randomthings.Client.Renderer.RenderBlut;
import lumien.randomthings.Handler.RTMoonHandler.Bloodmoon.ClientBloodmoonHandler;
import lumien.randomthings.Handler.RTMoonHandler.Bloodmoon.ServerBloodmoonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RTTickHandler
{
	@SubscribeEvent
	public void tick(TickEvent event)
	{
		switch (event.side)
		{
			case SERVER:
				serverTick(event);
				break;
			case CLIENT:
				clientTick(event);
				break;
			default:
				break;
		}
	}

	@SideOnly(Side.CLIENT)
	private void clientTick(TickEvent event)
	{
		switch (event.phase)
		{
			case START:
				if (event.type == TickEvent.Type.CLIENT)
				{
					RandomThings.instance.notificationHandler.update();
					ClientBloodmoonHandler.INSTANCE.tick(Minecraft.getMinecraft().theWorld);
					ClientBluemoonHandler.INSTANCE.tick(Minecraft.getMinecraft().theWorld);
				}
				RenderBlut.counter += 0.01;
				break;
			case END:
				break;
		}
	}

	private void serverTick(TickEvent event)
	{
		switch (event.phase)
		{
			case START:
				if (event.type == TickEvent.Type.SERVER)
				{
					MagneticForceHandler.INSTANCE.update();

					RandomThings.instance.letterHandler.update();
					if (RandomThings.instance.spectreHandler != null)
					{
						RandomThings.instance.spectreHandler.update();
					}
				}
				break;
			case END:
				if (event.type == TickEvent.Type.WORLD)
				{
					ServerBloodmoonHandler.INSTANCE.endWorldTick(((WorldTickEvent) event).world);
					ServerBluemoonHandler.INSTANCE.endWorldTick(((WorldTickEvent) event).world);
				}
				break;

		}
	}
}

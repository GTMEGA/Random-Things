package lumien.randomthings.Network;

import lumien.randomthings.Network.Messages.*;

public class PacketHandler
{
	public static final RandomThingsNetworkWrapper INSTANCE = new RandomThingsNetworkWrapper();

	public static void init()
	{
		// INSTANCE.registerMessage(MessageEnderLetter.class,
		// MessageEnderLetter.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageItemCollector.class);
		INSTANCE.registerMessage(MessageItemFilter.class);
		INSTANCE.registerMessage(MessageNotification.class);
		INSTANCE.registerMessage(MessageOnlineDetector.class);
		INSTANCE.registerMessage(MessagePaintBiome.class);
		INSTANCE.registerMessage(MessageWhitestone.class);
		INSTANCE.registerMessage(MessageRequestTeleport.class);
		INSTANCE.registerMessage(MessageAnswerTeleport.class);
		INSTANCE.registerMessage(MessageMagneticForceParticle.class);
		INSTANCE.registerMessage(MessageChangeItemProperty.class);
		INSTANCE.registerMessage(MessageSpectreData.class);
		INSTANCE.registerMessage(MessageBloodmoon.class);
		INSTANCE.registerMessage(MessageBluemoon.class);
		INSTANCE.registerMessage(MessageContainerProperty.class);
	}
}

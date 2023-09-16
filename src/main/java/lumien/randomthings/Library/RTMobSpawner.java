/**
 * It's a copy of the vanilla mob spawner class, but with a few changes to make it more flexible
 */
package lumien.randomthings.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeEventFactory;
import cpw.mods.fml.common.eventhandler.Event.Result;

import static sun.misc.Version.println;

public class RTMobSpawner
{
	/** The 17x17 area around the player where mobs can spawn */
	private HashMap eligibleChunksForSpawning = new HashMap();

	protected static ChunkPosition mobSpawnPosGen(World world, int x, int z)
	{
		Chunk chunk = world.getChunkFromChunkCoords(x, z);
		int k = x * 16 + world.rand.nextInt(16);
		int l = z * 16 + world.rand.nextInt(16);
		int i1 = world.rand.nextInt(chunk == null ? world.getActualHeight() : chunk.getTopFilledSegment() + 16 - 1);
		return new ChunkPosition(k, i1, l);
	}

	/**
	 * adds all chunks within the spawn radius of the players to
	 * eligibleChunksForSpawning. pars: the world, hostileCreatures,
	 * passiveCreatures. returns number of eligible chunks.
	 */
	public int findChunksForRandomSpawning(WorldServer world, boolean hostileCreatures, boolean passiveCreatures, boolean isSpawnTick, int multiplier, int spawnRange, boolean shouldVanish, String vanishFlag)
	{
		if (!hostileCreatures && !passiveCreatures)
		{
			return 0;
		}
		else
		{
			this.eligibleChunksForSpawning.clear();
			int i;
			int k;

			for (i = 0; i < world.playerEntities.size(); ++i)
			{
				EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
				int j = MathHelper.floor_double(entityplayer.posX / 16.0D);
				k = MathHelper.floor_double(entityplayer.posZ / 16.0D);
				byte b0 = 8;

				for (int l = -b0; l <= b0; ++l)
				{
					for (int i1 = -b0; i1 <= b0; ++i1)
					{
						boolean flag3 = l == -b0 || l == b0 || i1 == -b0 || i1 == b0;
						ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(l + j, i1 + k);

						if (!flag3)
						{
							this.eligibleChunksForSpawning.put(chunkcoordintpair, false);
						}
						else if (!this.eligibleChunksForSpawning.containsKey(chunkcoordintpair))
						{
							this.eligibleChunksForSpawning.put(chunkcoordintpair, true);
						}
					}
				}
			}

			i = 0;
			ChunkCoordinates chunkcoordinates = world.getSpawnPoint();
			EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();
			k = aenumcreaturetype.length;

			for (int k3 = 0; k3 < k; ++k3)
			{
				EnumCreatureType enumcreaturetype = aenumcreaturetype[k3];

				if ((!enumcreaturetype.getPeacefulCreature() || passiveCreatures) && (enumcreaturetype.getPeacefulCreature() || hostileCreatures) && (!enumcreaturetype.getAnimal() || isSpawnTick) && world.countEntities(enumcreaturetype, true) <= enumcreaturetype.getMaxNumberOfCreature() * multiplier * this.eligibleChunksForSpawning.size() / 256)
				{
					Iterator iterator = this.eligibleChunksForSpawning.keySet().iterator();
					ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
					Collections.shuffle(tmp);
					iterator = tmp.iterator();
					label110:

					while (iterator.hasNext())
					{
						ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) iterator.next();

						if (!((Boolean) this.eligibleChunksForSpawning.get(chunkcoordintpair1)).booleanValue())
						{
							ChunkPosition chunkposition = mobSpawnPosGen(world, chunkcoordintpair1.chunkXPos, chunkcoordintpair1.chunkZPos);
							int j1 = chunkposition.chunkPosX;
							int k1 = chunkposition.chunkPosY;
							int l1 = chunkposition.chunkPosZ;

							if (!world.getBlock(j1, k1, l1).isNormalCube() && world.getBlock(j1, k1, l1).getMaterial() == enumcreaturetype.getCreatureMaterial())
							{
								int i2 = 0;
								int j2 = 0;

								while (j2 < 3)
								{
									int k2 = j1;
									int l2 = k1;
									int i3 = l1;
									byte b1 = 6;
									BiomeGenBase.SpawnListEntry spawnlistentry = null;
									IEntityLivingData ientitylivingdata = null;
									int j3 = 0;

									while (true)
									{
										if (j3 < 4)
										{
											label103:
											{
												k2 += world.rand.nextInt(b1) - world.rand.nextInt(b1);
												l2 += world.rand.nextInt(1) - world.rand.nextInt(1);
												i3 += world.rand.nextInt(b1) - world.rand.nextInt(b1);

												if (world.canBlockSeeTheSky(k2, l2, i3) && canCreatureTypeSpawnAtLocation(enumcreaturetype, world, k2, l2, i3))
												{
													float f = k2 + 0.5F;
													float f1 = l2;
													float f2 = i3 + 0.5F;

													if (world.getClosestPlayer(f, f1, f2, spawnRange) == null)
													{
														float f3 = f - chunkcoordinates.posX;
														float f4 = f1 - chunkcoordinates.posY;
														float f5 = f2 - chunkcoordinates.posZ;
														float f6 = f3 * f3 + f4 * f4 + f5 * f5;

														if (f6 >= 576.0F)
														{
															if (spawnlistentry == null)
															{
																spawnlistentry = world.spawnRandomCreature(enumcreaturetype, k2, l2, i3);

																if (spawnlistentry == null)
																{
																	break label103;
																}
															}

															EntityLiving entityliving;

															try
															{
																entityliving = (EntityLiving) spawnlistentry.entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
															}
															catch (Exception exception)
															{
																exception.printStackTrace();
																return i;
															}
															
															entityliving.setLocationAndAngles(f, f1, f2, world.rand.nextFloat() * 360.0F, 0.0F);

															Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, world, f, f1, f2);
															if (canSpawn == Result.ALLOW || (canSpawn == Result.DEFAULT && entityliving.getCanSpawnHere()))
															{

																if (shouldVanish)
																{
																	entityliving.getEntityData().setBoolean(vanishFlag, true);
																}
																
																++i2;
																world.spawnEntityInWorld(entityliving);
																if (!ForgeEventFactory.doSpecialSpawn(entityliving, world, f, f1, f2))
																{
																	ientitylivingdata = entityliving.onSpawnWithEgg(ientitylivingdata);
																}

																if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving))
																{
																	continue label110;
																}
															}

															i += i2;
														}
													}
												}

												++j3;
												continue;
											}
										}

										++j2;
										break;
									}
								}
							}
						}
					}
				}
			}

			return i;
		}
	}

	/**
	 * adds all chunks within the spawn radius of the players to
	 * eligibleChunksForSpawning. pars: the world, hostileCreatures,
	 * passiveCreatures. returns number of eligible chunks.
	 */
	public <T extends EntityLiving> int findChunksForMobSpawning(WorldServer world, Class<T> mob, Consumer<T> mutator, boolean isSpawnTick, int multiplier, int spawnRange, boolean shouldVanish, String vanishFlag) {

		this.eligibleChunksForSpawning.clear();
		int i;
		int k;

		for (i = 0; i < world.playerEntities.size(); ++i) {
			EntityPlayer entityplayer = (EntityPlayer) world.playerEntities.get(i);
			int j = MathHelper.floor_double(entityplayer.posX / 16.0D);
			k = MathHelper.floor_double(entityplayer.posZ / 16.0D);
			byte b0 = 8;

			for (int l = -b0; l <= b0; ++l) {
				for (int i1 = -b0; i1 <= b0; ++i1) {
					boolean flag3 = l == -b0 || l == b0 || i1 == -b0 || i1 == b0;
					ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(l + j, i1 + k);

					if (!flag3) {
						this.eligibleChunksForSpawning.put(chunkcoordintpair, false);
					} else if (!this.eligibleChunksForSpawning.containsKey(chunkcoordintpair)) {
						this.eligibleChunksForSpawning.put(chunkcoordintpair, true);
					}
				}
			}
		}

		i = 0;
		ChunkCoordinates chunkcoordinates = world.getSpawnPoint();
		EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();
		k = aenumcreaturetype.length;

		nextEnumCreatureType:
		for (int k3 = 0; k3 < k; ++k3) {
			EnumCreatureType enumcreaturetype = aenumcreaturetype[k3];
			if (isSpawnTick && world.countEntities(enumcreaturetype, true) <= enumcreaturetype.getMaxNumberOfCreature() * multiplier * this.eligibleChunksForSpawning.size() / 256) {
				Iterator iterator = this.eligibleChunksForSpawning.keySet().iterator();
				ArrayList<ChunkCoordIntPair> tmp = new ArrayList(eligibleChunksForSpawning.keySet());
				Collections.shuffle(tmp);
				iterator = tmp.iterator();
				label110:

				while (iterator.hasNext()) {
					ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair) iterator.next();

					if (!((Boolean) this.eligibleChunksForSpawning.get(chunkcoordintpair1)).booleanValue()) {
						ChunkPosition chunkposition = mobSpawnPosGen(world, chunkcoordintpair1.chunkXPos, chunkcoordintpair1.chunkZPos);
						int j1 = chunkposition.chunkPosX;
						int k1 = chunkposition.chunkPosY;
						int l1 = chunkposition.chunkPosZ;

						if (!world.getBlock(j1, k1, l1).isNormalCube() && world.getBlock(j1, k1, l1).getMaterial() == enumcreaturetype.getCreatureMaterial()) {
							int i2 = 0;
							int j2 = 0;

							while (j2 < 3) {
								int k2 = j1;
								int l2 = k1;
								int i3 = l1;
								byte b1 = 6;
								IEntityLivingData ientitylivingdata = null;
								int j3 = 0;

								while (true) {
									if (j3 < 4) {
										k2 += world.rand.nextInt(b1) - world.rand.nextInt(b1);
										l2 += world.rand.nextInt(1) - world.rand.nextInt(1);
										i3 += world.rand.nextInt(b1) - world.rand.nextInt(b1);

										if (world.canBlockSeeTheSky(k2, l2, i3) && canCreatureTypeSpawnAtLocation(enumcreaturetype, world, k2, l2, i3)) {
											float f = k2 + 0.5F;
											float f1 = l2;
											float f2 = i3 + 0.5F;

											if (world.getClosestPlayer(f, f1, f2, spawnRange) == null) {
												float f3 = f - chunkcoordinates.posX;
												float f4 = f1 - chunkcoordinates.posY;
												float f5 = f2 - chunkcoordinates.posZ;
												float f6 = f3 * f3 + f4 * f4 + f5 * f5;

												if (f6 >= 576.0F) {
													T entityliving;

													try {
														entityliving = mob.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world});
														if (!entityliving.isCreatureType(enumcreaturetype, true)) {
															continue nextEnumCreatureType;
														}
													} catch (Exception exception) {
														exception.printStackTrace();
														return i;
													}

													entityliving.setLocationAndAngles(f, f1, f2, world.rand.nextFloat() * 360.0F, 0.0F);
													mutator.accept(entityliving);

													Result canSpawn = ForgeEventFactory.canEntitySpawn(entityliving, world, f, f1, f2);
													if (canSpawn == Result.ALLOW || (canSpawn == Result.DEFAULT
															/*&& entityliving.getCanSpawnHere() !!this comment makes mobs spawn anywhere*/)) {

														if (shouldVanish) {
															entityliving.getEntityData().setBoolean(vanishFlag, true);
														}

														++i2;
														world.spawnEntityInWorld(entityliving);
														if (!ForgeEventFactory.doSpecialSpawn(entityliving, world, f, f1, f2)) {
															ientitylivingdata = entityliving.onSpawnWithEgg(ientitylivingdata);
														}

														if (j2 >= ForgeEventFactory.getMaxSpawnPackSize(entityliving)) {
															continue label110;
														}
													}

													i += i2;
												}
											}
										}

										++j3;
										continue;
									}
									++j2;
									break;
								}
							}
						}
					}
				}
			}
		}

		return i;

	}

	/**
	 * Returns whether the specified creature type can spawn at the
	 * specified location.
	 */
	public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType creatureType, World world, int x, int y, int z)
	{
		if (creatureType.getCreatureMaterial() == Material.water)
		{
			return world.getBlock(x, y, z).getMaterial().isLiquid() && world.getBlock(x, y - 1, z).getMaterial().isLiquid() && !world.getBlock(x, y + 1, z).isNormalCube();
		}
		else if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
		{
			return false;
		}
		else
		{
			Block block = world.getBlock(x, y - 1, z);
			boolean spawnBlock = block.canCreatureSpawn(creatureType, world, x, y - 1, z);
			return spawnBlock && block != Blocks.bedrock && !world.getBlock(x, y, z).isNormalCube() && !world.getBlock(x, y, z).getMaterial().isLiquid() && !world.getBlock(x, y + 1, z).isNormalCube();
		}
	}
}

package lumien.randomthings.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lumien.randomthings.Library.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBlueWolf extends EntityMob {

    public EntityBlueWolf(World world) {
        super(world);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.isImmuneToFire = true;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    }

    public boolean attackEntityAsMob(Entity target)
    {
        if(target instanceof EntityLivingBase) {
            EntityLivingBase targetLivingBase = (EntityLivingBase) target;
            if(Math.random() >= 0.5) {
                targetLivingBase.addPotionEffect(new PotionEffect(PotionEffects.WEAKNESS, 120));
            } else {
                targetLivingBase.addPotionEffect(new PotionEffect(PotionEffects.SLOWNESS, 120));
            }
            if(targetLivingBase.getHealth() <= 6) {
                if(Math.random() >= 0.5) {
                    targetLivingBase.addPotionEffect(new PotionEffect(PotionEffects.NAUSEA, 120));
                } else {
                    targetLivingBase.addPotionEffect(new PotionEffect(PotionEffects.BLINDNESS, 120));
                }
            }
        }
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() { return "mob.wolf.growl"; }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation()
    {
        return 1.5393804F;
    }

    @Override
    public boolean interact(EntityPlayer player)
    {
        return super.interact(player);
    }
}

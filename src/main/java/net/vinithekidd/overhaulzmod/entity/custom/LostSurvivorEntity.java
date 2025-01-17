package net.vinithekidd.overhaulzmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class LostSurvivorEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LostSurvivorEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D);
    }

    private boolean isAttacking;

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        this.isAttacking = attacking;
    }

    private int attackAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if (attackAnimationTimeout > 0) {
            attackAnimationTimeout--;

            if (attackAnimationTimeout == 0) {
                this.setAttacking(false);
            }
        }
    }

    public int getAttackAnimationTimeout() {
        return this.attackAnimationTimeout;
    }

    public void resetAttackAnimationTimeout(int timeout) {
        this.attackAnimationTimeout = timeout;
    }

    public boolean isAttackAnimationTimeoutOver() {
        return attackAnimationTimeout <= 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 10, event -> {
            if (this.isAttacking()) {
                // Inicia a animação de ataque
                return event.setAndContinue(RawAnimation.begin().thenPlay("animation.lost_survivor.attack"));
            } else if (this.getDeltaMovement().horizontalDistanceSqr() > 0.01) {
                // Move para a animação de andar, se estiver se movimentando
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.lost_survivor.walk"));
            } else {
                // Volta para a animação idle, se estiver parado
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.lost_survivor.idle"));
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
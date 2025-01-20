package net.vinithekidd.overhaulzmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
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

    // Atributos da entidade (como vida, velocidade, etc.)
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D) // Define a vida máxima
                .add(Attributes.MOVEMENT_SPEED, 0.25D) // Velocidade de movimento padrão
                .add(Attributes.FOLLOW_RANGE, 35.0D); // Distância de percepção
    }

    @Override
    protected void registerGoals() {
        // Metas básicas de movimentação e comportamento
        this.goalSelector.addGoal(0, new FloatGoal(this)); // Permite que a entidade flutue na água
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D)); // Caminhada aleatória por terra
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this)); // Olhar ao redor aleatoriamente
    }

    @Override
    public void tick() {
        super.tick();
        // A lógica de ataque foi removida, logo nada relacionado a ataques será processado aqui
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 10, event -> {
            // Animação de movimento
            if (this.getDeltaMovement().horizontalDistanceSqr() > 0.0001D) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.lost_survivor.walk"));
            }

            // Animação de idle (parado)
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.lost_survivor.idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null; // Essa entidade não é reprodutível
    }
}
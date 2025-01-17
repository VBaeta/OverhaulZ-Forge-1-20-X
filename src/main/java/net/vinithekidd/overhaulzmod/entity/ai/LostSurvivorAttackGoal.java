package net.vinithekidd.overhaulzmod.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;

public class LostSurvivorAttackGoal extends MeleeAttackGoal {
    private final LostSurvivorEntity entity;
    private int attackDelay = 10;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;

    public LostSurvivorAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((LostSurvivorEntity) pMob);
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 10;
        ticksUntilNextAttack = 10;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target, double distanceToTargetSqr) {
        if (this.isEnemyWithinAttackDistance(target, distanceToTargetSqr)) {
            // Inicia a animação de ataque, se não estiver ativa
            if (entity.isAttackAnimationTimeoutOver()) {
                entity.setAttacking(true); // Ativa o estado de ataque
                entity.resetAttackAnimationTimeout(20); // Timeout de 20 ticks (1 segundo, duração da animação)
            }

            // Aplica o dano no momento do impacto (após 10 ticks)
            if (entity.getAttackAnimationTimeout() == 10) { // Após 0.5 segundos
                this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                performAttack(target); // Aplica dano
            }
        } else {
            resetAttackCooldown(); // Reseta o cooldown se o alvo sair do alcance
            entity.setAttacking(false); // Finaliza o estado de ataque
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}

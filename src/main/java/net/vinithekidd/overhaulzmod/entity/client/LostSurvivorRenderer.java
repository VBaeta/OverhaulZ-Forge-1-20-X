package net.vinithekidd.overhaulzmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LostSurvivorRenderer extends GeoEntityRenderer<LostSurvivorEntity> {
    public LostSurvivorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LostSurvivorModel());
    }

    @Override
    protected void applyRotations(LostSurvivorEntity livingEntity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTickTime) {
        super.applyRotations(livingEntity, poseStack, ageInTicks, rotationYaw, partialTickTime);
    }
}

package net.vinithekidd.overhaulzmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;

public class LostSurvivorRenderer extends MobRenderer<LostSurvivorEntity, LostSurvivorModel<LostSurvivorEntity>> {
    public LostSurvivorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LostSurvivorModel<>(pContext.bakeLayer(ModModelLayers.LOST_SURVIVOR_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(LostSurvivorEntity pEntity) {
        return new ResourceLocation(OverhaulZ.MOD_ID, "textures/entity/lost_survivor.png");
    }

    @Override
    public void render(LostSurvivorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}

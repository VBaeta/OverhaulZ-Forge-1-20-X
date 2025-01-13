package net.vinithekidd.overhaulzmod.entity.client;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.vinithekidd.overhaulzmod.entity.animations.ModAnimationDefinitions;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;

public class LostSurvivorModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart body;
	private final ModelPart upper_body;
	private final ModelPart right_arm;
	private final ModelPart right_upper_arm;
	private final ModelPart right_lower_arm;
	private final ModelPart left_arm;
	private final ModelPart left_upper_arm;
	private final ModelPart left_lower_arm;
	private final ModelPart head;
	private final ModelPart lower_body;
	private final ModelPart left_leg;
	private final ModelPart left_upper_leg;
	private final ModelPart left_lower_leg;
	private final ModelPart right_leg;
	private final ModelPart right_upper_leg;
	private final ModelPart right_lower_leg;

	public LostSurvivorModel(ModelPart root) {
		this.body = root.getChild("body");
		this.upper_body = this.body.getChild("upper_body");
		this.right_arm = this.upper_body.getChild("right_arm");
		this.right_upper_arm = this.right_arm.getChild("right_upper_arm");
		this.right_lower_arm = this.right_upper_arm.getChild("right_lower_arm");
		this.left_arm = this.upper_body.getChild("left_arm");
		this.left_upper_arm = this.left_arm.getChild("left_upper_arm");
		this.left_lower_arm = this.left_upper_arm.getChild("left_lower_arm");
		this.head = this.upper_body.getChild("head");
		this.lower_body = this.body.getChild("lower_body");
		this.left_leg = this.lower_body.getChild("left_leg");
		this.left_upper_leg = this.left_leg.getChild("left_upper_leg");
		this.left_lower_leg = this.left_upper_leg.getChild("left_lower_leg");
		this.right_leg = this.lower_body.getChild("right_leg");
		this.right_upper_leg = this.right_leg.getChild("right_upper_leg");
		this.right_lower_leg = this.right_upper_leg.getChild("right_lower_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -5.9772F, -2.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.9772F, 0.5229F));

		PartDefinition right_arm = upper_body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition right_upper_arm = right_arm.addOrReplaceChild("right_upper_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_lower_arm = right_upper_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create().texOffs(40, 22).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition left_arm = upper_body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition left_upper_arm = left_arm.addOrReplaceChild("left_upper_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_lower_arm = left_upper_arm.addOrReplaceChild("left_lower_arm", CubeListBuilder.create().texOffs(32, 54).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(16, 22).addBox(-4.0F, -0.0057F, -2.2154F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0057F, 0.7383F));

		PartDefinition left_leg = lower_body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -1.0F));

		PartDefinition left_upper_leg = left_leg.addOrReplaceChild("left_upper_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.2154F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 1.0F));

		PartDefinition left_lower_leg = left_upper_leg.addOrReplaceChild("left_lower_leg", CubeListBuilder.create().texOffs(16, 54).addBox(-1.6157F, 0.0F, -1.7259F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2843F, 5.9715F, -0.4895F));

		PartDefinition right_leg = lower_body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -1.0F));

		PartDefinition right_upper_leg = right_leg.addOrReplaceChild("right_upper_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.2154F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 1.0F));

		PartDefinition right_lower_leg = right_upper_leg.addOrReplaceChild("right_lower_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.1F, 0.0F, -2.2154F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animate(((LostSurvivorEntity) entity).idleAnimationState, ModAnimationDefinitions.LOST_SURVIVOR_IDLE, ageInTicks, 1f);
		this.animate(((LostSurvivorEntity) entity).walkAnimationState, ModAnimationDefinitions.LOST_SURVIVOR_WALK, ageInTicks, 1f);
		this.animate(((LostSurvivorEntity) entity).attackAnimationState, ModAnimationDefinitions.LOST_SURVIVOR_PUNCH, ageInTicks, 1f);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}
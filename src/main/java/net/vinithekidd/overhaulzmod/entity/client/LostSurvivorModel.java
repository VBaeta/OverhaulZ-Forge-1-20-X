package net.vinithekidd.overhaulzmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.vinithekidd.overhaulzmod.OverhaulZ;
import net.vinithekidd.overhaulzmod.entity.custom.LostSurvivorEntity;
import software.bernie.geckolib.model.DefaultedGeoModel;

public class LostSurvivorModel extends DefaultedGeoModel<LostSurvivorEntity> {
	public LostSurvivorModel() {
		super(new ResourceLocation(OverhaulZ.MOD_ID, "lost_survivor"));
	}

	@Override
	protected String subtype() {
		return "lost_survivor";
	}
}
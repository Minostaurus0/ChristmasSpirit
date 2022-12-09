package io.github.frqnny.cspirit.client.render;

import io.github.frqnny.cspirit.ChristmasSpirit;
import io.github.frqnny.cspirit.ChristmasSpiritClient;
import io.github.frqnny.cspirit.client.model.ReindeerModel;
import io.github.frqnny.cspirit.client.render.feature.ReindeerLeatherArmorFeatureRenderer;
import io.github.frqnny.cspirit.client.render.feature.ReindeerMarkingsFeatureRenderer;
import io.github.frqnny.cspirit.entity.ReindeerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.util.Identifier;

public class ReindeerEntityRenderer extends AbstractHorseEntityRenderer<ReindeerEntity, ReindeerModel<ReindeerEntity>> {
    public static final Identifier TEXTURE = ChristmasSpirit.id("textures/entity/reindeer.png");

    public ReindeerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ReindeerModel<>(ctx.getPart(ChristmasSpiritClient.REINDEER)), 1.1F);
        this.addFeature(new ReindeerLeatherArmorFeatureRenderer(this, ctx.getModelLoader()));
        this.addFeature(new ReindeerMarkingsFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(ReindeerEntity entity) {
        return TEXTURE;
    }
}

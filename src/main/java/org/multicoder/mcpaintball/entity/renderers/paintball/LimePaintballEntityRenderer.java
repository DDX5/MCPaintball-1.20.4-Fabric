package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.LimePaintballEntity;

@Environment(EnvType.CLIENT)
public class LimePaintballEntityRenderer extends ProjectileEntityRenderer<LimePaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/lime_paintball.png");

    public LimePaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LimePaintballEntity entity) {
        return TEXTURE;
    }
}

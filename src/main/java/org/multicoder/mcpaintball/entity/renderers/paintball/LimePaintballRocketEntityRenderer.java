package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.LimePaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class LimePaintballRocketEntityRenderer extends ProjectileEntityRenderer<LimePaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/lime_paintball_heavy.png");

    public LimePaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LimePaintballRocketEntity entity) {
        return TEXTURE;
    }
}

package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.BluePaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class BluePaintballRocketEntityRenderer extends ProjectileEntityRenderer<BluePaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/blue_paintball_heavy.png");

    public BluePaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BluePaintballRocketEntity entity) {
        return TEXTURE;
    }
}

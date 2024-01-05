package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.LightBluePaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class LightBluePaintballRocketEntityRenderer extends ProjectileEntityRenderer<LightBluePaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/light_blue_paintball_heavy.png");

    public LightBluePaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LightBluePaintballRocketEntity entity) {
        return TEXTURE;
    }
}

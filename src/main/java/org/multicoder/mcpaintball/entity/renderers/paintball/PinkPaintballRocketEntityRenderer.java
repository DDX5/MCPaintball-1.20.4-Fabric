package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.PinkPaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class PinkPaintballRocketEntityRenderer extends ProjectileEntityRenderer<PinkPaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/pink_paintball_heavy.png");

    public PinkPaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PinkPaintballRocketEntity entity) {
        return TEXTURE;
    }
}

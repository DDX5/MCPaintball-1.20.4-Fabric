package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.PurplePaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class PurplePaintballRocketEntityRenderer extends ProjectileEntityRenderer<PurplePaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/purple_paintball_heavy.png");

    public PurplePaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PurplePaintballRocketEntity entity) {
        return TEXTURE;
    }
}

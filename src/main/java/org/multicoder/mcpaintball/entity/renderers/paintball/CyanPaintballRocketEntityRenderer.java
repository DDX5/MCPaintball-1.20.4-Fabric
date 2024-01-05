package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.CyanPaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class CyanPaintballRocketEntityRenderer extends ProjectileEntityRenderer<CyanPaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/cyan_paintball_heavy.png");

    public CyanPaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(CyanPaintballRocketEntity entity) {
        return TEXTURE;
    }
}

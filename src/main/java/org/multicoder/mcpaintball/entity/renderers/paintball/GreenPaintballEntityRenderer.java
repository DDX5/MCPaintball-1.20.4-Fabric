package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.GreenPaintballEntity;

@Environment(EnvType.CLIENT)
public class GreenPaintballEntityRenderer extends ProjectileEntityRenderer<GreenPaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/green_paintball.png");

    public GreenPaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(GreenPaintballEntity entity) {
        return TEXTURE;
    }
}

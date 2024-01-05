package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.PinkPaintballEntity;

@Environment(EnvType.CLIENT)
public class PinkPaintballEntityRenderer extends ProjectileEntityRenderer<PinkPaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/pink_paintball.png");

    public PinkPaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PinkPaintballEntity entity) {
        return TEXTURE;
    }
}

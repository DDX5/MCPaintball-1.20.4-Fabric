package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.PurplePaintballEntity;

@Environment(EnvType.CLIENT)
public class PurplePaintballEntityRenderer extends ProjectileEntityRenderer<PurplePaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/purple_paintball.png");

    public PurplePaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PurplePaintballEntity entity) {
        return TEXTURE;
    }
}

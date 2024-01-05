package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.LightBluePaintballEntity;

@Environment(EnvType.CLIENT)
public class LightBluePaintballEntityRenderer extends ProjectileEntityRenderer<LightBluePaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/light_blue_paintball.png");

    public LightBluePaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LightBluePaintballEntity entity) {
        return TEXTURE;
    }
}

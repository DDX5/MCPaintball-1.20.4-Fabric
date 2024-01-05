package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.MagentaPaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class MagentaPaintballRocketEntityRenderer extends ProjectileEntityRenderer<MagentaPaintballRocketEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/magenta_paintball_heavy.png");

    public MagentaPaintballRocketEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(MagentaPaintballRocketEntity entity) {
        return TEXTURE;
    }
}

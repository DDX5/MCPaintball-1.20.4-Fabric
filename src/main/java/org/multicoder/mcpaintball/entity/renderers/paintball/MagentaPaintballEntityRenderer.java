package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.paintball.MagentaPaintballEntity;

@Environment(EnvType.CLIENT)
public class MagentaPaintballEntityRenderer extends ProjectileEntityRenderer<MagentaPaintballEntity> {
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball/magenta_paintball.png");

    public MagentaPaintballEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(MagentaPaintballEntity entity) {
        return TEXTURE;
    }
}

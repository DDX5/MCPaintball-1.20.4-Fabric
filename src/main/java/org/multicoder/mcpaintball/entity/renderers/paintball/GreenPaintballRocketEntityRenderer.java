package org.multicoder.mcpaintball.entity.renderers.paintball;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.multicoder.mcpaintball.entity.rockets.GreenPaintballRocketEntity;
import org.multicoder.mcpaintball.entity.rockets.RedPaintballRocketEntity;

@Environment(EnvType.CLIENT)
public class GreenPaintballRocketEntityRenderer extends ProjectileEntityRenderer<GreenPaintballRocketEntity>
{
    public static Identifier TEXTURE = new Identifier("mcpaintball:textures/entity/projectiles/paintball_heavy/green_paintball_heavy.png");
    public GreenPaintballRocketEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context);
    }

    @Override
    public Identifier getTexture(GreenPaintballRocketEntity entity)
    {
        return TEXTURE;
    }
}

package org.multicoder.mcpaintball;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import org.multicoder.mcpaintball.entity.MCPaintballEntities;
import org.multicoder.mcpaintball.entity.grenade.*;
import org.multicoder.mcpaintball.entity.paintball.*;
import org.multicoder.mcpaintball.entity.renderers.paintball.*;
import org.multicoder.mcpaintball.entity.rockets.*;
import org.multicoder.mcpaintball.network.MCPaintballNetworking;
import org.multicoder.mcpaintball.player.PlayerKeyInputs;

@SuppressWarnings("unchecked")
public class MCPaintballClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MCPaintball.LOGGER.info("Client Start");
        EntityRendererRegistry.register((EntityType<? extends RedPaintballEntity>) MCPaintballEntities.RED_PAINTBALL, RedPaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends GreenPaintballEntity>) MCPaintballEntities.GREEN_PAINTBALL, GreenPaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends BluePaintballEntity>) MCPaintballEntities.BLUE_PAINTBALL, BluePaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends CyanPaintballEntity>) MCPaintballEntities.CYAN_PAINTBALL, CyanPaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends MagentaPaintballEntity>) MCPaintballEntities.MAGENTA_PAINTBALL, MagentaPaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends LimePaintballEntity>) MCPaintballEntities.LIME_PAINTBALL, LimePaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends LightBluePaintballEntity>) MCPaintballEntities.LIGHT_BLUE_PAINTBALL, LightBluePaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends PinkPaintballEntity>) MCPaintballEntities.PINK_PAINTBALL, PinkPaintballEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends PurplePaintballEntity>) MCPaintballEntities.PURPLE_PAINTBALL, PurplePaintballEntityRenderer::new);

        EntityRendererRegistry.register((EntityType<? extends RedGrenadeEntity>) MCPaintballEntities.RED_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends GreenGrenadeEntity>) MCPaintballEntities.GREEN_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends BlueGrenadeEntity>) MCPaintballEntities.BLUE_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends CyanGrenadeEntity>) MCPaintballEntities.CYAN_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends MagentaGrenadeEntity>) MCPaintballEntities.MAGENTA_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends LimeGrenadeEntity>) MCPaintballEntities.LIME_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends LightBlueGrenadeEntity>) MCPaintballEntities.LIGHT_BLUE_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends PinkGrenadeEntity>) MCPaintballEntities.PINK_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));
        EntityRendererRegistry.register((EntityType<? extends PurpleGrenadeEntity>) MCPaintballEntities.PURPLE_GRENADE, (ctx -> new FlyingItemEntityRenderer<>(ctx)));

        EntityRendererRegistry.register((EntityType<? extends RedPaintballRocketEntity>) MCPaintballEntities.RED_PAINTBALL_ROCKET, RedPaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends GreenPaintballRocketEntity>) MCPaintballEntities.GREEN_PAINTBALL_ROCKET, GreenPaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends BluePaintballRocketEntity>) MCPaintballEntities.BLUE_PAINTBALL_ROCKET, BluePaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends CyanPaintballRocketEntity>) MCPaintballEntities.CYAN_PAINTBALL_ROCKET, CyanPaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends MagentaPaintballRocketEntity>) MCPaintballEntities.MAGENTA_PAINTBALL_ROCKET, MagentaPaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends LimePaintballRocketEntity>) MCPaintballEntities.LIME_PAINTBALL_ROCKET, LimePaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends LightBluePaintballRocketEntity>) MCPaintballEntities.LIGHT_BLUE_PAINTBALL_ROCKET, LightBluePaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends PinkPaintballRocketEntity>) MCPaintballEntities.PINK_PAINTBALL_ROCKET, PinkPaintballRocketEntityRenderer::new);
        EntityRendererRegistry.register((EntityType<? extends PurplePaintballRocketEntity>) MCPaintballEntities.PURPLE_PAINTBALL_ROCKET, PurplePaintballRocketEntityRenderer::new);

        PlayerKeyInputs.Register();
        MCPaintballNetworking.RegisterC2SPackets();
    }


}

package org.multicoder.mcpaintball.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.entity.paintball.*;
import org.multicoder.mcpaintball.entity.rockets.*;

public enum PaintballTeam{
    RED,
    GREEN,
    BLUE,
    CYAN,
    MAGENTA,
    LIME,
    LIGHT_BLUE,
    PINK,
    PURPLE;

    public PersistentProjectileEntity getPaintball(PlayerEntity player, World level)
    {
        switch (this)
        {
            case RED -> {return new RedPaintballEntity(player,level);}
            case GREEN -> {return new GreenPaintballEntity(player,level);}
            case BLUE -> {return new BluePaintballEntity(player,level);}
            case CYAN -> {return new CyanPaintballEntity(player,level);}
            case MAGENTA -> {return new MagentaPaintballEntity(player,level);}
            case LIME -> {return new LimePaintballEntity(player,level);}
            case LIGHT_BLUE -> {return new LightBluePaintballEntity(player,level);}
            case PINK -> {return new PinkPaintballEntity(player,level);}
            case PURPLE -> {return new PurplePaintballEntity(player,level);}
        }
        return null;
    }
    public PersistentProjectileEntity getPaintballRocket(PlayerEntity player, World level)
    {
        switch (this)
        {
            case RED -> {return new RedPaintballRocketEntity(player,level);}
            case GREEN -> {return new GreenPaintballRocketEntity(player,level);}
            case BLUE -> {return new BluePaintballRocketEntity(player,level);}
            case CYAN -> {return new CyanPaintballRocketEntity(player,level);}
            case MAGENTA -> {return new MagentaPaintballRocketEntity(player,level);}
            case LIME -> {return new LimePaintballRocketEntity(player,level);}
            case LIGHT_BLUE -> {return new LightBluePaintballRocketEntity(player,level);}
            case PINK -> {return new PinkPaintballRocketEntity(player,level);}
            case PURPLE -> {return new PurplePaintballRocketEntity(player,level);}
        }
        return null;
    }
}

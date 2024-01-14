package org.multicoder.mcpaintball.utility;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.block.MCPaintballBlocks;
import org.multicoder.mcpaintball.entity.paintball.*;
import org.multicoder.mcpaintball.entity.rockets.*;
import org.multicoder.mcpaintball.item.MCPaintballItems;

import java.awt.*;

public enum PaintballTeam {
    RED,
    GREEN,
    BLUE,
    CYAN,
    MAGENTA,
    LIME,
    LIGHT_BLUE,
    PINK,
    PURPLE;

    public Block getExplosive() {
        switch (this) {
            case RED -> {
                return MCPaintballBlocks.RED_EXPLOSIVE;
            }
            case GREEN -> {
                return MCPaintballBlocks.GREEN_EXPLOSIVE;
            }
            case BLUE -> {
                return MCPaintballBlocks.BLUE_EXPLOSIVE;
            }
            case CYAN -> {
                return MCPaintballBlocks.CYAN_EXPLOSIVE;
            }
            case MAGENTA -> {
                return MCPaintballBlocks.MAGENTA_EXPLOSIVE;
            }
            case LIME -> {
                return MCPaintballBlocks.LIME_EXPLOSIVE;
            }
            case LIGHT_BLUE -> {
                return MCPaintballBlocks.LIGHT_BLUE_EXPLOSIVE;
            }
            case PINK -> {
                return MCPaintballBlocks.PINK_EXPLOSIVE;
            }
            case PURPLE -> {
                return MCPaintballBlocks.PURPLE_EXPLOSIVE;
            }
        }
        return null;
    }

    public PersistentProjectileEntity getPaintball(PlayerEntity player, World level) {
        switch (this) {
            case RED -> {
                return new RedPaintballEntity(player, level);
            }
            case GREEN -> {
                return new GreenPaintballEntity(player, level);
            }
            case BLUE -> {
                return new BluePaintballEntity(player, level);
            }
            case CYAN -> {
                return new CyanPaintballEntity(player, level);
            }
            case MAGENTA -> {
                return new MagentaPaintballEntity(player, level);
            }
            case LIME -> {
                return new LimePaintballEntity(player, level);
            }
            case LIGHT_BLUE -> {
                return new LightBluePaintballEntity(player, level);
            }
            case PINK -> {
                return new PinkPaintballEntity(player, level);
            }
            case PURPLE -> {
                return new PurplePaintballEntity(player, level);
            }
        }
        return null;
    }

    public PersistentProjectileEntity getPaintballRocket(PlayerEntity player, World level) {
        switch (this) {
            case RED -> {
                return new RedPaintballRocketEntity(player, level);
            }
            case GREEN -> {
                return new GreenPaintballRocketEntity(player, level);
            }
            case BLUE -> {
                return new BluePaintballRocketEntity(player, level);
            }
            case CYAN -> {
                return new CyanPaintballRocketEntity(player, level);
            }
            case MAGENTA -> {
                return new MagentaPaintballRocketEntity(player, level);
            }
            case LIME -> {
                return new LimePaintballRocketEntity(player, level);
            }
            case LIGHT_BLUE -> {
                return new LightBluePaintballRocketEntity(player, level);
            }
            case PINK -> {
                return new PinkPaintballRocketEntity(player, level);
            }
            case PURPLE -> {
                return new PurplePaintballRocketEntity(player, level);
            }
        }
        return null;
    }

    public Formatting getColor()
    {
        switch(this){
            case RED -> {return Formatting.DARK_RED;}
            case GREEN -> {return Formatting.DARK_GREEN;}
            case BLUE -> {return Formatting.DARK_BLUE;}
            case CYAN -> {return Formatting.DARK_AQUA;}
            case MAGENTA -> {return Formatting.DARK_PURPLE;}
            case LIME -> {return Formatting.GREEN;}
            case LIGHT_BLUE -> {return Formatting.AQUA;}
            case PINK -> {return Formatting.LIGHT_PURPLE;}
            case PURPLE -> {return Formatting.BLUE;}
        }
        return Formatting.WHITE;
    }

    public String getTranslationKey()
    {
        switch (this){
            case RED -> {return "text.mcpaintball.team.red";}
            case GREEN -> {return "text.mcpaintball.team.green";}
            case BLUE -> {return "text.mcpaintball.team.blue";}
            case CYAN -> {return "text.mcpaintball.team.cyan";}
            case MAGENTA -> {return "text.mcpaintball.team.magenta";}
            case LIME -> {return "text.mcpaintball.team.lime";}
            case LIGHT_BLUE -> {return "text.mcpaintball.team.light_blue";}
            case PINK -> {return "text.mcpaintball.team.pink";}
            case PURPLE -> {return "text.mcpaintball.team.purple";}
        }
        return "null";
    }
    public ItemStack[] GetTeamItems()
    {
        switch (this){
            case RED -> {return new ItemStack[]{new ItemStack(MCPaintballItems.RED_GRENADE),new ItemStack(MCPaintballBlocks.RED_EXPLOSIVE)};}
            case GREEN -> {return new ItemStack[]{new ItemStack(MCPaintballItems.GREEN_GRENADE),new ItemStack(MCPaintballBlocks.GREEN_EXPLOSIVE)};}
            case BLUE -> {return new ItemStack[]{new ItemStack(MCPaintballItems.BLUE_GRENADE),new ItemStack(MCPaintballBlocks.BLUE_EXPLOSIVE)};}
            case CYAN -> {return new ItemStack[]{new ItemStack(MCPaintballItems.CYAN_GRENADE),new ItemStack(MCPaintballBlocks.CYAN_EXPLOSIVE)};}
            case MAGENTA -> {return new ItemStack[]{new ItemStack(MCPaintballItems.MAGENTA_GRENADE),new ItemStack(MCPaintballBlocks.MAGENTA_EXPLOSIVE)};}
            case LIME -> {return new ItemStack[]{new ItemStack(MCPaintballItems.LIME_GRENADE),new ItemStack(MCPaintballBlocks.LIME_EXPLOSIVE)};}
            case LIGHT_BLUE -> {return new ItemStack[]{new ItemStack(MCPaintballItems.LIGHT_BLUE_GRENADE),new ItemStack(MCPaintballBlocks.LIGHT_BLUE_EXPLOSIVE)};}
            case PINK -> {return new ItemStack[]{new ItemStack(MCPaintballItems.PINK_GRENADE),new ItemStack(MCPaintballBlocks.PINK_EXPLOSIVE)};}
            case PURPLE -> {return new ItemStack[]{new ItemStack(MCPaintballItems.PURPLE_GRENADE),new ItemStack(MCPaintballBlocks.PURPLE_EXPLOSIVE)};}
        }
        return null;
    }
}

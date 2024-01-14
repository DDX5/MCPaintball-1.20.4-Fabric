package org.multicoder.mcpaintball.events;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.storage.RegionBasedStorage;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.config.MCPaintballConfig;
import org.multicoder.mcpaintball.utility.BookLoader;
import org.multicoder.mcpaintball.world.PaintballMatchData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URL;

public class MCPaintballServerLoaded
{
    public static NbtCompound Book;
    public static void onServerStarted(MinecraftServer server)
    {
        // Adding Guide Book Via Built-In
        MCPaintball.LOGGER.info("Adding Guide Book Via Built-In");
        try
        {
            BookLoader.Read();
            MCPaintball.LOGGER.info("Added Guide Book Via Built-In");
        }
        catch (Exception e)
        {
            MCPaintball.LOGGER.error("Failed To Load Guide Book Via Built-In");
            MCPaintball.LOGGER.error("Reason: " + e.getMessage());
        }
        MCPaintball.LOGGER.info("Attaching Data To Server");
        PaintballMatchData data = PaintballMatchData.getServerState(server);
        if (data.Points == null)
        {
            data.Points = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }
        try
        {
            MCPaintballConfig.LoadOrCreate(server);
        } catch (Exception e)
        {
            MCPaintball.LOGGER.info("Failed To Load Config, Using Default Parameters");
            MCPaintball.LOGGER.info("Reason: " + e.getMessage());
            MCPaintballConfig.MATCH_SERVER_OP = true;
            MCPaintballConfig.BLOCKS_BREAK = false;
        }
        MCPaintball.LOGGER.info("Attached Data To Server");
    }
}

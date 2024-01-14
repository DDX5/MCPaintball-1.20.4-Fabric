package org.multicoder.mcpaintball.config;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;
import org.multicoder.mcpaintball.MCPaintball;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.FileSystem;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class MCPaintballConfig {
    public static final String VERSION = "1.0.1";
    public static boolean MATCH_SERVER_OP;
    public static boolean BLOCKS_BREAK;

    public static void LoadOrCreate(MinecraftServer server) throws Exception
    {
        String Prefix = server.getSavePath(WorldSavePath.ROOT).toAbsolutePath().toString();
        Prefix = Prefix.substring(0,Prefix.length() - 2);
        String FolderPrefix = Prefix + "\\config\\";
        Prefix += "\\config\\mcpaintball.properties";
        MCPaintball.LOGGER.info("Path: " + Prefix);
        File Folder = new File(Prefix);
        if(!new File(FolderPrefix).exists())
        {
            MCPaintball.LOGGER.info("Creating Directory");
            new File(FolderPrefix).mkdir();
        }
        if (!Folder.exists()) {
            Folder.createNewFile();
            FileOutputStream FOS = new FileOutputStream(Folder);
            Properties Config = new Properties();
            Config.setProperty("mcpaintball.config_version", "1.0.1");
            Config.setProperty("gameplay.match_commands_op", "True");
            Config.setProperty("gameplay.world.blocks_break", "True");
            MATCH_SERVER_OP = true;
            BLOCKS_BREAK = false;
            Config.store(FOS, "The Configuration For MCPaintball");
        } else {
            FileInputStream FIS = new FileInputStream(Folder);
            Properties Config = new Properties();
            Config.load(FIS);
            if (!Config.containsKey("mcpaintball.config_version") || !Objects.equals(Config.getProperty("mcpaintball.config_version"), VERSION)) {
                FIS.close();
                FileOutputStream FOS = new FileOutputStream(Folder);
                Config = new Properties();
                Config.setProperty("mcpaintball.config_version", "1.0.1");
                Config.setProperty("gameplay.match_commands_op", "True");
                Config.setProperty("gameplay.world.blocks_break", "True");
                Config.store(FOS, "The Configuration For MCPaintball");
                MATCH_SERVER_OP = true;
                BLOCKS_BREAK = false;
            } else {
                MATCH_SERVER_OP = Boolean.parseBoolean(Config.getProperty("gameplay.match_commands.op"));
                BLOCKS_BREAK = Boolean.parseBoolean(Config.getProperty("gameplay.world.blocks_break"));
            }
        }
    }
}

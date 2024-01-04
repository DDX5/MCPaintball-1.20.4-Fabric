package org.multicoder.mcpaintball.config;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import org.apache.logging.log4j.util.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class MCPaintballConfig
{
    public static boolean MATCH_SERVER_OP;
    public static boolean ROCKETS_BREAK_BLOCKS;
    public static final String VERSION = "1.0.0";

    public static void LoadOrCreate(MinecraftServer server) throws Exception {
        String Prefix = server.getRunDirectory().getAbsolutePath();
        Prefix += "/config/mcpaintball.properties";
        File Folder = new File(Prefix);
        if(!Folder.exists())
        {
            Folder.createNewFile();
            FileOutputStream FOS = new FileOutputStream(Folder);
            Properties Config = new Properties();
            Config.setProperty("mcpaintball.config_version","1.0.0");
            Config.setProperty("gameplay.match_commands_op","True");
            Config.setProperty("gameplay.world.rockets_break_block","True");
            MATCH_SERVER_OP = true;
            ROCKETS_BREAK_BLOCKS = true;
            Config.store(FOS,"The Configuration For MCPaintball");
        }
        else
        {
            FileInputStream FIS = new FileInputStream(Folder);
            Properties Config = new Properties();
            Config.load(FIS);
            if(!Config.containsKey("mcpaintball.config_version") || !Objects.equals(Config.getProperty("mcpaintball.config_version"), VERSION))
            {
                FIS.close();
                FileOutputStream FOS = new FileOutputStream(Folder);
                Config = new Properties();
                Config.setProperty("mcpaintball.config_version","1.0.0");
                Config.setProperty("gameplay.match_commands_op","True");
                Config.setProperty("gameplay.world.rockets_break_block","True");
                Config.store(FOS,"The Configuration For MCPaintball");
                MATCH_SERVER_OP = true;
                ROCKETS_BREAK_BLOCKS = true;
            }
            else
            {
                MATCH_SERVER_OP = Boolean.parseBoolean(Config.getProperty("gameplay.match_commands.op"));
                ROCKETS_BREAK_BLOCKS = Boolean.parseBoolean(Config.getProperty("gameplay.world.rockets_break_blocks"));
            }
        }
    }
}

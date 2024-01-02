package org.multicoder.mcpaintball.utility;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import org.apache.logging.log4j.util.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.Properties;

public class MCPaintballConfig
{
    public static boolean MATCH_SERVER_OP;

    public static void LoadOrCreate(MinecraftServer server) throws Exception {
        String Prefix = server.getRunDirectory().getAbsolutePath();
        Prefix += "/config/mcpaintball.properties";
        File Folder = new File(Prefix);
        if(!Folder.exists())
        {
            Folder.createNewFile();
            FileOutputStream FOS = new FileOutputStream(Folder);
            Properties Config = new Properties();
            Config.setProperty("gameplay.match_commands_op","True");
            MATCH_SERVER_OP = true;
            Config.store(FOS,"The Configuration For MCPaintball");
        }
        else
        {
            FileInputStream FIS = new FileInputStream(Folder);
            Properties Config = new Properties();
            Config.load(FIS);
            MATCH_SERVER_OP = Boolean.parseBoolean(Config.getProperty("gameplay.match_commands.op"));
        }
    }
}

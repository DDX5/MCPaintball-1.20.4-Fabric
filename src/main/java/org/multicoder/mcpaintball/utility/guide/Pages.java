package org.multicoder.mcpaintball.utility.guide;

public class Pages
{
    public static final String PAGE1 = """
            '   \u00a73MCPaintball Guide

            \u00a70This guide book will help get you set up for running paintball games.

            before we begin there is a few things we need to go through before you can run any matches'""";

    public static final String PAGE2 = """
            '     \u00a73Configuration

            \u00a70This mod contains a single configuration that requires setting up. The file is located in the saves folder inside the subfolder called config

            There are 2 options, match_commands_op and blocks_break.'""";
    public static final String PAGE3 = """
            '  \\u00a73Match Commands Op

            \\u00a70This option is used to set whether the match commands are Server Op/Admin Only

            \\u00a74Default Value: True'""";
    public static final String PAGE4 = """
            '     \\u00a73Blocks Break

            \\u00a70This option is used to set whether the explosions created by this mod break blocks.

            \\u00a74Default Value: False'""";

    public static String[] GetPages()
    {
        return new String[]{PAGE1,PAGE2,PAGE3,PAGE4};
    }
}

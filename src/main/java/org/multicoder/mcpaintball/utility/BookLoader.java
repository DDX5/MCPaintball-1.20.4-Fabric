package org.multicoder.mcpaintball.utility;

import com.google.gson.*;
import net.minecraft.nbt.*;
import net.minecraft.util.Util;
import org.multicoder.mcpaintball.MCPaintball;
import org.multicoder.mcpaintball.events.MCPaintballServerLoaded;
import org.multicoder.mcpaintball.utility.guide.GuideBookInfo;
import org.multicoder.mcpaintball.utility.guide.Pages;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BookLoader
{
    public static void Read()
    {
        NbtCompound BookNBT = new NbtCompound();
        BookNBT.putString("author", GuideBookInfo.AUTHOR);
        BookNBT.putString("title",GuideBookInfo.TITLE);
        NbtList PagesTag = new NbtList();
        String[] PagesString = Pages.GetPages();
        for(String Page : PagesString)
        {
            PagesTag.add(NbtString.of(Page));
        }
        BookNBT.put("pages",PagesTag);
        MCPaintballServerLoaded.Book = BookNBT;
    }
}

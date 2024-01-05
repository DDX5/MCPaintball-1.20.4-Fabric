package org.multicoder.mcpaintball.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.multicoder.mcpaintball.utility.PaintballClass;

import java.util.concurrent.CompletableFuture;

public class PaintballClassArgument implements ArgumentType<PaintballClass> {
    private PaintballClassArgument() {

    }

    public static PaintballClassArgument classArgument() {
        return new PaintballClassArgument();
    }

    public static PaintballClass getClass(CommandContext<?> context, String name) {
        return context.getArgument(name, PaintballClass.class);
    }

    @Override
    public PaintballClass parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readString().toUpperCase();
        return PaintballClass.valueOf(name);
    }


    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (PaintballClass team : PaintballClass.values()) {
            builder.suggest(team.name());
        }
        return builder.buildFuture();
    }
}

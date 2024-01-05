package org.multicoder.mcpaintball.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.multicoder.mcpaintball.utility.PaintballTeam;

import java.util.concurrent.CompletableFuture;

public class PaintballTeamArgument implements ArgumentType<PaintballTeam> {
    private PaintballTeamArgument() {

    }

    public static PaintballTeamArgument teamArgument() {
        return new PaintballTeamArgument();
    }

    public static PaintballTeam getTeam(CommandContext<?> context, String name) {
        return context.getArgument(name, PaintballTeam.class);
    }

    @Override
    public PaintballTeam parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readString().toUpperCase();
        return PaintballTeam.valueOf(name);
    }


    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (PaintballTeam team : PaintballTeam.values()) {
            builder.suggest(team.name());
        }
        return builder.buildFuture();
    }
}

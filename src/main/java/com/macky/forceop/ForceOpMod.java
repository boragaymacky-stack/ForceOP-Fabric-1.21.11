package com.macky.forceop;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Server-side ForceOP mod for Minecraft 1.21.11.
 *
 * The /forceop command intentionally has no permission requirement.
 * Install this only on servers where that behavior is desired.
 */
public final class ForceOpMod implements ModInitializer {
    public static final String MOD_ID = "forceop";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                Commands.literal("forceop")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(context -> {
                            ServerPlayer target = EntityArgument.getPlayer(context, "player");
                            context.getSource().getServer().getPlayerList().op(target.nameAndId());

                            context.getSource().sendSuccess(
                                () -> Component.literal("ForceOP: " + target.getGameProfile().name() + " is now OP."),
                                true
                            );
                            return 1;
                        }))
            );

            dispatcher.register(
                Commands.literal("fop")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(context -> {
                            ServerPlayer target = EntityArgument.getPlayer(context, "player");
                            context.getSource().getServer().getPlayerList().op(target.nameAndId());
                            context.getSource().sendSuccess(
                                () -> Component.literal("ForceOP: " + target.getGameProfile().name() + " is now OP."),
                                true
                            );
                            return 1;
                        }))
            );
        });
    }
}

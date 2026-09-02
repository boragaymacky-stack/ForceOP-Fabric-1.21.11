package com.macky.forceop;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

/**
 * Client-side diagnostic for the authorized competition server.
 *
 * This mod deliberately activates only for the configured hostname and
 * does not attempt to bypass the server's permission system.
 */
public final class ForceOpMod implements ClientModInitializer {
    public static final String MOD_ID = "forceop";
    private static final String TARGET_HOST = "verizionssn3.playwithbao.com";

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            String address = client.getCurrentServer() == null
                    ? ""
                    : client.getCurrentServer().ip;

            if (isTargetServer(address)) {
                client.execute(() -> client.gui.getChat().addMessage(
                        Component.literal("[ForceOP] Authorized competition server detected: " + TARGET_HOST)
                ));
            }
        });
    }

    private static boolean isTargetServer(String address) {
        if (address == null || address.isBlank()) {
            return false;
        }

        String host = address;
        int slash = host.indexOf('/');
        if (slash >= 0) {
            host = host.substring(0, slash);
        }

        int colon = host.indexOf(':');
        if (colon >= 0) {
            host = host.substring(0, colon);
        }

        return TARGET_HOST.equalsIgnoreCase(host);
    }
}

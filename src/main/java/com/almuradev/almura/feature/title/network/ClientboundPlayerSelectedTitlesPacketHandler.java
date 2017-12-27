/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.almura.feature.title.network;

import com.almuradev.almura.feature.title.TitleManager;
import org.spongepowered.api.Platform;
import org.spongepowered.api.network.MessageHandler;
import org.spongepowered.api.network.RemoteConnection;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

public final class ClientboundPlayerSelectedTitlesPacketHandler implements MessageHandler<ClientboundPlayerSelectedTitlesPacket> {

    private final TitleManager manager;

    @Inject
    public ClientboundPlayerSelectedTitlesPacketHandler(final TitleManager manager) {
        this.manager = manager;
    }

    @Override
    public void handleMessage(ClientboundPlayerSelectedTitlesPacket message, RemoteConnection connection, Platform.Type side) {
        if (side.isClient()) {
            this.manager.putSelectedTitles(message.titles);

            final Map<UUID, String> selectedTitles = new HashMap<>();

            for (Map.Entry<UUID, Text> titleEntry : message.titles.entrySet()) {
                selectedTitles.put(titleEntry.getKey(), TextSerializers.LEGACY_FORMATTING_CODE.serialize(titleEntry.getValue()));
            }

            // Cache serialized forms
            this.manager.putClientSelectedTitles(selectedTitles);
        }
    }
}

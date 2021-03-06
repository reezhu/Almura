/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.almura.feature.title.network;

import com.almuradev.almura.feature.title.Title;
import com.almuradev.almura.shared.util.PacketUtil;
import com.almuradev.almura.shared.util.SerializationUtil;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.Message;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

public final class ClientboundTitlesRegistryPacket implements Message {

    @Nullable public Set<Title> titles;

    public ClientboundTitlesRegistryPacket() {
    }

    public ClientboundTitlesRegistryPacket(@Nullable final Set<Title> titles) {
        this.titles = titles;
    }

    @Override
    public void readFrom(final ChannelBuf buf) {
        final int count = buf.readVarInt();

        if (count > 0) {
            this.titles = new HashSet<>();

            for (int i = 0; i < count; i++) {
                try {
                    this.titles.add(SerializationUtil.bytesToObject(buf.readBytes(buf.readVarInt())));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void writeTo(final ChannelBuf buf) {
        buf.writeVarInt(this.titles == null ? 0 : this.titles.size());

        if (this.titles != null) {
            for (Title title : this.titles) {
                try {
                    final byte[] data = SerializationUtil.objectToBytes(title);
                    buf.writeVarInt(data.length);
                    buf.writeBytes(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

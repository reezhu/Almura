/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.almura.feature.menu.ingame;

import com.almuradev.almura.feature.menu.ingame.network.ServerboundFeaturesOpenRequestPacket;
import com.almuradev.almura.shared.client.keyboard.binder.KeyBindingEntry;
import com.almuradev.almura.shared.network.NetworkConfig;
import com.almuradev.core.event.Witness;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelId;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@SideOnly(Side.CLIENT)
public final class ClientFeaturesManager implements Witness {

    private final ChannelBinding.IndexedMessageChannel network;
    private final KeyBinding featuresOpenBinding;

    @Inject
    public ClientFeaturesManager(final @ChannelId(NetworkConfig.CHANNEL) ChannelBinding.IndexedMessageChannel network, final Set<KeyBindingEntry>
            keybindings) {
        this.network = network;
        this.featuresOpenBinding = keybindings.stream().map(KeyBindingEntry::getKeybinding).filter((keyBinding -> keyBinding
                .getKeyDescription().equalsIgnoreCase("key.almura.features.open"))).findFirst().orElse(null);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (featuresOpenBinding.isPressed()) {
            this.network.sendToServer(new ServerboundFeaturesOpenRequestPacket());
        }
    }
}

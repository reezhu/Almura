/**
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) 2014 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.core.mixin;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(SimpleReloadableResourceManager.class)
public abstract class MixinSimpleReloadableResourceManager implements IReloadableResourceManager {

    @Shadow
    public List reloadListeners;
    private boolean reload = false;

    @Overwrite
    private void notifyReloadListeners() {

        for (Object reloadListener : reloadListeners) {
            IResourceManagerReloadListener iresourcemanagerreloadlistener = (IResourceManagerReloadListener) reloadListener;
            if (iresourcemanagerreloadlistener.getClass() == TextureManager.class && Loader.instance().hasReachedState(LoaderState.AVAILABLE)
                && !reload) {
                reload = true;
                continue;
            }
            iresourcemanagerreloadlistener.onResourceManagerReload(this);
        }
    }
}
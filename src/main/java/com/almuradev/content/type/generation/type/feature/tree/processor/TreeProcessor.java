/*
 * This file is part of Almura.
 *
 * Copyright (c) AlmuraDev <https://github.com/AlmuraDev/>
 *
 * All Rights Reserved.
 */
package com.almuradev.content.type.generation.type.feature.tree.processor;

import com.almuradev.content.registry.delegate.CatalogDelegate;
import com.almuradev.content.type.generation.type.feature.tree.TreeGenerator;
import com.almuradev.content.type.generation.type.feature.tree.TreeGeneratorConfig;
import com.almuradev.content.type.tree.Tree;
import com.almuradev.toolbox.config.tag.ConfigTag;
import ninja.leaping.configurate.ConfigurationNode;

public final class TreeProcessor implements AbstractTreeProcessor {
    private static final ConfigTag TAG = ConfigTag.create(TreeGeneratorConfig.TREE);

    @Override
    public ConfigTag tag() {
        return TAG;
    }

    @Override
    public void processTagged(final ConfigurationNode config, final TreeGenerator.Builder context) {
        context.tree(CatalogDelegate.namespaced(Tree.class, config));
    }
}

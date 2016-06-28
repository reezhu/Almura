/**
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.block.builder;

import static com.google.common.base.Preconditions.checkNotNull;

import com.almuradev.almura.api.block.BuildableBlockType;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class AbstractBlockTypeBuilder<BLOCK extends BuildableBlockType, BUILDER extends AbstractBlockTypeBuilder<BLOCK, BUILDER>> implements
        BuildableBlockType.Builder<BLOCK, BUILDER> {
    protected CreativeTabs tabs;
    protected Material material;
    protected MapColor mapColor;

    public AbstractBlockTypeBuilder() {
        this.reset();
    }

    @Override
    public BUILDER material(Material material) {
        checkNotNull(material);
        this.material = material;
        return (BUILDER) this;
    }

    @Override
    public BUILDER mapColor(MapColor mapColor) {
        checkNotNull(mapColor);
        this.mapColor = mapColor;
        return (BUILDER) this;
    }

    @Override
    public BUILDER creativeTab(CreativeTabs tab) {
        this.tabs = tab;
        return (BUILDER) this;
    }

    @Override
    public BUILDER from(BuildableBlockType value) {
        checkNotNull(value);
        final Block block = (Block) value;
        material(block.getMaterial(block.getDefaultState()));
        mapColor(block.getMapColor(block.getDefaultState()));
        return (BUILDER) this;
    }

    @Override
    public BUILDER reset() {
        this.tabs = null;
        this.material = Material.GROUND;
        this.mapColor = MapColor.DIRT;
        return (BUILDER) this;
    }
}

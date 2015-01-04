/**
 * This file is part of Almura, All Rights Reserved.
 *
 * Copyright (c) 2014 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almura.client.gui.menu;

import java.awt.Color;

import com.almuradev.almura.Configuration;
import com.almuradev.almura.client.ChatColor;
import com.almuradev.almura.client.gui.AlmuraBackgroundGui;
import com.almuradev.almura.client.gui.AlmuraGui;
import com.flowpowered.cerealization.config.ConfigurationException;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.client.GuiModList;
import net.malisis.core.client.gui.Anchor;
import net.malisis.core.client.gui.component.container.UIBackgroundContainer;
import net.malisis.core.client.gui.component.control.UIMoveHandle;
import net.malisis.core.client.gui.component.decoration.UILabel;
import net.malisis.core.client.gui.component.decoration.UIMultiLineLabel;
import net.malisis.core.client.gui.component.interaction.UIButton;
import net.malisis.core.client.gui.component.interaction.UICheckBox;

public class AlmuraAboutMenu extends AlmuraBackgroundGui {

    private UIBackgroundContainer window;
    private UIButton modsButton, websiteButton, closeButton;
    private UILabel titleLabel;
    private UIMultiLineLabel aboutUsLabel;

    /**
     * Creates an gui with a parent screen and calls {@link AlmuraGui#setup}, if the parent is null then no background will be added

     * @param parent the {@link AlmuraGui} that we came from
     */
    public AlmuraAboutMenu(AlmuraGui parent) {
        super(parent);
    }

    @Override
    protected void setup() {
        // Create the window container
        window = new UIBackgroundContainer(this);
        window.setSize(300, 225);
        window.setAnchor(Anchor.CENTER | Anchor.MIDDLE);
        window.setColor(Integer.MIN_VALUE);
        window.setBackgroundAlpha(125);

        final int padding = 4;

        // Create the title label
        titleLabel = new UILabel(this, ChatColor.AQUA + "About Almura 2.0");
        titleLabel.setFontScale(1.2F);
        titleLabel.setPosition(0, padding, Anchor.CENTER | Anchor.TOP);
        
        // Create About us multi-line label
        aboutUsLabel = new UIMultiLineLabel(this, "");
       
        String fieldText = "Almura 2.0 began June 1st, 2014.  Based on the idea that we could finally get away from the broken and abandoned Spoutcraft client a brilliant developer came to Almura and said, ";
        fieldText = fieldText + "why don't you get rid of that out of date client and move into the present?  This brilliant developers name is " + ChatColor.AQUA + "Zidane" + ChatColor.RESET + ".  Along with him and another outstanding developer " + ChatColor.AQUA + "Grinch" + ChatColor.RESET + ", ";
        fieldText = fieldText + "Almura 2.0 was born.  Using the forge client as our basis these two developers along with " + ChatColor.GOLD + "Zidane" + ChatColor.RESET + " content and gui abilites built in our opinion one of the best content loading / ";
        fieldText = fieldText + "gui enabled Minecraft game ever conceived.  " + ChatColor.LIGHT_PURPLE + "More info to follow..." + ChatColor.RESET + "";
        
        aboutUsLabel.setText(fieldText);
        aboutUsLabel.setSize(290, 200);
        aboutUsLabel.setColor(Color.WHITE.getRGB());
        aboutUsLabel.setPosition(0, 25, Anchor.CENTER | Anchor.CENTER);
        aboutUsLabel.setName("mline.aboutus");
        
        // Create the mods button
        modsButton = new UIButton(this, "Mods");
        modsButton.setSize(50, 16);
        modsButton.setPosition( 5, -5, Anchor.LEFT | Anchor.BOTTOM);
        modsButton.setName("button.mods");
        modsButton.register(this);        
        
        // Create the close button
        closeButton = new UIButton(this, "Close");
        closeButton.setSize(50, 16);
        closeButton.setPosition( -5, -5, Anchor.RIGHT | Anchor.BOTTOM);
        closeButton.setName("button.close");
        closeButton.register(this);

        window.add(titleLabel, aboutUsLabel, modsButton, closeButton);

        // Allow the window to move
        new UIMoveHandle(this, window);

        addToScreen(window);
    }

    @Subscribe
    public void onButtonClick(UIButton.ClickEvent event) {
        switch (event.getComponent().getName().toLowerCase()) {
            case "button.mods":           
                mc.displayGuiScreen(new GuiModList(this));
                break;
            case "button.close":
                displayParent();
                break;            
        }
    }
}
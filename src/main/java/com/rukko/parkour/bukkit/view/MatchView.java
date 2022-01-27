package com.rukko.parkour.bukkit.view;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.builder.ItemBuilder;
import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.model.user.match.Match;
import com.rukko.parkour.settings.Messages;
import com.rukko.parkour.settings.Settings;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;

import java.util.List;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public class MatchView extends PaginatedView<Match> {

    private final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    public MatchView() {
        super(5, Settings.INVENTORY_MATCHES_TITLE.asString());

        setCancelOnClick(true);

        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "<OOOOOOO>",
                "XOOOOOOOX",
                "XXXXXXXXX"
        );
    }

    @Override
    protected void onRender(ViewContext context) {
        final List<Match> matches = context.get("matches");
        if (matches.isEmpty())
            context.slot(22).withItem(new ItemBuilder(Material.BARRIER)
                    .name(Messages.ITEM_MATCH_EMPTY_NAME.get())
                    .lore(Messages.ITEM_MATCH_EMPTY_LORE.get())
                    .build());

        setSource(matches);
    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Match> render, ViewItem item, Match match) {
        item.withItem(new ItemBuilder(Material.PAPER)
                .name(PLUGIN.getReplaceManagement().getReplacedLine(null, Messages.ITEM_MATCH_NAME.get(), match))
                .lore(PLUGIN.getReplaceManagement().getReplacedLines(null, Messages.ITEM_MATCH_LORE.asList(), match))
                .build());
    }
}

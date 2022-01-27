package com.rukko.parkour.bukkit.view;

import com.rukko.parkour.ParkourPlugin;
import com.rukko.parkour.backend.builder.ItemBuilder;
import com.rukko.parkour.backend.format.FormatTime;
import com.rukko.parkour.model.Ranking;
import com.rukko.parkour.settings.Messages;
import com.rukko.parkour.settings.Settings;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewContext;
import me.saiintbrisson.minecraft.ViewItem;
import org.bukkit.Material;

import java.util.List;

 /**
  * This file is part of a ViiictorXD project
  *
  * Copyright (c) ViiictorXD
  * https://github.com/viiictorxd
  **/
public class RankingView extends PaginatedView<Ranking> {

     private final ParkourPlugin PLUGIN = ParkourPlugin.getPlugin();

    public RankingView() {
        super(5, Settings.INVENTORY_RANKING_TITLE.asString());

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
        final List<Ranking> rankings = context.get("rankings");
        if (rankings.isEmpty())
            context.slot(22).withItem(new ItemBuilder(Material.BARRIER)
                    .name(Messages.ITEM_RANKING_EMPTY_NAME.get())
                    .lore(Messages.ITEM_RANKING_EMPTY_LORE.get())
                    .build());

        setSource(rankings);
    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Ranking> render, ViewItem item, Ranking ranking) {
        item.withItem(new ItemBuilder(Material.PAPER)
                .name("§a" + ranking.getPosition() + "° " + ranking.getPlayerName())
                .lore(
                        "§7Arena Name: §f" + ranking.getArenaName(),
                        "§7Result: " + ranking.getResult().beautifulName(),
                        "",
                        "§7Elapse Time: §f" + FormatTime.buildString(ranking.getElapsed()),
                        "§7Date: §f" + FormatTime.format(ranking.getDate())
                )
                .build());

        item.withItem(new ItemBuilder(Material.PAPER)
                .name(PLUGIN.getReplaceManagement().getReplacedLine(null, Messages.ITEM_RANKING_NAME.get(), ranking))
                .lore(PLUGIN.getReplaceManagement().getReplacedLines(null, Messages.ITEM_RANKING_LORE.asList(), ranking))
                .build());
    }
}

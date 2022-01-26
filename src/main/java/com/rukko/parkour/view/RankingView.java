package com.rukko.parkour.view;

import com.rukko.parkour.builder.ItemBuilder;
import com.rukko.parkour.format.FormatTime;
import com.rukko.parkour.model.Ranking;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewContext;
import me.saiintbrisson.minecraft.ViewItem;
import org.bukkit.Material;

import java.util.List;

/**
 * @author ViiictorXD
 */
public class RankingView extends PaginatedView<Ranking> {

    public RankingView() {
        super(5, "Ranking");

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
                    .name("§cEmpty")
                    .lore(
                            "§cYou haven't entered any arenas yet. "
                    )
                    .build());

        setSource(rankings);
    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Ranking> render, ViewItem item, Ranking ranking) {
        item.withItem(new ItemBuilder(Material.PAPER)
                .name("§a" + ranking.getPosition() + "° " + ranking.getArenaName())
                .lore(
                        "§7Arena Name: §f" + ranking.getArenaName(),
                        "§7Result: " + ranking.getResult().beautifulName(),
                        "",
                        "§7Elapse Time: §f" + FormatTime.buildString(ranking.getElapsed()),
                        "§7Date: §f" + FormatTime.format(ranking.getDate())
                )
                .build());
    }
}

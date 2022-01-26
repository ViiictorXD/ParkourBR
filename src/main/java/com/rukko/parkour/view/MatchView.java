package com.rukko.parkour.view;

import com.rukko.parkour.builder.ItemBuilder;
import com.rukko.parkour.format.FormatTime;
import com.rukko.parkour.model.user.match.Match;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;

import java.util.List;

/**
 * @author ViiictorXD
 */
public class MatchView extends PaginatedView<Match> {

    public MatchView() {
        super(5, "Matches");

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
                    .name("§cEmpty")
                    .lore(
                            "§cYou haven't entered any arenas yet. "
                    )
                    .build());

        setSource(matches);
    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Match> render, ViewItem item, Match match) {
        item.withItem(new ItemBuilder(Material.PAPER)
                .name("§a" + match.getUniqueId())
                .lore(
                        "§7Parkour Name: §f" + match.getParkourName(),
                        "§7Result: " + match.getResult().beautifulName(),
                        "",
                        "§7Elapse Time: §f" + FormatTime.buildString(match.getTime()),
                        "§7Date: §f" + FormatTime.format(match.getDate())
                )
                .build());
    }
}

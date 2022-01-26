package com.rukko.parkour.manager;

import com.rukko.parkour.replace.Replace;
import com.rukko.parkour.replace.impl.ArenaReplace;
import com.rukko.parkour.replace.impl.CasualReplace;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ViiictorXD
 */

@Getter
public class ReplaceManagement {

    private final Set<Replace> replaces = new HashSet<>();

    public void constructor(Replace replace) {
        replaces.add(replace);
    }

    public String getReplacedLine(Player player, String line) {
        for (Replace replace : replaces)
            line = replace.replace(player, line);

        return line;
    }

    public List<String> getReplacedLines(Player player, List<String> lines) {
        final List<String> clone = new ArrayList<>(lines);
        for (int index = 0; index < lines.size(); index++)
            clone.set(index, getReplacedLine(player, clone.get(index)));

        return clone;
    }

    public void registerDefaults() {
        replaces.add(new ArenaReplace());
        replaces.add(new CasualReplace());
    }
}

package com.rukko.parkour;

import com.rukko.parkour.board.FastBoard;
import com.rukko.parkour.builder.ItemBuilder;
import com.rukko.parkour.command.arena.*;
import com.rukko.parkour.command.parkour.*;
import com.rukko.parkour.connection.ConnectionFactory;
import com.rukko.parkour.connection.sql.SqliteConnectionFactory;
import com.rukko.parkour.listener.BaseListener;
import com.rukko.parkour.listener.ConnectionListener;
import com.rukko.parkour.listener.PlayerListener;
import com.rukko.parkour.loadable.impl.ParkourLoadable;
import com.rukko.parkour.manager.*;
import com.rukko.parkour.model.Board;
import com.rukko.parkour.model.arena.Arena;
import com.rukko.parkour.model.user.Content;
import com.rukko.parkour.model.user.User;
import com.rukko.parkour.repository.user.MatchRepository;
import com.rukko.parkour.repository.user.MatchRepositoryImpl;
import com.rukko.parkour.serialization.Serializations;
import com.rukko.parkour.view.MatchView;
import com.rukko.parkour.view.RankingView;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.ViewItem;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author ViiictorXD
 */

@Getter
public class ParkourPlugin extends JavaPlugin {

    private ConnectionFactory connectionFactory;

    private UserManagement userManagement;
    private BoardManagement boardManagement;
    private ParkourManagement parkourManagement;
    private RankingManagement rankingManagement;
    private ReplaceManagement replaceManagement;
    private ArenaManagement arenaManagement;

    private ParkourBoard parkourBoard;
    private ParkourLoadable parkourLoadable;

    private MatchRepository matchRepository;

    private ViewFrame viewFrame;
    private BukkitFrame commandFrame;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        connectionFactory = new SqliteConnectionFactory(getDataFolder(), "parkour_database");
        connectionFactory.connect();

        userManagement = new UserManagement();
        boardManagement = new BoardManagement();
        parkourManagement = new ParkourManagement();
        rankingManagement = new RankingManagement();
        replaceManagement = new ReplaceManagement();
        arenaManagement = new ArenaManagement();

        replaceManagement.registerDefaults();

        parkourBoard = new ParkourBoard();
        parkourLoadable = new ParkourLoadable(this);

        matchRepository = new MatchRepositoryImpl(connectionFactory);

        parkourBoard.load();
        parkourLoadable.load();

        matchRepository.createNonExistentTable();

        rankingManagement.setRankings(matchRepository.getRanking());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ParkourArenaTask(this), 20L, 20L);
        Bukkit.getOnlinePlayers().forEach(this::constructor);

        registerViews();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        if (connectionFactory != null && connectionFactory.hasConnection())
            connectionFactory.disconnect();

        for (Player player : Bukkit.getOnlinePlayers()) {
            final Arena arena = arenaManagement.match(player.getUniqueId());

            if (arena == null)
                continue;

            final User user = userManagement.match(player.getUniqueId());

            final Content content = user.getContent();
            final PlayerInventory inventory = player.getInventory();

            inventory.setContents(Serializations.ITEM_STACK_VET_SERIALIZATION.deserialize(content.getInventoryContent()));
            inventory.setArmorContents(Serializations.ITEM_STACK_VET_SERIALIZATION.deserialize(content.getArmorContent()));
        }
    }

    private void registerViews() {
        viewFrame = new ViewFrame(this);

        viewFrame.setDefaultNextPageItem(context -> context.isLastPage() ? null : new ViewItem()
                .withItem(new ItemBuilder(Material.ARROW).name("§aNext Page").build()));

        viewFrame.setDefaultPreviousPageItem(context -> context.isFirstPage() ? null : new ViewItem()
                .withItem(new ItemBuilder(Material.ARROW).name("§cPrevious Page").build()));

        viewFrame.register(
                new MatchView(),
                new RankingView()
        );
    }

    private void registerCommands() {
        commandFrame = new BukkitFrame(this);

        final MessageHolder messageHolder = commandFrame.getMessageHolder();
        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cYou don't have permission to execute that command.");

        commandFrame.registerCommands(
                new CommandArena(),
                new CommandArenaAdd(this),
                new CommandArenaCreate(this),
                new CommandArenaDelete(this),
                new CommandArenaRemove(this),
                new CommandArenaSet(this),

                new CommandParkour(),
                new CommandParkourStart(this),
                new CommandParkourMatches(this),
                new CommandParkourRanking(this),
                new CommandParkourLeave(this)
        );
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new BaseListener(userManagement, matchRepository, boardManagement, parkourBoard), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(arenaManagement), this);
    }

    public void constructor(Player player) {
        if (!boardManagement.exists(player)) {
            final FastBoard board = new FastBoard(player);
            board.updateTitle(parkourBoard.getTitle());

            boardManagement.constructor(new Board(player, board));
        }

        if (!userManagement.exists(player.getUniqueId())) {
            final User user = new User(player.getUniqueId(), player.getName());
            user.setMatches(matchRepository.match(user.getUniqueId()));

            userManagement.constructor(user);
        }
    }

    public static ParkourPlugin getPlugin() {
        return getPlugin(ParkourPlugin.class);
    }
}

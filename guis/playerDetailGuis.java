package org.minecraft.minecraft.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.minecraft.minecraft.DatabaseCommands.playerDetailSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class playerDetailGuis {

    public static Inventory getPlayerDetails(String playerName) throws SQLException {

        ResultSet details = playerDetailSql.getDetails(Objects.requireNonNull(Bukkit.getPlayer(playerName)));

        Inventory profile = Bukkit.createInventory(null, 27, ChatColor.RED + "PlayerProfile");

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        ItemStack village = new ItemStack(Material.BELL);
        ItemStack clan = new ItemStack(Material.WHITE_BANNER);
        ItemStack rank = new ItemStack(Material.CYAN_BANNER);
        ItemStack team = new ItemStack(Material.RED_BANNER);
        ItemStack bio = new ItemStack(Material.OAK_SIGN);
        ItemStack status = new ItemStack(Material.SLIME_BALL);
        ItemStack nonBlack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        assert playerHeadMeta != null;
        playerHeadMeta.setDisplayName(playerName);
        playerHead.setItemMeta(playerHeadMeta);


        ItemMeta villageMeta = village.getItemMeta();
        assert villageMeta != null;
        villageMeta.setDisplayName(details.getString("villageName"));
        village.setItemMeta(villageMeta);


        ItemMeta clanMeta = clan.getItemMeta();
        assert clanMeta != null;
        clanMeta.setDisplayName(details.getString("clanName"));
        clan.setItemMeta(clanMeta);


        ItemMeta rankMeta = rank.getItemMeta();
        assert rankMeta != null;
        rankMeta.setDisplayName(details.getString("rank"));
        rank.setItemMeta(rankMeta);

        ItemMeta teamMeta = team.getItemMeta();
        assert teamMeta != null;
        teamMeta.setDisplayName(details.getString("team"));
        team.setItemMeta(teamMeta);

        ItemMeta bioMeta = bio.getItemMeta();
        assert bioMeta != null;
        bioMeta.setDisplayName(details.getString("bio"));
        bio.setItemMeta(bioMeta);

        ItemMeta statusMeta = status.getItemMeta();
        assert statusMeta != null;
        statusMeta.setDisplayName(details.getString("status"));
        status.setItemMeta(statusMeta);

        ItemMeta nonBlackMeta = nonBlack.getItemMeta();
        assert nonBlackMeta != null;
        nonBlackMeta.setDisplayName(" ");
        nonBlack.setItemMeta(nonBlackMeta);


        profile.setItem(10, playerHead);
        profile.setItem(11, village);
        profile.setItem(12, clan);
        profile.setItem(13, rank);
        profile.setItem(14, team);
        profile.setItem(15, bio);
        profile.setItem(16, status);

        for (int i = 0; i<= 26; i++){
            if (i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16){
                continue;
            }
            profile.setItem(i, nonBlack);
        }

        return profile;

    }

}

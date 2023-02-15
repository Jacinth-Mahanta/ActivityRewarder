package me.dave.activityrewarder.events;

import me.dave.activityrewarder.ActivityRewarder;
import me.dave.activityrewarder.datamanager.RewardUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RewardUserEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ActivityRewarder.dataManager.loadRewardUser(player.getUniqueId()).thenAccept((rewardUser) -> rewardUser.setUsername(player.getName()));
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        RewardUser rewardUser = ActivityRewarder.dataManager.getRewardUser(player.getUniqueId());
        ActivityRewarder.dataManager.saveRewardUser(rewardUser);
    }
}

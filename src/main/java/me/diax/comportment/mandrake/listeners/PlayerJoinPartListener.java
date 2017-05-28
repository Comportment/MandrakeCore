/*
 * Copyright 2017 Comportment | comportment@diax.me
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.diax.comportment.mandrake.listeners;

import me.diax.comportment.mandrake.Main;
import me.diax.comportment.mandrake.util.ChatUtil;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.inject.Inject;

/**
 * Created by Comportment at 15:17 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class PlayerJoinPartListener implements Listener {

    private final Main plugin;

    @Inject
    public PlayerJoinPartListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Server server = plugin.getServer();
        String name = player.getDisplayName();
        event.setJoinMessage("&8[&a+&8] " + name);
        if (!player.hasPlayedBefore()) {
            server.broadcastMessage(ChatUtil.colorise(String.format("&dWelcome for the first time %s!", name)));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Server server = player.getServer();
        String name = player.getDisplayName();
        event.setQuitMessage("&8[&c-&8] " + name);
        if (player.isBanned()) {
            server.broadcastMessage(String.format("&dLooks like we won't be seeing %s for a while...", name));
        }
    }
}
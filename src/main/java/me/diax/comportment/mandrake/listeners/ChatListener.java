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

import me.diax.comportment.mandrake.api.MandrakePlayer;
import me.diax.comportment.mandrake.util.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Comportment at 20:28 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class ChatListener implements Listener {

    private final Logger logger;

    @Inject
    public ChatListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player p = event.getPlayer();
        ChatChannel channel;
        Set<Player> receps = event.getRecipients();
        switch (message.charAt(0)) {
            //Global chat
            case '!': {
                channel = ChatChannel.GLOBAL;
                message = message.replaceFirst(Pattern.quote("!"), "");
                break;
            }
            //Staff chat
            case '#': {
                if (p.hasPermission(Rank.STAFF.getPermission())) {
                    channel = ChatChannel.STAFF;
                    message = message.replaceFirst(Pattern.quote("#"), "");
                    receps = receps.stream().filter(p2 -> p2.hasPermission(Rank.STAFF.getPermission())).collect(Collectors.toSet());
                    break;
                }
            }
            //Dev chat
            case '$': {
                if (p.hasPermission(Rank.DEVELOPER.getPermission())) {
                    channel = ChatChannel.DEVELOPER;
                    message = message.replaceFirst(Pattern.quote("$"), "");
                    receps = receps.stream().filter(p2 -> p2.hasPermission(Rank.DEVELOPER.getPermission())).collect(Collectors.toSet());
                    break;
                }
            }
            //House chat
            case '?': {
                //TODO: IMplement
            }
            //Local chat
            default: {
                channel = ChatChannel.LOCAL;
                receps = receps.stream().filter(p2 -> p2.getLocation().distanceSquared(p.getLocation()) < 50).collect(Collectors.toSet());
                break;
            }
        }
        event.setCancelled(true);
        String msg = message;
        receps.forEach(player -> player.sendMessage(String.format("%s[%s] %s %s%s: %s%s", channel.getColor(), channel.getSname(), new MandrakePlayer(p).getColouredTag(), ChatColor.GRAY, p.getDisplayName(), ChatColor.GRAY, msg)));
        logger.info(String.format("[%s] %s %s: %s", channel.getName(), new MandrakePlayer(p).getTag(), p.getDisplayName(), msg));
    }

    private enum ChatChannel {
        GLOBAL('!', "Global", "G", ChatColor.GREEN),
        LOCAL(' ', "Local", "L", ChatColor.LIGHT_PURPLE),
        STAFF('#', "Staff", "S", ChatColor.DARK_PURPLE),
        DEVELOPER('$', "Developer", "D", ChatColor.DARK_BLUE);

        char prefix;
        String name;
        String sname;
        ChatColor color;

        ChatChannel(char prefix, String name, String sname, ChatColor color) {
            this.prefix = prefix;
            this.name = name;
            this.sname = sname;
            this.color = color;
        }

        char getPrefix() {
            return prefix;
        }

        public String getName() {
            return name;
        }

        public String getSname() {
            return sname;
        }

        public ChatColor getColor() {
            return color;
        }
    }
}
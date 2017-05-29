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

package me.diax.comportment.mandrake.util;

import org.bukkit.ChatColor;

/**
 * Created by Comportment at 23:10 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public enum Rank {

    PLAYER(ChatColor.GRAY, "Player", "mandrake.rank.player", false),
    //STAFF(ChatColor.BLUE, "Staff", "mandrake.rank.staff", PLAYER),
    MOD(ChatColor.YELLOW, "Mod", "mandrake.rank.mod", true),
    ADMIN(ChatColor.DARK_RED, "Admin", "mandrake.rank.admin", true),
    DEVELOPER(ChatColor.BLUE, "Developer", "mandrake.rank.developer", true),
    OWNER(ChatColor.AQUA, "Owner", "mandrake.rank.owner", true);

    private ChatColor color;
    private String name;
    private String permission;
    private boolean isStaff;

    Rank(ChatColor color, String name, String permission, boolean isStaff) {
        this.color = color;
        this.name = name;
        this.permission = permission;
        this.isStaff = isStaff;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isStaff() {
        return isStaff;
    }
}
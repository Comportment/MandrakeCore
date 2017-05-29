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

package me.diax.comportment.mandrake.api;

import org.bukkit.ChatColor;

/**
 * Created by Comportment at 02:33 on 29/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public enum House {

    HUFFLEPUFF(ChatColor.YELLOW, "Hufflepuff", "F", "mandrake.house.hufflepuff"),
    RAVENCLAW(ChatColor.DARK_BLUE, "Ravenclaw", "R", "mandrake.house.ravenclaw"),
    SLYTHERIN(ChatColor.DARK_GREEN, "Slytherin", "S", "mandrake.house.slytherin"),
    GRYFFINDOR(ChatColor.DARK_RED, "Gryffindor", "G", "mandrake.house.gryffindor"),
    UNSORTED(ChatColor.LIGHT_PURPLE, "Unsorted", "U", "mandrake.house.unsorted");

    private final ChatColor color;
    private final String name;
    private final String sname;
    private final String permission;

    House(ChatColor color, String name, String sname, String permission) {
        this.color = color;
        this.name = name;
        this.sname = sname;
        this.permission = permission;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public String getPermission() {
        return permission;
    }
}
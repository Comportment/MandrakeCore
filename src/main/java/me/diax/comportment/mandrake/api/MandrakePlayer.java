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

import me.diax.comportment.mandrake.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Comportment at 02:32 on 29/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class MandrakePlayer {

    private final Player player;
    private House house;
    private int year;

    public MandrakePlayer(Player player) {
        this.player = player;
        this.house = House.UNSORTED;
        this.year = 1;
    }

    public MandrakePlayer(Player player, House house) {
        this.player = player;
        this.house = house;
        this.year = 1;
    }

    public MandrakePlayer(Player player, House house, int year) {
        this.player = player;
        this.house = house;
        this.year = year;
    }

    public String getTag() {
        return String.format("[%s/%s]", this.getHouse().getSname(), this.getYear());
    }

    public String getColouredTag() {
        ChatColor c = this.getHouse().getColor();
        return ChatUtil.colourise(String.format("%s[&7%s%s/&7%s%s]", c, this.getHouse().getSname(), c, this.getYear(), c));
    }

    public Player getBukkitPlayer() {
        return player;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return player.getName();
    }
}
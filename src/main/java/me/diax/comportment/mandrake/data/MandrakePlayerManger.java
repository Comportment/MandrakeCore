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

package me.diax.comportment.mandrake.data;

import me.diax.comportment.mandrake.Main;
import me.diax.comportment.mandrake.api.House;
import me.diax.comportment.mandrake.api.MandrakePlayer;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Comportment at 10:52 on 29/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class MandrakePlayerManger {

    @Inject
    private static Main plugin;

    private static Map<Player, MandrakePlayer> playerList = new HashMap<>();
    private static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().severe("JDBC not found!");
        }
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "players.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not load player db!");
        }
    }

    public static MandrakePlayer getPlayer(Player player) {
        if (playerList.containsKey(player)) {
            return playerList.get(player);
        }
        if (connection == null) connect();
        ResultSet rs;
        PreparedStatement ps;
        String id = player.getUniqueId().toString();
        String sql = "SELECT * FROM players WHERE uuid LIKE ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            MandrakePlayer mp = new MandrakePlayer(player, House.valueOf(rs.getString("house")), rs.getInt("year"), rs.getInt("points"));
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            playerList.put(player, mp);
            return mp;
        } catch (SQLException | NullPointerException e) {
            MandrakePlayer mp = createPlayer(player);
            playerList.put(player, mp);
            return mp;
        }
    }

    public static MandrakePlayer createPlayer(Player player) {
        if (connection == null) connect();
        MandrakePlayer mp = new MandrakePlayer(player);
        try {
            String sql = "INSERT INTO players (TEXT, TEXT, INT, INT) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, mp.getHouse().getName());
            ps.setInt(3, mp.getYear());
            ps.setLong(4, mp.getPoints());
            ps.execute();
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not save player");
        }
        return mp;
    }
}
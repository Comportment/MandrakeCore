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

package me.diax.comportment.mandrake;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import me.diax.comportment.mandrake.listeners.ChatListener;
import me.diax.comportment.mandrake.listeners.PlayerJoinPartListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Created by Comportment at 15:12 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
@Singleton
public final class Main extends JavaPlugin implements Module {

    private final Injector injector;

    public Main() {
        injector = Guice.createInjector(this);
    }

    public <T> T getInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(Main.class).toInstance(this);
    }

    @Override
    public void onEnable() {
        this.registerEvents(injector.getInstance(PlayerJoinPartListener.class), new ChatListener());
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
    }
}
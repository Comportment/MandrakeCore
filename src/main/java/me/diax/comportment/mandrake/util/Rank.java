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

/**
 * Created by Comportment at 23:10 on 28/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public enum Rank {

    PLAYER("mandrake.player", null),
    STAFF("mandrake.staff", PLAYER),
    DEVELOPER("mandrake.developer", STAFF),
    OWNER("mandrake.owner", DEVELOPER);

    private String permission;
    private Rank previous;

    Rank(String permission, Rank previous) {
        this.permission = permission;
        this.previous = previous;
    }

    public String getPermission() {
        return permission;
    }

    public Rank getPrevious() {
        return previous;
    }
}
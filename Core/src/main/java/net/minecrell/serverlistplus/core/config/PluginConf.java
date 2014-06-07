/*
 *        _____                     __    _     _   _____ _
 *       |   __|___ ___ _ _ ___ ___|  |  |_|___| |_|  _  | |_ _ ___
 *       |__   | -_|  _| | | -_|  _|  |__| |_ -|  _|   __| | | |_ -|
 *       |_____|___|_|  \_/|___|_| |_____|_|___|_| |__|  |_|___|___|
 *
 *  ServerListPlus - Customize your complete server status ping!
 *  Copyright (C) 2014, Minecrell <https://github.com/Minecrell>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.minecrell.serverlistplus.core.config;

import net.minecrell.serverlistplus.core.config.help.Description;

@Description({
        "General options about the plugin.",
        "Stats: Enable/disable sending plugin statistics.",
        "PlayerTracking: Enable/disable tracking of player names and their IP-Addresses.",
        "UnknownPlayerName: The player name that is used to replace '%player%' in the",
        "   default profile.",
        "UnknownPlayerCount: The player count used for replacing '%online%' and '%max%'",
        "   if we can't get the correct count for some reason."
})
public class PluginConf {
    public boolean Stats = true;
    public boolean PlayerTracking = true;
    public String UnknownPlayerName = "player";
    public String UnknownPlayerCount = "???";
}

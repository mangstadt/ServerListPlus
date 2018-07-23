/*
 * ServerListPlus - https://git.io/slp
 * Copyright (C) 2014 Minecrell (https://github.com/Minecrell)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.minecrell.serverlistplus.bukkit;

import net.minecrell.serverlistplus.core.plugin.ScheduledTask;
import net.minecrell.serverlistplus.core.util.Wrapper;
import org.bukkit.scheduler.BukkitTask;

public class ScheduledBukkitTask extends Wrapper<BukkitTask> implements ScheduledTask {
    public ScheduledBukkitTask(BukkitTask handle) {
        super(handle);
    }

    @Override
    public void cancel() {
        handle.cancel();
    }
}

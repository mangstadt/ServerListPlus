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

package net.minecrell.serverlistplus.core;

import net.minecrell.serverlistplus.core.config.io.IOUtil;
import net.minecrell.serverlistplus.core.util.CoreManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class ProfileManager extends CoreManager {
    private static final String DEFAULT_PROFILE = "ServerListPlus";

    private static final String PROFILE_FILENAME = "Profiles.json";
    private static final Gson JSON = new Gson();

    // TODO: Implement profiles
    private boolean enabled;

    public ProfileManager(ServerListPlusCore core) {
        super(core);
    }

    public Path getProfilePath() {
        return core.getConf().getPluginFolder().resolve(PROFILE_FILENAME);
    }

    public void reload() throws ServerListPlusException {
        Path profilePath = this.getProfilePath();
        this.getLogger().info("Reloading profiles from: " + profilePath);

        try {
            if (Files.exists(profilePath)) {
                // TODO: Serialize / deserialize objects instead
                try (BufferedReader reader = IOUtil.newBufferedReader(profilePath)) {
                    JsonObject obj = JSON.fromJson(reader, JsonObject.class);
                    obj = obj.getAsJsonObject(DEFAULT_PROFILE);
                    this.enabled = obj.get("Enabled").getAsBoolean();
                }

                if (enabled)
                    this.getLogger().info("ServerListPlus profile is enabled.");
                else
                    this.getLogger().info("ServerListPlus profile is disabled.");
            } else
                this.getLogger().info("Profile configuration not found, assuming the profile is disabled.");
        } catch (JsonSyntaxException e) {
            throw this.getLogger().process(e, "Unable to parse profile configuration, have you changed it?");
        } catch (IOException | JsonIOException e) {
            throw this.getLogger().process(e, "Unable to access profile configuration.");
        } catch (Exception e) {
            throw this.getLogger().process(e, "An internal error occurred while reloading the profiles!");
        }
    }

    public void save() throws ServerListPlusException {
        Path profilePath = this.getProfilePath();
        this.getLogger().info("Saving profiles to: " + profilePath);

        try {
            if (Files.notExists(profilePath)) {
                // Actually this should have been already created by the configuration manager...
                Files.createDirectories(profilePath.getParent());
            }

            // TODO: Serialize / deserialize objects instead
            try (BufferedWriter writer = IOUtil.newBufferedWriter(profilePath)) {
                JsonObject profile = new JsonObject();
                profile.addProperty("Enabled", this.enabled);
                JsonObject obj = new JsonObject();
                obj.add(DEFAULT_PROFILE, profile);
                JSON.toJson(obj, writer);
            }
        } catch (IOException | JsonIOException e) {
            throw this.getLogger().process(e, "Unable to access profile configuration.");
        } catch (Exception e) {
            throw this.getLogger().process(e, "An internal error occurred while saving the profiles!");
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean state) throws ServerListPlusException {
        if (this.enabled != state) {
            this.enabled = state;
            this.save();
            if (enabled) core.reload();
            else core.getPlugin().statusChanged(core.getStatus());
        }
    }
}
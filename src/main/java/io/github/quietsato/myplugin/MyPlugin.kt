package io.github.quietsato.myplugin

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class MyPlugin : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("Plugin activated")

        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info("Plugin activated")
    }

    @EventHandler
    fun onPlayerDied(e: EntityDeathEvent) {
        if (e.entityType != EntityType.PLAYER) return

        logger.info("Player '${e.entity.name}' died")
    }
}
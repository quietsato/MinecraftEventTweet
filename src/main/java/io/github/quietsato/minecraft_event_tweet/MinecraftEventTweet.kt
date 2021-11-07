package io.github.quietsato.minecraft_event_tweet

import io.github.quietsato.minecraft_event_tweet.twitter.TwitterManager
import kotlinx.coroutines.runBlocking
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class MinecraftEventTweet : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("Plugin activated")

        server.pluginManager.registerEvents(this, this)

        val twitterManager = TwitterManager()

        runBlocking {
            twitterManager.fetchWebApiTest()
        }
    }

    override fun onDisable() {
        logger.info("Plugin deactivated")
    }

    @EventHandler
    fun onPlayerDeath(e: EntityDeathEvent) {
        if (e.entityType != EntityType.PLAYER) return

        logger.info("Player '${e.entity.name}' died")
    }
}
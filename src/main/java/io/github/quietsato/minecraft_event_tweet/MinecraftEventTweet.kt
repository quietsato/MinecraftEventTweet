package io.github.quietsato.minecraft_event_tweet

import io.github.quietsato.minecraft_event_tweet.twitter.TwitterManager
import kotlinx.coroutines.runBlocking
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class MinecraftEventTweet : JavaPlugin(), Listener {
    private var twitterManager: TwitterManager? = null

    override fun onEnable() {
        logger.info("Plugin activated")

        server.pluginManager.registerEvents(this, this)

        saveDefaultConfig()

        val tm = initTwitterManager()
        if (tm == null){
            logger.warning("Failed to init TwitterManager. Maybe configuration is invalid.")
            return
        }
        twitterManager = tm

        // DEBUG
        tm.tweetString("Hello World")
    }

    override fun onDisable() {
        logger.info("Plugin deactivated")
    }

    @EventHandler
    fun onPlayerDeath(e: EntityDeathEvent) {
        if (e.entityType != EntityType.PLAYER) return

        logger.info("Player '${e.entity.name}' died")
    }

    private fun initTwitterManager(): TwitterManager? {
        val consumerKey: String = config.getString("consumerKey", null) ?: return null
        val consumerSecret: String = config.getString("consumerSecret", null) ?: return null
        val accessToken: String = config.getString("accessToken", null) ?: return null
        val accessTokenSecret: String = config.getString("accessTokenSecret", null) ?: return null

        return TwitterManager(
            consumerKey,
            consumerSecret,
            accessToken,
            accessTokenSecret
        )
    }
}
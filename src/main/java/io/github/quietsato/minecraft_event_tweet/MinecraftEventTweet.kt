package io.github.quietsato.minecraft_event_tweet

import io.github.quietsato.minecraft_event_tweet.twitter.TwitterManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.plugin.java.JavaPlugin


class MinecraftEventTweet : JavaPlugin(), Listener {
    private var twitterManager: TwitterManager? = null

    override fun onEnable() {
        logger.info("Plugin activated")

        server.pluginManager.registerEvents(this, this)

        saveDefaultConfig()

        val tm = initTwitterManager()
        if (tm == null) {
            logger.warning("Failed to init TwitterManager. Maybe configuration is invalid.")
            return
        }
        twitterManager = tm
    }

    override fun onDisable() {
        logger.info("Plugin deactivated")
    }

    override fun onCommand(
        sender: CommandSender?,
        command: Command?,
        label: String?,
        args: Array<out String>?
    ): Boolean {
        if (args == null) return false

        if (args[0].lowercase() == "tweet") {
            if (args.size < 2) return false

            val tweetContent = args.asSequence().drop(1).joinToString(" ")

            twitterManager?.also { tm ->
                tm.tweetString(tweetContent)
                logger.info("Tweeted: $tweetContent")
                return true
            }
            logger.warning("TwitterManager is not initialized")
            return false
        }
        return false
    }

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        twitterManager?.tweetString(e.deathMessage)
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
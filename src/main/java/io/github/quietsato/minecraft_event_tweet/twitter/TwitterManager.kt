package io.github.quietsato.minecraft_event_tweet.twitter

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TwitterManager(
    consumerKey: String,
    consumerSecret: String,
    accessToken: String,
    accessTokenSecret: String,
) {
    private val twitter: Twitter

    init {
        val builder = ConfigurationBuilder()
        builder.setOAuthConsumerKey(consumerKey)
            .setOAuthConsumerSecret(consumerSecret)
            .setOAuthAccessToken(accessToken)
            .setOAuthAccessTokenSecret(accessTokenSecret)

        twitter = TwitterFactory(builder.build()).instance
    }

    fun tweetString(s: String) {
        twitter.updateStatus(s)
    }
}

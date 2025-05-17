package by.emred.telegram.cloud.bot.config

import by.emred.telegram.cloud.bot.DevOpsBot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Configuration
class BotConfig {
    @Bean
    fun telegramBotsApi(bot: DevOpsBot): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(bot)
        }
}
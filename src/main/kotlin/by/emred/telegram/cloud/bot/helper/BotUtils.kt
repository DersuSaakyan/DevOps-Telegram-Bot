package by.emred.telegram.cloud.bot.helper

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

object BotUtils {

    fun createMessage(chatId: Long, text: String) =
        SendMessage(chatId.toString(), text)
            .apply { enableMarkdown(true) }
            .apply { disableWebPagePreview() }
}
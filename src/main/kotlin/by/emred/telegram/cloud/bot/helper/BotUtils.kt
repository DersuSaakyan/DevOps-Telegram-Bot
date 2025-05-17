package by.emred.telegram.cloud.bot.helper

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

object BotUtils {

    fun createMessage(chatId: Long, text: String) =
        SendMessage(chatId.toString(), text)
            .apply { enableMarkdown(true) }
            .apply { disableWebPagePreview() }

    fun createMessageWithSimpleButtons(chatId: Long, text: String, simpleButtons: List<List<String>>) =
        createMessage(chatId, text)
            .apply {
                replyMarkup = getSimpleKeyboard(simpleButtons)
            }

    private fun getSimpleKeyboard(allButtons: List<List<String>>): ReplyKeyboard =
        ReplyKeyboardMarkup().apply {
            keyboard = allButtons.map { rowButtons ->
                val row = KeyboardRow()
                rowButtons.forEach { rowButton -> row.add(rowButton) }
                row
            }
        }
}
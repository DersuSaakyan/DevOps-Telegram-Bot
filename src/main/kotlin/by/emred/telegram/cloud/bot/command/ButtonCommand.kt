package by.emred.telegram.cloud.bot.command

import by.emred.telegram.cloud.bot.enums.Command
import by.emred.telegram.cloud.bot.helper.BotUtils
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class ButtonCommand : BotCommand(Command.BUTTONS.value, Command.BUTTONS.description) {
    companion object {
        const val COMMAND_LIST_SIZE = 2
    }

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>?) {
        val listOfCommand = Command.entries.map { it.value }.chunked(COMMAND_LIST_SIZE)
        absSender.execute(
            BotUtils.createMessageWithSimpleButtons(
                chat.id,
                "Select the command :)",
                listOfCommand
            )
        )
    }
}
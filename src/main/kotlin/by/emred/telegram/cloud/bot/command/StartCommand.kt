package by.emred.telegram.cloud.bot.command

import by.emred.telegram.cloud.bot.enums.BotState
import by.emred.telegram.cloud.bot.enums.Command
import by.emred.telegram.cloud.bot.helper.BotUtils
import by.emred.telegram.cloud.bot.holder.UserState
import by.emred.telegram.cloud.bot.holder.UserStateHolder
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class StartCommand : BotCommand(Command.START.value, Command.START.description) {
    override fun execute(p0: AbsSender, p1: User, p2: Chat, p3: Array<out String>?) {
        p0.execute(BotUtils.createMessage(p2.id, "Welcome! Select /buttons command to se all available commands"))
        UserStateHolder.set(
            UserState(userId = p2.id, BotState.AWAITING_NAMESPACE_FOR_PODS)
        )
    }
}
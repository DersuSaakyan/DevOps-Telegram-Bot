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
class NamespaceSetCommand : BotCommand(Command.NAMESPACE_SET.value, Command.NAMESPACE_SET.description) {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>?) {
        UserStateHolder.set(UserState(userId = user.id, BotState.AWAITING_NAMESPACE_FOR_PODS))
        absSender.execute(BotUtils.createMessage(user.id, "Enter name of namespace"))
    }
}
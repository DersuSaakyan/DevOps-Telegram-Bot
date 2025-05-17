package by.emred.telegram.cloud.bot.command

import by.emred.telegram.cloud.bot.enums.BotState
import by.emred.telegram.cloud.bot.enums.Command
import by.emred.telegram.cloud.bot.helper.BotUtils
import by.emred.telegram.cloud.bot.holder.UserState
import by.emred.telegram.cloud.bot.holder.UserStateHolder
import by.emred.telegram.cloud.k8s.holder.UserContextHolder
import by.emred.telegram.cloud.k8s.service.PodsService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

@Component
class PodsCommand(
    private val podsService: PodsService,
) : BotCommand(Command.PODS.value, Command.PODS.description) {
    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>?) {
        UserContextHolder.get()?.let {
            val pods = podsService.getPods(it.namespace)
            absSender.execute(BotUtils.createMessage(user.id, pods))
            return
        }

        absSender.execute(BotUtils.createMessage(chat.id, "Enter a name of namespace"))
        UserStateHolder.set(
            UserState(
                userId = chat.id,
                state = BotState.AWAITING_NAMESPACE_FOR_PODS
            )
        )
    }
}
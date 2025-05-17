package by.emred.telegram.cloud.bot

import by.emred.telegram.cloud.bot.enums.BotState
import by.emred.telegram.cloud.bot.helper.BotUtils
import by.emred.telegram.cloud.bot.holder.UserStateHolder
import by.emred.telegram.cloud.k8s.holder.UserContext
import by.emred.telegram.cloud.k8s.holder.UserContextHolder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class DevOpsBot(
    @Value("\${telegram.token}") telegramToken: String,
    commands: MutableSet<out BotCommand>,
) : TelegramLongPollingCommandBot(telegramToken) {

    @Value("\${telegram.username}")
    val telegramUsername: String = ""

    init {
        registerAll(*commands.toTypedArray())
    }

    override fun getBotUsername(): String = telegramUsername

    override fun processNonCommandUpdate(update: Update?) {
        update?.let { upd ->
            val message = upd.message
            val userState = UserStateHolder.get()
            userState?.let {
                if (it.state == BotState.AWAITING_NAMESPACE_FOR_PODS) {
                    UserContextHolder.set(UserContext(userId = userState.userId, namespace = message.text))
                    UserStateHolder.clear()
                    execute(BotUtils.createMessage(userState.userId, "Namespace set to: ${message.text}"))
                }
            }
        }
    }
}
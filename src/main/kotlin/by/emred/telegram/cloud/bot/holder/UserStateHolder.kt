package by.emred.telegram.cloud.bot.holder

import by.emred.telegram.cloud.bot.enums.BotState

object UserStateHolder {
    private val userState = InheritableThreadLocal<UserState>()

    fun set(contextData: UserState) {
        userState.remove()
        userState.set(contextData)
    }

    fun get(): UserState? = userState.get()

    fun clear() {
        userState.remove()
    }
}

data class UserState(val userId: Long, val state: BotState)
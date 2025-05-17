package by.emred.telegram.cloud.k8s.holder

object UserContextHolder {
    private val context = InheritableThreadLocal<UserContext>()

    fun set(contextData: UserContext) {
        context.set(contextData)
    }

    fun get(): UserContext? = context.get()

    fun remove() = context.remove()
}

data class UserContext(
    val userId: Long,
    val namespace: String,
)
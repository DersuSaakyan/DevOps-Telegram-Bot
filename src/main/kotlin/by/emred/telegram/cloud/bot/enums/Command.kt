package by.emred.telegram.cloud.bot.enums

enum class Command(val value: String, val description: String) {
    START("start", ""),
    PODS("pods", "Get all pods"),
    NAMESPACE_CHANGE("namespace_change", "Change namespace")
}
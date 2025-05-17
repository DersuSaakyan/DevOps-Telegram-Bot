package by.emred.telegram.cloud.bot.enums

enum class Command(val value: String, val description: String) {
    START("/start", ""),
    PODS("/pods", "Get all pods"),
    NAMESPACE_SET("/namespace_set", "Set namespace"),
    BUTTONS("/buttons", "Show all available buttons"),
}
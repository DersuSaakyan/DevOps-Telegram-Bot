package by.emred.telegram.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudApplication

fun main(args: Array<String>) {
    runApplication<CloudApplication>(*args)
}

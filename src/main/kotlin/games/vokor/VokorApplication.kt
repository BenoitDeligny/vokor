package games.vokor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VokorApplication

fun main(args: Array<String>) {
    runApplication<VokorApplication>(*args)
}

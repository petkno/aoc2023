import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val detektVersion = "1.23.4" // https://mvnrepository.com/artifact/io.gitlab.arturbosch.detekt/detekt-gradle-plugin
val kotestVersion = "5.8.0" // https://mvnrepository.com/artifact/io.kotest/kotest-runner-junit5
val junitVersion = "5.10.1" // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter

plugins {
    val kotlinVersion = "1.9.21"
    val detektVersion = "1.23.4" // https://mvnrepository.com/artifact/io.gitlab.arturbosch.detekt/detekt-gradle-plugin

    kotlin("jvm") version kotlinVersion

    id("io.gitlab.arturbosch.detekt") version detektVersion
}

group = "de.iits.petkno"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json:$kotestVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

detekt {
    buildUponDefaultConfig = true
    config.from("config/detekt.yaml")
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
    }
}


tasks.test {
    useJUnitPlatform()
}

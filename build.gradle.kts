import org.cadixdev.gradle.licenser.LicenseExtension
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.cadixdev.licenser") version "0.6.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    kotlin("jvm") version "1.5.31"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = sourceCompatibility
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
    maven {
        name = "Paper"
        url = uri("https://papermc.io/repo/repository/maven-public/")
        content {
            includeGroup("com.destroystokyo.paper")
        }
    }
    maven {
        name = "EngineHub"
        url = uri("https://maven.enginehub.org/repo/")
    }
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    // Testing renovate from here
    compileOnly(libs.piston)
}

version = "1.0.1"

configure<LicenseExtension> {
    header = rootProject.file("LICENSE-HEADER")
    newLine = false
}

bukkit {
    name = "RenovateDependencyTest"
    main = "de.notmyfault.renovatedependencytest.RenovateDependencyTestPlugin"
    author = "NotMyFault"
    apiVersion = "1.13"
    version = rootProject.version.toString()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}

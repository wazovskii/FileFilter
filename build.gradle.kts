plugins {
    id("java-library")
    application
}

application {
    mainClass = "App"
}

group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-cli:commons-cli:1.4")
}

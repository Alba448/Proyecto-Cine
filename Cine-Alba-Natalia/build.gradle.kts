plugins {
    kotlin("jvm") version "1.9.23"
    //Serializacion
    kotlin("plugin.serialization") version "1.9.23"
    // Koin KSP
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Result
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    //Logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //Cargar scripts
    implementation("org.mybatis:mybatis:3.5.13")
    //Driver sqlite
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    //Mock
    testImplementation("io.mockk:mockk:1.13.10")
    //Koin
    implementation(platform("io.insert-koin:koin-bom:3.5.3"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-test")
    //Koin Junit
    testImplementation("io.insert-koin:koin-test-junit5")
    // Koin Annotations
    implementation(platform("io.insert-koin:koin-annotations-bom:1.3.1"))
    implementation("io.insert-koin:koin-annotations")
    ksp("io.insert-koin:koin-ksp-compiler:1.3.1")
    //Serializable
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
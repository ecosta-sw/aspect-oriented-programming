import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion: String by project
val testContainerVersion: String by project

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    kotlin("plugin.jpa") version "1.6.0"
}

group = "com.creditas"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val snippetsDir = file("build/generated-snippets")

dependencies {

    // DATABASE DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql")

    //JACKSON DEPENDENCIES
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // KOTLIN DEPENDENCIES
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // VALIDATION DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // web DEPENDENCIES
    implementation("org.springframework.boot:spring-boot-starter-web")

    // TEST DEPENDENCIES
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//        exclude(group = "org.junit.jupiter", module = "junit-jupiter-api")
//        exclude(group = "org.junit.jupiter", module = "junit-jupiter-engine")
    }
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
//    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:$testContainerVersion")
    }
}

tasks {

    asciidoctor {
        inputs.dir(snippetsDir)
        dependsOn(test)
    }

    getByName<Jar>("jar") {
        enabled = false
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    test {
        outputs.dir(snippetsDir)
    }
}
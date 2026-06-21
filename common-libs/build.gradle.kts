plugins {
    id("java-library")
}

version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.7"))
    annotationProcessor(platform("org.springframework.boot:spring-boot-dependencies:3.5.7"))
    testImplementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.7"))

    api("jakarta.validation:jakarta.validation-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}
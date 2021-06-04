plugins {
    val kotlinVersion = "1.4.32"
    id("org.springframework.boot") version "2.3.10.RELEASE"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    // @Entity 클래스들을 자동으로 인자없는 생성자를 추가해준다.
    kotlin("plugin.noarg") version kotlinVersion
    // @Entity 클래스들은 모두 open으로 변경
    // Hibernate는 지연로딩을 위해 Entity들을 상속하여 프록시를 만듬. kotlin에서는 기본이 final이기 때문에 지연로딩으로 설정해도 프록시를 만들지 못해 지연로딩이 되지 않아서 open을 붙여야함.
    kotlin("plugin.allopen") version kotlinVersion
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktlint by configurations.creating

dependencies {
    // NoClassDefFoundError: kotlin/reflect/full/KClasses
    // 프로그램에서 리플렉션은 런타임 때 프로그램의 구조(객체, 함수, 프로퍼티)를 분석해 내는 기법을 이야기합니다. 예를 들어 어떤 함수를 정의하는데 함수의 매개변수로 클래스 타입을 선언하고 런타임 때 매개변수로 전달된 클래스의 이름, 클래스의 함수나 프로퍼티를 동적으로 판단하는 작업을 리플렉션이라고 합니다.
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
    ktlint("com.pinterest:ktlint:0.41.0")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xuse-experimental=kotlin.Experimental")
            jvmTarget = "1.8"
        }
    }
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}


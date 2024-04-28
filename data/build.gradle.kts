plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "br.com.luiz.data"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(project(":commons"))
    implementation(project(":domain"))

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.kotlinx.serialization.converter)

    implementation(libs.okhttp)
    implementation(libs.retrofit)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.org.junit.jupiter.api)
    testImplementation(libs.org.mockito.core)
    testImplementation(libs.io.kotlintest)


}

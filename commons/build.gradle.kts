plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)
    alias(libs.plugins.kotlinxSerialization)
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
}

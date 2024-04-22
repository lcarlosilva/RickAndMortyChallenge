plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)
    alias(libs.plugins.kotlinxSerialization)
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}

plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)
}

dependencies {

    implementation(project(":commons"))

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}

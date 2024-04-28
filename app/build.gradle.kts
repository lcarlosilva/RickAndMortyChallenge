plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
	namespace = "br.com.luiz.rickandmortychallenge"
	compileSdk = 34

	defaultConfig {
		applicationId = "br.com.luiz.rickandmortychallenge"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	val url = "\"https://rickandmortyapi.com/api/\""
	val fullApplicationId = android.defaultConfig.applicationId
	val appName = fullApplicationId?.substringAfterLast(".")

	buildTypes {
		debug {
			isMinifyEnabled = false
			isDebuggable = true
			applicationIdSuffix = ".debug"
			buildConfigField(
				"String",
				"API_RICK_MORTY",
				url,
			)
			buildConfigField(
				"String",
				"NAME_APP_FORMATTED",
				"\"${appName}_${android.defaultConfig.versionName}-DBG\"",
			)
		}

		release {
			isMinifyEnabled = true
			isDebuggable = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)
			buildConfigField(
				"String",
				"API_RICK_MORTY",
				url,
			)
			buildConfigField(
				"String",
				"NAME_APP_FORMATTED",
				"\"${android.defaultConfig.applicationId}_${android.defaultConfig.versionName}\"",
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_17.toString()
	}
	buildFeatures {
		buildConfig = true
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.12"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(project(":data"))
	implementation(project(":domain"))
	implementation(project(":commons"))

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.navigation.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)

	implementation(platform(libs.koin.bom))
	implementation(libs.koin.core)
	implementation(libs.koin.android)
	implementation(libs.koin.androidx.compose)

	implementation(libs.paging)
	implementation(libs.paging.compose)
	implementation(libs.androidx.paging.common.ktx)

	implementation(libs.io.coil.kt.compose)
	implementation(libs.landscapist.coil)

	implementation(libs.kotlinx.serialization.json)
	implementation(libs.androidx.palette.ktx)
	implementation(libs.androidx.compose.constraint.layout)

	implementation(libs.accompanist.placeholder.material)
	implementation(libs.androidx.junit.ktx)
	implementation(libs.androidx.navigation.testing)

	testImplementation(libs.junit)
	testImplementation(libs.mockk)
	testImplementation(libs.androidx.core.testing)
	testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)

	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	androidTestImplementation(libs.androidx.core.testing)
	androidTestImplementation(libs.ui.test.junit4)
	androidTestImplementation(libs.org.jetbrains.kotlinx.coroutines.test)

	debugImplementation(libs.ui.test.manifest)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}

object Dependencies {
    val androidCore by lazy {"androidx.core:core-ktx:${Versions.kotlin}"}
    val lifecycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"}
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil}" } // image loading
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" } // logging
    val navigation by lazy { "androidx.navigation:navigation-compose:${Versions.navigation}" } // navigation
    // Compose
    val composeActivity by lazy {"androidx.activity:activity-compose:${Versions.composeActivity}"}
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val composeRuntimeLivedata by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.compose}" }
    val composeRuntimeLivecycle by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${Versions.compose}" }
    val composeMaterialIcons by lazy { "androidx.compose.material:material-icons-extended:${Versions.compose}" }
    val composePaging by lazy { "androidx.paging:paging-compose:${Versions.composePaging}" }
    // Kotlin
    val kotlinCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val kotlinSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}" }
    val kotlinSerializationConverter by lazy { "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverter}" }
    // retrofit
    val retrofit by lazy {"com.squareup.retrofit2:retrofit:${Versions.retrofit}"}
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okHttp by lazy{ "com.squareup.okhttp3:logging-interceptor:4.10.0" }
    // room
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomPaging by lazy { "androidx.room:room-paging:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    // Firebase
    val firebaseBOM by lazy { "com.google.firebase:firebase-bom:${Versions.firebase}" }
    val crashlitics by lazy { "com.google.firebase:firebase-crashlytics-ktx" }
    val analytics by lazy { "com.google.firebase:firebase-analytics-ktx" }
    // dagger-hilt
    val daggerHilt by lazy {"com.google.dagger:hilt-android:${Versions.daggerHilt}"}
    val daggerCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.daggerHilt}" }
    val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}" }
    val hiltNavigation by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}" }
    // testing
    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitExt by lazy { "androidx.test.ext:junit:${Versions.jUnitExt}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val composeUiTest by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
    val composeUiTestTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.compose}" }
    val roomTesting by lazy { "androidx.room:room-testing:${Versions.room}" }
}
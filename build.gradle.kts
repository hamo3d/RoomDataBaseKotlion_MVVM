// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
    }
    dependencies {
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.10-1.0.13")
        // Other dependencies...
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:about-app"))
    implementation(project(":feature:account"))
    implementation(project(":feature:award-list"))
    implementation(project(":feature:collection-list"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:movie-list"))
    implementation(project(":feature:movie-screen"))
    implementation(project(":feature:person-podium"))
    implementation(project(":feature:person-screen"))
    implementation(project(":feature:search"))
    api(project(":feature:settings"))
    implementation(project(":core:navigation-route"))
    implementation(project(":feature:otp-screen"))
}
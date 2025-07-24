plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:aboutApp"))
    implementation(project(":feature:account"))
    implementation(project(":feature:awardList"))
    implementation(project(":feature:collectionList"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:movieList"))
    implementation(project(":feature:movieScreen"))
    implementation(project(":feature:personPodium"))
    implementation(project(":feature:personScreen"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))
    implementation(project(":core:navigationRoute"))
}
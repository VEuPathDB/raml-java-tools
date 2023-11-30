plugins {
  `java-library`
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11

  withSourcesJar()
  withJavadocJar()
}

dependencies {
  implementation(platform(project(":platform")))

  implementation("org.raml:raml-parser-2")
}
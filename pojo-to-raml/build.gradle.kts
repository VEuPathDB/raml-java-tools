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

  implementation(project(":raml-builder"))
  implementation(project(":raml-to-pojo"))
  implementation(project(":raml-simple-emitter"))

  implementation("org.raml:raml-parser-2")
  implementation("org.hamcrest:hamcrest-core")
  implementation("javax.annotation:javax.annotation-api")
}
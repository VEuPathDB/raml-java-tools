dependencies {
  implementation(platform(project(":platform")))

  implementation(project(":raml-parser-tools"))
  implementation(project(":raml-builder"))

  implementation("org.raml:raml-parser-2")
}
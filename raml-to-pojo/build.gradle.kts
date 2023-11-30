dependencies {
  implementation(platform(project(":platform")))

  implementation("org.raml:raml-parser-2")
  implementation("com.google.guava:guava")
  implementation("org.hamcrest:hamcrest-all")
  implementation("javax.validation:validation-api")
  implementation("com.squareup:javapoet")
  implementation("com.google.code.gson:gson")
  implementation("org.codehaus.jackson:jackson-mapper-asl")
  implementation("org.codehaus.jackson:jackson-core-asl")
  implementation("javax.annotation:javax.annotation-api")
  implementation("javax.xml.bind:jaxb-api")
}
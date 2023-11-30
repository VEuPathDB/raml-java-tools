plugins {
  `java-platform`
}

dependencies {
  constraints {
    api("org.raml:raml-parser-2:1.0.51")
    api("org.hamcrest:hamcrest-core:2.1")
    api("org.hamcrest:hamcrest-all:1.3")
    api("com.google.guava:guava:18.0")
    api("com.squareup:javapoet:1.11.1")
    api("com.google.code.gson:gson:2.5")
    api("org.codehaus.jackson:jackson-mapper-asl:1.9.1")
    api("org.codehaus.jackson:jackson-core-asl:1.9.1")
    api("com.fasterxml.jackson.core:jackson-annotations:2.9.0")
    api("com.fasterxml.jackson.core:jackson-core:2.9.7")
    api("com.fasterxml.jackson.core:jackson-databind:2.9.10.4")
    api("javax.annotation:javax.annotation-api:1.3.2")
    api("javax.validation:validation-api:1.1.0.Final")
    api("javax.xml.bind:jaxb-api:2.3.0")
  }
}
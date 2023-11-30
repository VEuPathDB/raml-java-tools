plugins {
  `java-library`
  `maven-publish`
}

group = "org.raml"
version = "1.0.9"

subprojects {

  if (this.name == "platform")
    return@subprojects

  apply(plugin = "java-library")
  apply(plugin = "maven-publish")

  repositories {
    mavenLocal()
    mavenCentral()

    maven {
      name = "GitHubPackages"
      url  = uri("https://maven.pkg.github.com/veupathdb/packages")
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
        password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
      }
    }
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withSourcesJar()
    withJavadocJar()
  }

  publishing {
    repositories {
      maven {
        name = "GitHub"
        url = uri("https://maven.pkg.github.com/VEuPathDB/raml-java-tools")
        credentials {
          username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
          password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
      }
    }

    publications {
      create<MavenPublication>("gpr") {
        from(components["java"])
        afterEvaluate {
          artifactId = tasks.jar.get().archiveBaseName.get()
        }
        pom {
          url.set("https://github.com/VEuPathDB/raml-java-tools")
          group = rootProject.group
          version = rootProject.version as String
          organization {
            name.set("Mulesoft")
            url.set("http://mulesoft.com")
          }
          licenses {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
          developers {
            developer {
              id.set("jpbelang")
              name.set("Jean-Philippe Belanger")
              email.set("jpbelang@hotmail.com")
              organization.set("Mulesoft")
              organizationUrl.set("http://mulesoft.com")

            }
            developer {
              id.set("epharper")
              name.set("Elizabeth Paige Harper")
              email.set("epharper@upenn.edu")
              url.set("https://github.com/foxcapades")
              organization.set("VEuPathDB")
            }
          }
          scm {
            connection.set("scm:git:git://github.com/VEuPathDB/raml-java-tools.git")
            developerConnection.set("scm:git:ssh://github.com/VEuPathDB/raml-java-tools.git")
            url.set("https://github.com/VEuPathDB/raml-java-tools")
          }
        }
      }
    }
  }
}

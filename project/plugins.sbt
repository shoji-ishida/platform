logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/maven-releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.8")

// Ebean
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "1.0.0")

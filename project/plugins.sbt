logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")

// Ebean
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "1.0.0")

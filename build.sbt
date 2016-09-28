name := "platform"

version := "1.0"

lazy val `platform` = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs ,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc4" ,
  "uk.co.panaxiom" %% "play-jongo" % "2.0.0-jongo1.3")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  
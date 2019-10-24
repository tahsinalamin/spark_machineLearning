# spark_machineLearning

Several Machine learning models in Spark-MLLib with Scala.

1. Linear Regression
2. PCA
3. Naive Bayes
4. K-means

Create a build.sbt, compile and run the program. One example to create build.sbt is given below:


*******build.sbt********
name := "Kmeans"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.2.0"
**************************

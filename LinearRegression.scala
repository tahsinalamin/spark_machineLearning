/*
Author:     Sikder Tahsin Al Amin
Description: Compute Linear Regression on a data set
*/

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.ml.regression.LinearRegression

object LinearRegression {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val session = SparkSession.builder().appName("LinearRegression").master("local").getOrCreate()
    val t1 = System.nanoTime
    
    val training = session.read.format("libsvm").load("/path_to_file/dataset.txt")
    val lr = new LinearRegression()
    val lrModel = lr.fit(training)

    val duration = (System.nanoTime - t1) / 1e9d
    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")
    println("The program finishes in .....")
    println(duration)
    }
}

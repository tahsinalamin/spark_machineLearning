/*
Author     : Sikder Tahsin Al Amin
Description: Compute K-means clustering algorithm on a data set
*/


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.ml.clustering.KMeans

object KmeansClustering {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val session = SparkSession.builder().appName("Kmeans").master("local").getOrCreate()
  
     val t1 = System.nanoTime
    // Loads data.
    val dataset = session.read.format("libsvm").load("/path_to_file/dataset.txt")

    // Trains a k-means model.
    val kmeans = new KMeans().setK(2).setSeed(1L).setMaxIter(10000)
    val model = kmeans.fit(dataset)

    // Make predictions
    val predictions = model.transform(dataset)
    
    val duration = (System.nanoTime - t1) / 1e9d

    // Evaluate clustering by computing Within Set Sum of Squared Errors.
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    println("The program finishes in .....")
    println(duration)
  }
}

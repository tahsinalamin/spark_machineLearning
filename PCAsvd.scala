/*
Author     : Sikder Tahsin Al Amin
Description: Compute the SVD of a data set in CVS format
*/

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.mllib.linalg.Matrix
import org.apache.spark.mllib.linalg.SingularValueDecomposition
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix

object PCAsvd {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("PCAsvd")
    val sc = new SparkContext(conf)
    val t1 = System.nanoTime
    
    val rawData = sc.textFile("/path_to_file/dataset.csv") // read the dataset
    val parsedData = rawData.map(s => Vectors.dense(s.split(',').map(_.toDouble))) //convert the data to RDD[Vector]
    val mat: RowMatrix = new RowMatrix(parsedData) //convert RDD[vector] to RowMatrix

    val svd: SingularValueDecomposition[RowMatrix, Matrix] = mat.computeSVD(9, computeU = true) //svd of 90 columns

    val duration = (System.nanoTime - t1) / 1e9d

    println("The program finishes in .....")
    println(duration)
  }
}

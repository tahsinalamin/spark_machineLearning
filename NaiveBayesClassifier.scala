/*
Author     : Sikder Tahsin Al Amin
Description: Compute Naive Bayes classifier on a data set
*/

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.util.MLUtils
// $example off$

object NaiveBayesClassifier {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("NaiveBayes")
    val sc = new SparkContext(conf)
  
     val t1 = System.nanoTime
    // Load and parse the data file.
    val data = MLUtils.loadLibSVMFile(sc, "/path_to_file/dataset.txt")

    // Split data into training (60%) and test (40%). If splitting is needed
    /*val Array(training, test) = data.randomSplit(Array(0.6, 0.4)) */

    val model = NaiveBayes.train(data, lambda = 1.0, modelType = "multinomial")

    //For Prediction
    /*val predictionAndLabel = test.map(p => (model.predict(p.features), p.label))
    val accuracy = 1.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()
    */
    
    
    // Save and load model
    /* model.save(sc, "target/tmp/myNaiveBayesModel")
    val sameModel = NaiveBayesModel.load(sc, "target/tmp/myNaiveBayesModel")
    */
    
    val duration = (System.nanoTime - t1) / 1e9d
    println("The program finishes in .....")
    println(duration)

    sc.stop()
  }
}

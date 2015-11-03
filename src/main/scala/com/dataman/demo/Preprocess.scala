package com.dataman.demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by mymac on 15/11/3.
 */
object Preprocess {
  def main(args: Array[String]) = {
    val inputPath = args(0).toString
    val outputPath = args(1).toString
    val stopwordPath = args(2).toString

    val conf = new SparkConf().setAppName("preprocess")
    val sc = new SparkContext(conf)

    Logger.getRootLogger.setLevel(Level.WARN)

    val data = sc.textFile(inputPath)
    val cleanData = data.map(line => {
      line.replaceAll( """[^\w]""", " ").replaceAll("""[1-9,0]""", "").trim.replaceAll("_", "").replaceAll(" +", " ").replaceAll("_", "")
    })
    val stopword = sc.textFile(stopwordPath).collect
    val finalData = cleanData.map(line => {
      line.split(" ").filterNot{w => stopword.contains(w)}.mkString(" ")
    }).map(line => Stemmer.stemming(line))
    finalData.repartition(10).saveAsTextFile(outputPath)
    sc.stop()
  }
}

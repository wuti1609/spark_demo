package com.dataman.demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by mymac on 15/11/3.
 */
object Assignment1 {

  def main(args: Array[String]) = {

    val conf = new SparkConf().setAppName("assignment1")
    val sc = new SparkContext(conf)

    Logger.getRootLogger.setLevel(Level.WARN)

    //please code here.

    sc.stop()
  }
}

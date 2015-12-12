package com.dataman.demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by mymac on 15/11/3.
 */
object Assignment2 {

  val source = "hdfs://192.168.111.134:9000/Assignment2/jingdong.json"
  val result = "/tmp/project/data/answer"

  def main(args: Array[String]) = {

    val conf = new SparkConf().setAppName("assignment2")
    val sc = new SparkContext(conf)
      
    //please code here.

    sc.stop()
  }
}

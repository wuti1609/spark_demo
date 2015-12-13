package com.dataman.demo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.{StructType,StructField,StringType}

/**
 * Created by mymac on 15/11/3.
 */
object Assignment2 {

  val source = "hdfs://192.168.111.134:9000/Assignment2/jingdong.json"

  def main(args: Array[String]) = {

    val conf = new SparkConf().setAppName("assignment2")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
      
    //please code here.

    sc.stop()
  }
}

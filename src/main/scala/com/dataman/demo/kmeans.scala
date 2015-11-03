package com.dataman.demo

import java.util.Random
import org.apache.spark.SparkContext
import SparkContext._
import org.apache.spark.util.Vector

object Kmeans {
	val N = 1000
	val R = 1000     //随机数范围  0-1  *  R
	val D = 10       //点空间纬度
	val K = 10       //聚类中心个数
	val rand = new Random(42) //随机种子
	val convergeDist = 0.01   //迭代收敛条件

	def closestPoint(p:Vector,centers: Array[Vector]): Int = {
		var bestIndex = 0
		var closest = Double.PositiveInfinity

		for (i <- 0 until centers.length) {
			val tempDist = p.squaredDist(centers(i))
			if(tempDist < closest) {
				closest = tempDist
				bestIndex = i
			}
		}
		bestIndex
	}

	def generateData = {
		def generatePoint(i: Int) = {
			Vector(D,_ => rand.nextDouble * R)
		}
		Array.tabulate(N)(generatePoint)
	}

	def main(args: Array[String]) {
		val sc = new SparkContext()
		val data = sc.parallelize(generateData).cache()

		val kPoints = data.takeSample(false,K,42).toArray
		var tempDist = 1.0

		while(tempDist > convergeDist) {
			val closest = data.map(p => (closestPoint(p,kPoints),(p,1)))
			val pointStats = closest.reduceByKey{
				case ((x1,y1),(x2,y2)) => (x1+x2,y1+y2)
			}
			val newPoints = pointStats.map{
				pair => (pair._1, pair._2._1 / pair._2._2)
			}.collectAsMap()

			tempDist = 0.0
			for (i <- 0 until K) {
				tempDist += kPoints(i).squaredDist(newPoints(i))
			}
			for (newP <- newPoints) {
				kPoints(newP._1) = newP._2
			}
			println("Finished iteration(delta = "+ tempDist + ")")
		}
		println("Final centers:")
		kPoints.foreach(println)
		System.exit(0)
	}
}
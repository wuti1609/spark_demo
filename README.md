# spark_demo
## 准备  
1. jdk 1.7 or later  
2. scala 2.10.4 or later  
3. sbt 0.13.8 or later  
4. spark 1.5.0 or later  

## 编译和运行  
提供两种编译方式：  
  1. sbt  
编译  
`sbt clean assembly`  
运行  
`bin/spark-submit --class com.dataman.demo.Assignment1 ${PROJECT_HOME}/target/scala-2.10/spark-demo-assembly-1.0.jar`  

  2. maven  
编译  
`mvn clean package`  
运行  
`bin/spark-submit --class com.dataman.demo.Assignment1 ${PROJECT_HOME}/target/spark-demo-1.0.jar`  

## 作业1—倒排表
1. 输入  
数据源：  
	hdfs://192.168.111.134:9000/Assignment1  
	(和/data/Assignment1的数据一致)
数据结构：  
	（doc_id,doc_contents）  
2. 要求  
建立倒排表  
	在 Assignment1.scala 中添加相应代码。  
	倒排表的JSON格式定义为：  
{w1: [ { w1_d1: [ w1_d1_p1, d1_p2 ] },{ w1_d2: [ w1_d2_p1 ] },{ w1_d3: [ w1_d3_p1 ] } ]}  
{w2: [ { w2_d1: [ w1_d1_p1, d1_p2, d1_p3] },{ w2_d2: [ w1_d2_p1] } ]}  
	如上倒排表所示，单词w1出现在三篇文档中，文档的ID分别：w1_d1、w1_d2、w1_d3。该单词w1在文档w1_d1中出现了两次，出现的位置分别为w1_d1_p1和d1_p2，在文档w1_d2和w1_d3中哥出现一次，其出现位置分别为w1_d2_p1和w1_d3_p1。  
3. 输出  
	所有DF=10的单词及其对应的倒排表键值，输出格式要求每一行为一个合法的JSON格式字符串。  
    
## 作业2-DataFrame 操作

1. 输入  
数据源：  
	```hdfs://192.168.111.134:9000/Assigment2/jingdong.json```  
数据结构：  

```  
	 |-- AllComments: string (nullable = true)
	 |-- HighPraise: string (nullable = true)
	 |-- _id: struct (nullable = true)
	 |    |-- $oid: string (nullable = true)
	 |-- brand: string (nullable = true)
	 |-- itemId: string (nullable = true)
	 |-- model: string (nullable = true)
	 |-- price: string (nullable = true)
	 |-- timestamp: struct (nullable = true)
	 |    |-- $date: string (nullable = true)
	 |-- type1: string (nullable = true)
	 |-- type2: string (nullable = true)
	 |-- type3: string (nullable = true)
	 |-- url: string (nullable = true)
```  

2. 要求  
	- 使用 SQLContext 读取 jingdong 数据
	- 使用 DataFrame 操作
	- 找出商品中最贵的1000个“手机”
	- 找出商品中评论最多的1000个“手机”
	- 找出以上两个数据集中都出现过的“手机”，打印出名称、价格、评论数、好评度以及商品编号
	- 输出格式，每条记录为单行有效 JSON
	

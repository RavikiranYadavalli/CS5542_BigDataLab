import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.util.control._;

/**
  *
  */
object MainApp {

  val sparkConf = new SparkConf().setAppName("TwitterSparkStreaming").setMaster("local[*]");

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","F:\\winutils");
    //val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]");

   // val sc= new SparkContext(sparkConf)

    val filters = args
    val loop = new Breaks;

    // Set the system properties so that Twitter4j library used by twitter stream
    // can use them to generate OAuth credentials

    System.setProperty("twitter4j.oauth.consumerKey", "4PF36QamiKzLFsTUl8obusYCN")
    System.setProperty("twitter4j.oauth.consumerSecret", "A9BnOPGA158n1GVdB0jrYLLF2hRbTnPoAsr9RnXRPjlGrHDs14")
    System.setProperty("twitter4j.oauth.accessToken", "107943927-ZqQ7lKSyPVBkh1SdPMy1dofmqwJdu2x1AWffF4Ex")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "j8moFl7chQnHeA1EAnPuVuY26mZKqXeomXYYZNLsDLwz2")



    //Create a spark configuration with a custom name and master
    // For more master configuration see  https://spark.apache.osrg/docs/1.2.0/submitting-applications.html#master-urls
    val sparkConf = new SparkConf().setAppName("STweetsApp").setMaster("local[*]")
    //Create a Streaming COntext with 2 second window
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    //Using the streaming context, open a twitte
    // r stream (By the way you can also use filters)
    //Stream generates a series of random tweets
    val stream = TwitterUtils.createStream(ssc, None, filters)
    stream.print()
    //Map : Retrieving Hash Tags
    val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))

    //Finding the top hash Tags on 30 second window
    val topCounts30 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(30))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))
    //Finding the top hash Tgas on 10 second window


    // Print popular hashtags


    topCounts30.foreachRDD(rdd => {
      val topList = rdd.take(10)
      println("\nPopular topics in last 10 seconds (%s total):".format(rdd.count()))

      topList.foreach { case (count, tag) =>
        println("%s (%s tweets)".format(tag, count))
        val result="%s (%s tweets)".format(tag, count);
        rdd.saveAsTextFile("output")
        SocketClient.sendCommandToRobot("TAG:"+tag+" "+"Count:"+count +" tweet\n ")
      }



    })
    ssc.start()

    ssc.awaitTermination()
  }

}
package com.cloudywaters

import scala.collection.JavaConverters._
import scala.io.Source
import java.io.InputStream

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.ListObjectsRequest

object First {

  def main(args: Array[String]) {

    val bucket: String = ""
    val request = new ListObjectsRequest()
    request.setBucketName(bucket)
    request.setMaxKeys(10)

    val region: Regions = Regions.US_WEST_2

    def s3: AmazonS3 = AmazonS3ClientBuilder.standard()
      .withRegion(region)
      .withCredentials(new ProfileCredentialsProvider(""))
      .build()

    var listing = s3.listObjects(request)
    var proceed = true
    while (proceed) {
      if (listing.getObjectSummaries.isEmpty) {
        proceed = false
      } else {
        val s3FileKeys = listing.getObjectSummaries.asScala.map(_.getKey).toList
        println(s3FileKeys)
        listing = s3.listNextBatchOfObjects(listing)
      }
    }
  }
}
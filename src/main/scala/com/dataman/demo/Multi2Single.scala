package com.dataman.demo

import java.io.{PrintWriter, File}

import scala.io.Source

/**
 * Created by mymac on 15/11/3.
 */
object Multi2Single {
  def subdirs(dir: File): Iterator[File] = {
    val d = dir.listFiles.filter(_.isDirectory)
    val f = dir.listFiles.toIterator
    f ++ d.toIterator.flatMap(subdirs _)
  }

  import java.nio.charset.CodingErrorAction
  import scala.io.Codec

  def conv(readPath:String, writePath: String) = {
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    val fileIter = subdirs(new File(readPath))
    while (fileIter.hasNext) {
      val file = fileIter.next
      if (file.isFile) {
        val lineIter = Source.fromFile(file).getLines
        val sb = new StringBuilder
        while (lineIter.hasNext) {
          val line = lineIter.next
          if (line.length > 1)
            line.addString(sb)
        }
        val writer = new PrintWriter(new File(writePath + file.getName))
        writer.write(sb.toString)
        writer.close()
      }
    }
  }
}

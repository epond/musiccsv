package musiccsv

import java.io.BufferedReader

object Main extends App {

  val labelsStream = getClass.getResourceAsStream("/Record_Labels.csv")
  val labelsReader = new BufferedReader(io.Source.fromInputStream(labelsStream).reader())
  val releasesStream = getClass.getResourceAsStream("/Record_Collection.csv")
  val releasesReader = new BufferedReader(io.Source.fromInputStream(releasesStream).reader())

  val importer = new Importer()
  val labels = importer.labels(labelsReader)
  val releases = importer.releases(releasesReader, labels)

  println(releases)

}
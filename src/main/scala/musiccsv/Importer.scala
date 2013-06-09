package musiccsv

import java.io.{StringReader, Reader}
import com.github.tototoshi.csv.CSVReader
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class Importer {

  val dateFormatter = DateTimeFormat.forPattern("MM/dd/yyyy")

  val defaultDate = new DateTime(0)

  def labels(labelsCSV: Reader): List[RecordLabel] = {
    val allLabelsCSV = CSVReader.open(labelsCSV).all()
    val allLabels = allLabelsCSV.map(labelLine => {
      val labelVector = labelLine.to[Vector]
      RecordLabel(labelVector(0), labelVector(1), labelVector(2), labelVector(3), parseCatalog(labelVector(4)))
    })
    labelsCSV.close()
    allLabels
  }

  def releases(releasesCSV: Reader, labels: List[RecordLabel]): List[RecordRelease] = {
    def labelEntry(label: RecordLabel) = (label.catalog.id -> label)
    val allReleases = releases(releasesCSV, labels.map(labelEntry).toMap)
    releasesCSV.close()
    allReleases
  }

  def releases(releasesCSV: Reader, labelMap: Map[Int, RecordLabel]): List[RecordRelease] = {
    val allReleasesCSV = CSVReader.open(releasesCSV).all()
    allReleasesCSV.map(releaseAsList => {
      val releaseVector = releaseAsList.to[Vector]
      RecordRelease(releaseVector(0),
                    releaseVector(1),
                    parseDate(releaseVector(2)),
                    labelFromCatalog(releaseVector(3), labelMap),
                    releaseVector(4),
                    releaseVector(5),
                    releaseVector(6),
                    releaseVector(7).toInt,
                    releaseVector(8).toInt)
    })
  }

  private def parseCatalog(catalogString: String): Catalog = {
    val catalogReader = CSVReader.open(new StringReader((catalogString)))
    val catalogList = catalogReader.readNext().getOrElse(List())
    val catalogVector = catalogList.to[Vector]
    new Catalog(catalogVector(0).toInt, catalogVector(1).trim, catalogVector(2).trim, catalogVector(3).trim)
  }

  def parseDate(dateString: String): DateTime = {
    try {
      dateFormatter.parseDateTime(dateString)
    }
    catch {
      case e: IllegalArgumentException => defaultDate
    }
  }

  def labelFromCatalog(catalogString: String, labelMap: Map[Int, RecordLabel]) = {
    val catalog = parseCatalog(catalogString)
    val noLabel = new RecordLabel("none")
    val label = labelMap.get(catalog.id) getOrElse (noLabel)
    label.name
  }

}

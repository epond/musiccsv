import java.io.{BufferedReader, StringReader, InputStreamReader, Reader}
import musiccsv.Importer
import org.specs2.mutable.Specification

/**
 * In the sbt console you can test only this spec with
 * test-only ImporterSpec
 */
class ImporterSpec extends Specification {

  def labelsCSV: Reader = Option {
    new BufferedReader(io.Source.fromInputStream(getClass.getResourceAsStream("Record_Labels.csv")).reader())
  } getOrElse {
    new StringReader("")
  }

  def releasesCSV: Reader = Option {
    new BufferedReader(io.Source.fromInputStream(getClass.getResourceAsStream("Record_Collection.csv")).reader())
  } getOrElse {
    new StringReader("")
  }

  "An Importer" should {

    "convert a label csv row into a RecordLabel" in {
      val label = new Importer().labels(labelsCSV).head
      label.name mustEqual "warp"
      label.website mustEqual "www.warprecords.com"
      label.origin mustEqual "Sheffield"
      label.note mustEqual ""
      label.catalog.id mustEqual 441
      label.catalog.misc1 mustEqual "6768050030b2d1f4"
      label.catalog.misc2 mustEqual "2000033"
      label.catalog.misc3 mustEqual "506"
    }

    "import labels wergo and warp" in {
      val labels = new Importer().labels(labelsCSV)
      labels.size mustEqual 2
      labels.head.name mustEqual "warp"
      labels.tail.head.name mustEqual "wergo"
    }

    "import releases Kontakte and Frequencies" in {
      val importer = new Importer()
      val labels = importer.labels(labelsCSV)
      val releases = importer.releases(releasesCSV, labels)
      releases.size mustEqual 2
      releases.head.title mustEqual "Frequencies"
      releases.tail.head.title mustEqual "Kontakte"
    }

    "convert a release csv row into a RecordRelease" in {
      val importer = new Importer()
      val labels = importer.labels(labelsCSV)
      val release = importer.releases(releasesCSV, labels).head
      release.artist mustEqual "LFO"
      release.title mustEqual "Frequencies"
      release.added.getYear mustEqual 1991
      release.added.getMonthOfYear mustEqual 8
      release.added.getDayOfMonth mustEqual 27
      release.label mustEqual "warp"
      release.format mustEqual "cd"
      release.length mustEqual "full-length"
      release.genre mustEqual "IDM;Techno"
      release.mp3cd mustEqual 8
      release.wavdvd mustEqual 12
    }

    "parse missing 'date added' as start of epoch" in {
      val importer = new Importer()
      val labels = importer.labels(labelsCSV)
      val release = importer.releases(releasesCSV, labels).tail.head
      release.added.getYear mustEqual 1970
      release.added.getMonthOfYear mustEqual 1
      release.added.getDayOfMonth mustEqual 1
    }

    "associate a label by its id" in {
      val importer = new Importer()
      val releases = importer.releases(releasesCSV, importer.labels(labelsCSV))
      val frequencies = releases.head
      val kontakte = releases.tail.head
      frequencies.label mustEqual "warp"
      kontakte.label mustEqual "wergo"
    }

  }
}

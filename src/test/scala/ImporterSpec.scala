import java.io.{StringReader, InputStreamReader, Reader}
import musiccsv.Importer
import org.specs2.mutable.Specification

class ImporterSpec extends Specification {

  val labelsCSV: Reader = Option {
    io.Source.fromInputStream(getClass.getResourceAsStream("Record_Labels.csv")).reader()
  } getOrElse {
    new StringReader("")
  }

  val releasesCSV: Reader = Option {
    io.Source.fromInputStream(getClass.getResourceAsStream("Record_Labels.csv")).reader()
  } getOrElse {
    new StringReader("")
  }

  "An Importer" should {
    "import labels Wergo and Warp" in {
      val labels = new Importer().labels(labelsCSV)
      labels.size mustEqual 2
      labels.head.name mustEqual "Wergo"
      labels.tail.head.name mustEqual "Warp"
    }
    "import releases Kontakte and Frequencies" in {
      val releases = new Importer().records(releasesCSV)
      releases.size mustEqual 2
      releases.head.title mustEqual "Kontakte"
      releases.tail.head.title mustEqual "Frequencies"
    }
  }
}

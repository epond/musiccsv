import musiccsv.{Catalog, RecordLabel}
import org.specs2.mutable.Specification

class RecordLabelSpec extends Specification {
  "A RecordLabel" should {
    "have accessible fields" in {
      var catalog = Catalog(1234, null, null, null)
      var label = RecordLabel("Doobery", "www.doobery.com", "Finland", "awesome", catalog)
      label.name mustEqual "Doobery"
      label.website mustEqual "www.doobery.com"
      label.origin mustEqual "Finland"
      label.note mustEqual "awesome"
    }
    "be composed of a catalog" in {
      var catalog = Catalog(1234, null, null, null)
      var label = RecordLabel("Doobery", "www.doobery.com", "Finland", "awesome", catalog)
      label.catalog.id mustEqual 1234
    }
  }
}

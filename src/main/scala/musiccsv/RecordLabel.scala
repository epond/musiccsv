package musiccsv

case class RecordLabel(name: String, website: String, origin: String, note: String, catalog: Catalog) {
  def this(name: String) = this(name, null, null, null, null)
}

case class Catalog(id: Int, misc1: String, misc2: String, misc3: String) {
  def this(id: Int) = this(id, null, null, null)
}
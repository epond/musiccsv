package musiccsv

case class RecordLabel(name: String, website: String, origin: String, note: String, catalog: Catalog)

case class Catalog(id: Int, misc1: String, misc2: String, misc3: String)
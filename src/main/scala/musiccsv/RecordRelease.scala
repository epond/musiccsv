package musiccsv

import org.joda.time.DateTime

case class RecordRelease(artist: String, title: String, added: DateTime, label: String, format: String, length: String, genre: String, mp3cd: Int, wavdvd: Int) {
  def toCSV = List(artist, title, added.toString("dd/MM/yyyy"), label, format, length, genre)
}
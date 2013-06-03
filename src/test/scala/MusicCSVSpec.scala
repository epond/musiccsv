import org.specs2._

/**
 * As a music lover I can obtain my record collection as a single csv file
 * including record label column in plain text and excluding unused columns
 * so that I can maintain it in Google Docs and import into a future project.
 *
 * Dates must be of a consistent format and releases without a date must be
 * given start of epoch.
 */
class MusicCSVSpec extends Specification { def is = end

  // Given a labels csv containing two labels
  // And a record collection csv containing two records
  // When the labels are imported
  // And the records are imported
  // Then the Stockhausen record is on the Wergo label
  // And the LFO record is on the Warp label

}

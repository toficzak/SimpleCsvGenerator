package csv.generator;

public interface CsvRepresentational {

	/**
	 * Maps entity fields to title row of csv file.
	 * 
	 * @return csv representation of used fields.
	 */
	String titleRow();

	/**
	 * Maps entity to csv representation.
	 * 
	 * @return csv representation of entity.
	 */

	String toCSVRow();

}

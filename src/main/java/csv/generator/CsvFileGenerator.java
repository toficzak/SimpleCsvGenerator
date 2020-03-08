package csv.generator;

import java.io.File;
import java.util.List;

public interface CsvFileGenerator {

	File generate(List<? extends CsvRepresentational> entities);
}

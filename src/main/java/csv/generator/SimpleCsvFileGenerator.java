package csv.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCsvFileGenerator implements CsvFileGenerator {

	@Override
	public File generate(String filePath,
			List<? extends CsvRepresentational> entities) {

		if (entities == null || entities.isEmpty()) {
			throw new CsvGenerationException("List empty!");
		}

		try (FileWriter writer = new FileWriter(filePath)) {
			String content = this.generateContent(entities);
			writer.append(content);
			writer.close();
			return new File(filePath);
		} catch (IOException e) {
			throw new CsvGenerationException("File creation error!");
		}
	}

	private String generateContent(
			List<? extends CsvRepresentational> entities) {

		List<String> content = new ArrayList<>();
		content.add(entities.get(0).titleRow());
		content.addAll(entities.stream().map(CsvRepresentational::toCSVRow)
				.collect(Collectors.toList()));
		return content.stream()
				.collect(Collectors.joining(CsvConfig.SEPARATOR));

	}

}

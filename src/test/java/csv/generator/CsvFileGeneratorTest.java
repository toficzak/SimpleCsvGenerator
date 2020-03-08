package csv.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import csv.entity.SystemUser;

public class CsvFileGeneratorTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	private String filePath;

	private final String NAME = "Test name";
	private final String EMAIL = "Test email";

	private final String FILENAME = "testCsvFile.csv";

	@Test(expected = CsvGenerationException.class)
	public void generate___shouldThrowException() throws IOException {

		// given
		filePath = this.generateFilePath();
		CsvFileGenerator csvFileGenerator = new SimpleCsvFileGenerator(
				filePath);

		// when
		csvFileGenerator.generate(Collections.emptyList());
	}

	@Test
	public void generate___shouldSucceed() throws IOException {

		// given
		filePath = this.generateFilePath();
		SystemUser systemUser = new SystemUser(NAME, EMAIL);
		CsvFileGenerator csvFileGenerator = new SimpleCsvFileGenerator(
				filePath);

		// when
		File file = csvFileGenerator.generate(List.of(systemUser));

		// then
		assertTrue(file.exists());
		assertTrue(file.isFile());
		assertEquals(FILENAME, file.getName());

		List<String> content = Files.lines(Paths.get(file.getAbsolutePath()))
				.collect(Collectors.toList());
		assertEquals(2, content.size());
		assertEquals("Identificator,User's name,User's email", content.get(0));
		assertEquals("1,Test name,Test email", content.get(1));
	}

	private String generateFilePath() {
		try {
			return new StringBuilder(testFolder.newFolder().getAbsolutePath())
					.append("/").append(FILENAME).toString();
		} catch (IOException e) {
			throw new RuntimeException("Confiuration failed.");
		}
	}

}

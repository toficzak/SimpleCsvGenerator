package csv.entity;

import java.util.Arrays;
import java.util.stream.Collectors;

import csv.generator.CsvConfig;
import csv.generator.CsvRepresentational;

public class SystemUser implements CsvRepresentational {

	private Long id;
	private String name;
	private String email;
	private boolean active;

	@Override
	public String titleRow() {
		String[] data = new String[]{"Identificator", "User's name",
				"User's email"};

		return Arrays.stream(data)
				.collect(Collectors.joining(CsvConfig.SEPARATOR));
	}

	@Override
	public String toCSVRow() {
		String[] data = new String[]{id.toString(), name, email};

		return Arrays.stream(data)
				.collect(Collectors.joining(CsvConfig.SEPARATOR));
	}

}

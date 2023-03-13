package com.mjc.school.controller;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

		private DataGenerator() {		}
		public static final String DATA_NEWS_CSV = "module-repository/src/main/resources/data_news.csv";
		public static final String DATA_AUTHOR_CSV = "module-repository/src/main/resources/data_authors.csv";
		public static final String CONTENT_TXT = "module-repository/src/main/resources/content";
		public static final String NEWS_TXT = "module-repository/src/main/resources/news";
		public static final String AUTHORS_TXT = "module-repository/src/main/resources/authors";

		public static void generateData() {
				List<String> listContent = new ArrayList<>();
				List<String> listTitle = new ArrayList<>();
				List<String> listAuthorName = new ArrayList<>();

				try (BufferedReader fileReaderContent = new BufferedReader(new FileReader(CONTENT_TXT));
						 BufferedReader fileReaderTitle = new BufferedReader(new FileReader(NEWS_TXT));
						 BufferedReader fileReaderName = new BufferedReader(new FileReader(AUTHORS_TXT))) {
						listContent = fileReaderContent.lines().toList();
						listTitle = fileReaderTitle.lines().toList();
						listAuthorName = fileReaderName.lines().toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				try (BufferedWriter newsWriter = new BufferedWriter(new FileWriter(DATA_NEWS_CSV));
						 BufferedWriter authorWriter = new BufferedWriter(new FileWriter(DATA_AUTHOR_CSV))) {
						for (long i = 0L; i < 20; i++) {
								StringJoiner sj = new StringJoiner(";");
								sj.add(String.valueOf(i))
												.add(listTitle.get(ThreadLocalRandom.current().nextInt(0, listTitle.size())))
												.add(listContent.get(ThreadLocalRandom.current().nextInt(0, listContent.size())))
												.add(randomDate().toString())
												.add(randomDate().toString())
												.add(String.valueOf(ThreadLocalRandom.current().nextLong(1, listAuthorName.size())))
												.add("\n");
								newsWriter.write(sj.toString());
						}
						for (int i = 0; i < listAuthorName.size(); i++) {
								StringJoiner sj = new StringJoiner(";");
								sj.add(String.valueOf(i))
												.add(listAuthorName.get(i))
												.add(randomDate().toString())
												.add(randomDate().toString())
												.add("\n");
								authorWriter.write(sj.toString());
						}
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public static LocalDateTime randomDate() {
				long startMillis = LocalDateTime.now().minus(Duration.ofDays(10)).toEpochSecond(ZoneOffset.ofHours(0));
				long endMillis = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0));
				long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
				return LocalDateTime.ofEpochSecond(randomMillisSinceEpoch, 0, ZoneOffset.ofHours(0));
		}
}
package com.mjc.school.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
@NoArgsConstructor
public class DataSource {
		public static final String DATA_NEWS_CSV = "module-repository/src/main/resources/data_news.csv";

		public static final String DATA_AUTHOR_CSV = "module-repository/src/main/resources/data_authors.csv";
		public List<NewsModel> parseNewsFromFile() {
				List<NewsModel> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader(DATA_NEWS_CSV))){
						list = fileReader.lines().map(this::stringToNews).toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				return list;
		}
		public List<AuthorModel> parseAuthorFromFile() {
				List<AuthorModel> list = new ArrayList<>();
				try (BufferedReader fileReader = new BufferedReader(new FileReader(DATA_AUTHOR_CSV))){
						list = fileReader.lines().map(this::stringToAuthor).toList();
				} catch (IOException e) {
						e.printStackTrace();
				}
				return list;
		}
		public void appendNewsToFile(NewsModel newsModel) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_NEWS_CSV, true))) {
						bw.append(newsToString(newsModel));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public void appendAuthorToFile(AuthorModel authorModel) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_AUTHOR_CSV, true))) {
						bw.append(authorToString(authorModel));
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public void saveAllNewsToFile(List<NewsModel> newsModelList) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_NEWS_CSV))) {
						newsModelList.forEach(n -> {
								try {
										bw.write(newsToString(n) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		public void saveAllAuthorsToFile(List<AuthorModel> authorModelList) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_AUTHOR_CSV))) {
						authorModelList.forEach(n -> {
								try {
										bw.write(authorToString(n) + "\n");
								} catch (IOException e) {
										e.printStackTrace();
								}
						});
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		private NewsModel stringToNews(String s) {
				String[] stringArr = s.split(";");
				return new NewsModel(Long.parseLong(stringArr[0]),stringArr[1],stringArr[2], LocalDateTime.parse(stringArr[3]),LocalDateTime.parse(stringArr[4]),Long.parseLong(stringArr[5]));
		}
		private AuthorModel stringToAuthor(String s) {
				String[] stringArr = s.split(";");
				return new AuthorModel(Long.parseLong(stringArr[0]),stringArr[1],LocalDateTime.parse(stringArr[2]),LocalDateTime.parse(stringArr[3]));
		}
		private String newsToString(NewsModel newsModel) {
				StringJoiner sj = new StringJoiner(";");
				return sj.add(newsModel.getId().toString())
								.add(newsModel.getTitle())
								.add(newsModel.getContent())
								.add(newsModel.getCreateDate().toString())
								.add(newsModel.getLastUpdateDate().toString())
								.add(newsModel.getAuthorId().toString())
								.toString();
		}
		private String authorToString(AuthorModel authorModel) {
				StringJoiner sj = new StringJoiner(";");
				return sj.add(authorModel.getId().toString())
								.add(authorModel.getName())
								.add(authorModel.getCreateDate().toString())
								.add(authorModel.getLastUpdateDate().toString())
								.toString();
		}
}
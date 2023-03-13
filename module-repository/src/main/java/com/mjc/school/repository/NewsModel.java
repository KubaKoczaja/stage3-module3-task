package com.mjc.school.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsModel implements BaseEntity<Long>{
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
		private Long authorId;
}

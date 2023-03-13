package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsModelDto implements BaseEntityDto<Long> {
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
		private Long authorId;
}

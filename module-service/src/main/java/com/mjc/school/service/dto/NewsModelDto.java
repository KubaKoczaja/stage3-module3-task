package com.mjc.school.service.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "authorModelDto")
@EqualsAndHashCode(exclude = "authorModelDto")
public class NewsModelDto implements BaseEntityDto<Long> {
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
		private AuthorModelDto authorModelDto;
}

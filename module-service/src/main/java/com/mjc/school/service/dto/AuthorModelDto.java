package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorModelDto implements BaseEntityDto<Long> {
		private Long id;
		private String name;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
}

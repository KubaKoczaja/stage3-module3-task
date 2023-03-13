package com.mjc.school.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorModel implements BaseEntity<Long>{
		private Long id;
		private String name;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
}
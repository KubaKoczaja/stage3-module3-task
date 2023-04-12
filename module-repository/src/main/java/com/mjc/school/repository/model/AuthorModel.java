package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = "newsModelList")
@EqualsAndHashCode(exclude = "id")
public class AuthorModel implements BaseEntity<Long>{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@NonNull
		private String name;
		@NonNull
		private LocalDateTime createDate;
		@NonNull
		private LocalDateTime lastUpdateDate;
		@OneToMany(mappedBy = "authorModel")
		private List<NewsModel> newsModelList = new ArrayList<>();
}
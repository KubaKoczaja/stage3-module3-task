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
		@Column(name = "ID")
		private Long id;
		@Column(name = "NAME")
		@NonNull
		private String name;
		@NonNull
		@Column(name = "CREATE_DATE")
		private LocalDateTime createDate;
		@NonNull
		@Column(name = "LAST_UPDATE_DATE")
		private LocalDateTime lastUpdateDate;
		@OneToMany(mappedBy = "authorModel")
		private List<NewsModel> newsModelList = new ArrayList<>();
}
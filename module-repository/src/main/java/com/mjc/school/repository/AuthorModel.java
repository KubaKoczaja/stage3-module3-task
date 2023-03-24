package com.mjc.school.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "AUTHOR")
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
		private List<NewsModel> newsModelList;
}
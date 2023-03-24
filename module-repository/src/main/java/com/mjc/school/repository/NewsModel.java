package com.mjc.school.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "NEWS")
public class NewsModel implements BaseEntity<Long>{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "ID")
		private Long id;
		@Column(name = "TITLE")
		@NonNull
		private String title;
		@Column(name = "CONTENT")
		@NonNull
		private String content;
		@Column(name = "CREATE_DATE")
		@NonNull
		private LocalDateTime createDate;
		@Column(name = "LAST_UPDATE_DATE")
		@NonNull
		private LocalDateTime lastUpdateDate;
		@ManyToOne
		@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "id")
		private AuthorModel authorModel;
}

package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "NEWS")
@ToString(exclude = {"tagModelSet", "authorModel"})
@EqualsAndHashCode(exclude = {"id", "tagModelSet"})
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
		@ManyToOne(cascade={CascadeType.MERGE})
		@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
		private AuthorModel authorModel;
		@ManyToMany(cascade={CascadeType.MERGE})
		@JoinTable(name="NEWS_TAG",
						joinColumns=@JoinColumn(name="TAG_ID", referencedColumnName = "ID"),
						inverseJoinColumns=@JoinColumn(name="NEWS_ID", referencedColumnName = "ID"))
		private Set<TagModel> tagModelSet = new HashSet<>();
}

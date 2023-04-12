package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = "newsModelSet")
@EqualsAndHashCode(exclude = {"id", "newsModelSet"})
public class TagModel implements BaseEntity<Long>{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@NonNull
		private String name;
		@ManyToMany(mappedBy = "tagModelSet", cascade={CascadeType.MERGE})
		private Set<NewsModel> newsModelSet = new HashSet<>();
}

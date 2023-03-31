package com.mjc.school.repository;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TAG")
@ToString(exclude = "newsModelSet")
@EqualsAndHashCode(exclude = "id")
public class TagModel implements BaseEntity<Long>{
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "ID")
		private Long id;
		@Column(name = "NAME")
		@NonNull
		private String name;
		@ManyToMany(mappedBy = "tagModelSet")
		@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
		private Set<NewsModel> newsModelSet = new HashSet<>();
}

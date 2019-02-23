package by.grizzly.recommendations.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Long telegramId;

	@Column
	@Enumerated(EnumType.STRING)
	private State state;
}

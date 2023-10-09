package team.java;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="boardid")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}

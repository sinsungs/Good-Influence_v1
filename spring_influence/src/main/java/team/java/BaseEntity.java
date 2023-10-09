package team.java;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass  // 테이블로 생성하지 않는다 
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;
}

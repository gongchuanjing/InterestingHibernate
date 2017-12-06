package tk.chuanjing.chapter03.manyToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author ChuanJing
 * @date 2017年7月16日 下午3:32:50
 * @version 1.0
 */

@Entity
@Table(name="t_student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	// 使用JoinTabl来描述中间表，并描述中间表中外键与Student,Teacher的映射关系
	// joinColumns它是用来描述Student与中间表中的映射关系
	// inverseJoinColums它是用来描述Teacher与中间表中的映射关系
	@ManyToMany(targetEntity=Teacher.class)
	@JoinTable( name = "s_t",
				joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id") },
				inverseJoinColumns = { @JoinColumn(name = "teacher_id", referencedColumnName = "id")}
			  )
	@Cascade(CascadeType.SAVE_UPDATE)
//	@Cascade(CascadeType.ALL)//慎用
	private Set<Teacher> teachers = new HashSet<Teacher>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
}
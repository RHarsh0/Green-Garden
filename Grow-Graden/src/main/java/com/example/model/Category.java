package com.example.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	
	 @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	private List<Product> products;
	
	private Integer level;

}

package com.codewithsam.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter			
@Setter
public class CategoryDto {
	
	private Integer categoryId;

	@Size(min=3)
	private String categoryTitle;

	@NotBlank
	@Size(max=20)
	private String categoryDescription;
	
}

package com.codewithsam.blog.payloads;




import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=3, message= "Username must be of min of 3 characters!!")
	private String name;
	
	@NotEmpty
	@Size(min=3, max=10, message= "Password must be of size 3-10")
	private String password;
	
	@Email
	private String email;
	
	@NotEmpty
	private String about;
	
}

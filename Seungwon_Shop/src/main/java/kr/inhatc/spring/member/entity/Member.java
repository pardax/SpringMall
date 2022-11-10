package kr.inhatc.spring.member.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.inhatc.spring.member.constant.Role;
import kr.inhatc.spring.member.dto.MemberFormDto;
import kr.inhatc.spring.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private long id;
	
	private String name;
	
	@Column(unique = true)
	private String email; 
	private String password; 
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberFormDto,
			PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		member.setRole(Role.USER);
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);
		return member;
	}
}
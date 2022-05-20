package peg.board.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	/* UserDetails
	 * Spring Security에서 사용자의 정보를 담는 인터페이스
	 * 사용자 정보를 불러오기 위해서 구현해야하는 인터페이스다.
	 */
	private String id;
	private String pw;
	private boolean memberDiv;
	private String memberAuthority;
	private boolean memberEnabled;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //계정 권한 목록을 리턴
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(memberAuthority));
		return auth;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pw;
	}
	@Override
	public String getUsername() { //계정의 고유한 값을 리턴한다. 통합인증(SSO)으로 중복된 아이디/이메일이 생길 수 있음 -> DB table의 pk를 넘겨주는 것이 좋음
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean isAccountNonExpired() { //계정의 만료 여부 (true = 만료 안됨)
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() { //계정의 잠김 여부 (true = 잠기지 않음)
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() { //비밀번호의 만료 여부 (true = 만료 안됨)
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() { //계정의 활성화 여부 (true = 활성화)
		// TODO Auto-generated method stub
		return memberEnabled;
	}
}

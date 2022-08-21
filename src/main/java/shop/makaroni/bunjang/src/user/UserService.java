package shop.makaroni.bunjang.src.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.makaroni.bunjang.src.user.dao.UserDao;
import shop.makaroni.bunjang.src.user.model.dto.PatchUserRequest;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserDao userDao;

	public void update(Long userId, PatchUserRequest request) {
		userDao.update(userId, request);
	}
}

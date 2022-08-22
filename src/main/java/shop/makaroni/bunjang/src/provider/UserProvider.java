package shop.makaroni.bunjang.src.provider;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.makaroni.bunjang.src.dao.FollowDao;
import shop.makaroni.bunjang.src.dao.ReviewDao;
import shop.makaroni.bunjang.src.dao.UserDao;
import shop.makaroni.bunjang.src.dao.WishListDao;
import shop.makaroni.bunjang.src.domain.item.Item;
import shop.makaroni.bunjang.src.domain.item.State;
import shop.makaroni.bunjang.src.domain.user.User;
import shop.makaroni.bunjang.src.domain.user.dto.MyStoreResponse;
import shop.makaroni.bunjang.src.domain.user.dto.StoreSaleResponse;
import shop.makaroni.bunjang.utils.resolver.PagingCond;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserProvider {

	private final UserDao userDao;
	private final ReviewDao reviewDao;
	private final WishListDao wishListDao;
	private final FollowDao followDao;

	public MyStoreResponse getMyStore(Long userId) {

		User user = findById(userId);
		Integer reviewCount = reviewDao.countStoreReview(userId);
		Integer wishListCount = wishListDao.countMyWishList(userId);
		Integer followerCount = followDao.countMyFollowers(userId);
		Integer followingcount =  followDao.countMyFollowings(userId);

		return MyStoreResponse.of(user, reviewCount, wishListCount, followerCount, followingcount, getMyStoreItem(userId, State.SELLING.getState(), PagingCond.defaultValue()));
	}

	public List<StoreSaleResponse> getMyStoreItem(Long userId, String condition, PagingCond pagingCond) {
		findById(userId);
		List<Item> item = userDao.getMyStoreItem(userId, condition, pagingCond);
		return item.stream()
				.map(StoreSaleResponse::of)
				.collect(Collectors.toList());
	}

	public List<StoreSaleResponse> searchStoreItemByName(Long userId, String itemName, String condition, PagingCond pagingCond) {
		findById(userId);
		List<Item> item = userDao.searchStoreItemByName(userId, itemName, condition, pagingCond);
		return item.stream()
				.map(StoreSaleResponse::of)
				.collect(Collectors.toList());
	}

	public User findById(Long userId) {
		User user = userDao.findById(userId).orElseThrow(NoSuchElementException::new);
		user.validate();
		return user;
	}
}

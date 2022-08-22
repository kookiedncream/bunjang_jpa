package shop.makaroni.bunjang.src.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FollowDao {

	private final NamedParameterJdbcTemplate template;

	public Integer countMyFollowers(Long userId) {
		var sql = "select count(*) from Follow f " +
				"where f.userIdx=:userId " +
				"and f.status='Y'";
		return template.queryForObject(sql, Map.of("userId", userId), Integer.class);
	}

	public Integer countMyFollowings(Long userId) {
		var sql = "select count(*) from Follow f " +
				"where f.storeIdx=:userId " +
				"and f.status='Y'";
		return template.queryForObject(sql, Map.of("userId", userId), Integer.class);
	}
}
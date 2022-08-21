package shop.makaroni.bunjang.src.user.model.dto;

import lombok.Getter;

@Getter
public class PatchUserRequest {
	String storeName;
	String storeUrl;
	Integer contactStart;
	Integer contactEnd;
	String description;
	String policy;
	String precaution;

	public PatchUserRequest(String storeName, String storeUrl, Integer contactStart, Integer contactEnd, String description, String policy, String precaution) {
		this.storeName = storeName;
		this.storeUrl = storeUrl;
		this.contactStart = contactStart;
		this.contactEnd = contactEnd;
		this.description = description;
		this.policy = policy;
		this.precaution = precaution;
	}
}
package shop.makaroni.bunjang.src.validation;
import shop.makaroni.bunjang.config.BaseException;
import shop.makaroni.bunjang.config.BaseResponse;
import shop.makaroni.bunjang.src.domain.item.model.*;

import static shop.makaroni.bunjang.config.BaseResponseStatus.*;
import static shop.makaroni.bunjang.src.validation.validationRegex.*;

public class validation {
    public static void validateItems(ItemReq itemReq) throws BaseException {
        if( (itemReq.getDelivery() != null && itemReq.getDelivery() < 0) ||
                (itemReq.getIsNew() != null && itemReq.getIsNew() < 0) ||
                (itemReq.getExchange() !=null && itemReq.getExchange() < 0) ||
                (itemReq.getSafePay() != null && itemReq.getSafePay() < 0) ||
                (itemReq.getIsAd() != null && itemReq.getIsAd() < 0 )||
                (itemReq.getInspection()!= null && itemReq.getInspection() < 0 )){
            throw new BaseException(REQUEST_ERROR);
        }
        if(itemReq.getName() != null && !isRegexItemName(itemReq.getName())){
            throw new BaseException(POST_ITEM_INVALID_NAME);
        }
        if(itemReq.getCategory() != null && !isRegexCategory(itemReq.getCategory())){
            throw new BaseException(POST_ITEM_INVALID_CATEGORY);
        }
        if(itemReq.getPrice() != null &&(itemReq.getPrice() < 100 || itemReq.getPrice() > 100000000)){
            throw new BaseException(POST_ITEM_INVALID_PRICE);
        }
        if(itemReq.getStock() != null && itemReq.getStock() < 1){
            throw new BaseException(POST_ITEM_INVALID_STOCK);
        }
        if(itemReq.getContent() != null && !isRegexContent(itemReq.getContent())){
            throw new BaseException(POST_ITEM_INVALID_CONTENT);
        }
        if(itemReq.getPrice() != null &&  itemReq.getPrice() < 500){
            itemReq.setSafePay(0);
        }
        if(itemReq.getSellerIdx() != null && itemReq.getSellerIdx() < 0){
            throw new BaseException(POST_ITEM_INVALID_SELLER);
        }
    }
}
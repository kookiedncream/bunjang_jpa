package shop.makaroni.bunjang.src.controller;

import org.hibernate.hql.internal.ast.tree.DisplayableNode;
import shop.makaroni.bunjang.config.BaseException;
import shop.makaroni.bunjang.config.BaseResponse;
import shop.makaroni.bunjang.src.domain.item.model.GetItemRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.makaroni.bunjang.src.provider.ItemProvider;
import shop.makaroni.bunjang.src.service.ItemService;
import shop.makaroni.bunjang.src.domain.item.model.*;

import static shop.makaroni.bunjang.config.BaseResponseStatus.*;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private final ItemProvider itemProvider;
    @Autowired
    private final ItemService itemService;

    public ItemController(ItemProvider itemProvider, ItemService itemService) {
        this.itemProvider = itemProvider;
        this.itemService = itemService;
    }

    @ResponseBody
    @GetMapping("/{itemIdx}")
    public BaseResponse<GetItemRes> getItem(@PathVariable("itemIdx") int itemIdx) {
        try {
            GetItemRes getItemRes;
            getItemRes= itemProvider.getItem(itemIdx);
            getItemRes.setWish(itemProvider.getItemWishCnt(itemIdx));
            getItemRes.setChat(itemProvider.getItemChatCnt(itemIdx));
            getItemRes.setTags(itemProvider.getItemTags(itemIdx));
            getItemRes.setImages(itemProvider.getItemImages(itemIdx));
            return new BaseResponse<>(getItemRes);
        } catch (BaseException exception) {
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
/*

    @ResponseBody
    @PostMapping("/{search}")
     public BaseResponse<PostSearchRes> PostSearch(@RequestBody PostSearchReq postSearchReq) {
        // 입력 없으면 int : 0, char:'\0', String:null, boolean:false
        if(postSearchReq.getCategory().length()>10){
            return new BaseResponse<>(ITEM_INVALID_CATEGORY);
        }
        if(postSearchReq.getBrand() < 0){
            return new BaseResponse<>(ITEM_INVALID_BRAND_ID);
        }
        if(postSearchReq.getMinPrice() < 0 ||
                postSearchReq.getMinPrice() > postSearchReq.getMaxPrice()){
            return new BaseResponse<>(ITEM_INVALID_PRICE_MIN);
        }
        if(postSearchReq.getMaxPrice() > 100000000){
            return new BaseResponse<>(ITEM_INVALID_PRICE_MAX);
        }
        char sort = postSearchReq.getSort();
        if(sort == '\0'){
            postSearchReq.setSort('R');
        }
        else if(sort!='R' && sort !='F' &&
                sort !='L' && sort !='H'){
            return new BaseResponse<>(ITEM_INVALID_SORT);
        }
        int period = postSearchReq.getPeriod();
        if( period!= 0 && period != 1 && period != 3 && period != 7 && period!= 30){
            return new BaseResponse<>(ITEM_INVALID_PERIOD);
        }
        char delivery = postSearchReq.getDelivery();
        if (delivery == '\0'){
            postSearchReq.setDelivery('E');
        }
        else if(delivery != 'Y' && delivery != 'N' && delivery != 'E'){
            return new BaseResponse<>(ITEM_INVALID_DELIVERY);
        }
        char isNew = postSearchReq.getIsNew();
        if (isNew == '\0'){
            postSearchReq.setIsNew('E');
        }
        else if(isNew != 'Y' && isNew != 'N' && isNew != 'E'){
            return new BaseResponse<>(ITEM_INVALID_DELIVERY);
        }
        char exchange = postSearchReq.getExchange();
        if (exchange == '\0'){
            postSearchReq.setExchange('E');
        }
        else if(exchange != 'Y' && exchange != 'N' && exchange != 'E'){
            return new BaseResponse<>(ITEM_INVALID_DELIVERY);
        }
        try {
            PostSearchRes postSearchRes = itemProvider.PostSearch(postSearchReq);
            return new BaseResponse<>(postSearchRes);
        } catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }
    */


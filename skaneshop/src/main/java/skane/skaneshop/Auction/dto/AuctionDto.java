package skane.skaneshop.Auction.dto;

import lombok.Data;
import skane.skaneshop.domain.Auction;
import skane.skaneshop.domain.Category;
import skane.skaneshop.domain.Image;
import skane.skaneshop.domain.Option;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuctionDto {

    private Long auctionId;
    private Category category;
    private Option option;
    private String itemName;
    //상품 설명
    private String postName;
    private List<AuctionRequestDto> bidInfo;
    private LocalDateTime left_time;
    private List<Image> images;
    private String userName;

    public AuctionDto(Auction auction){
        this.auctionId =auction.getAuctionNumber();
        this.category = auction.getCategory();
        this.option = auction.getOption();
        this.itemName = auction.getPostBordNumber().getProductNumber().getName();
        this.postName = auction.getPostBordNumber().getName();
        this.bidInfo = auction.getBidInfos().stream()
                .map(o->new AuctionRequestDto(o.getAuction().getAuctionNumber(),o.getUserName(),o.getBid_Price()))
                .collect(Collectors.toList());
        Collections.sort(bidInfo,(a,b)->b.getBid_price()-a.getBid_price());
        this.left_time=auction.getLeft_time();
        this.images = auction.getPostBordNumber().getProductNumber().getImageNumber();
        this.userName = auction.getPostBordNumber().getProductNumber().getUserNumber().getName();
    }

}

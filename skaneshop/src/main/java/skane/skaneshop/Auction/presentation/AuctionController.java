package skane.skaneshop.Auction.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skane.skaneshop.Auction.dto.AuctionDto;
import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.domain.Auction;

import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
public class AuctionController {


    private final AuctionTestRepository auctionTestRepository;


    /**
     * 경매 전체리스트
     * @return
     */
    @GetMapping("/auctions")
    public String auction_list(){

        return "";
    }


    /**
     * 경매 물품 상세페이지
     * @return
     */
    @GetMapping("/auction/{id}")
    public String auction_detail(@PathVariable Long id , Model model){

        Auction auction = auctionTestRepository.findById(id);

        AuctionDto auctionDto = new AuctionDto(auction);


        model.addAttribute("auction",auctionDto);

        return "auction/scone_auction";
    }

}

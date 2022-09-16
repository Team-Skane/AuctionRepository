package skane.skaneshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import skane.skaneshop.Auction.application.AuctionService;
import skane.skaneshop.Auction.application.AuctionTimeThread;
import skane.skaneshop.Auction.infra.AuctionTestRepository;
import skane.skaneshop.Auction.infra.BidInfoRepository;
import skane.skaneshop.Auction.infra.BidInfoTestRepository;
import skane.skaneshop.board.application.FileStore;
import skane.skaneshop.board.infra.ItemRepository;
import skane.skaneshop.domain.*;
import skane.skaneshop.login.dto.request.Member;
import skane.skaneshop.login.infrastructure.MemberRepository;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final AuctionService auctionService;
    private final BidInfoRepository bidInfoRepository;
    private final AuctionTestRepository auctionTestRepository;
    private final BidInfoTestRepository bidInfoTestRepository;

    private final FileStore fileStore;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {


        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        memberRepository.save(member);
    }


    /**
     * 이 데이터 추가는 TestRepository 용임.
     */
    @PostConstruct
    public void Auction_init(){

        //예시 유저 2명 생성
        User user = new User(null,"test2","1234","판매자1",20,ManagementStatus.USER,Gender.MAN);
        User user2 = new User(null,"test3","1234","판매자2",20,ManagementStatus.USER,Gender.WOMAN);

        // 예시 상품 2개 생성

        List<Image> images = new ArrayList<>();
        Image image = new Image();
        image.setPath("컴퓨터.jpg");
        images.add(image);

        Image image3 = new Image();
        image3.setPath("컴퓨터2.jpg");
        images.add(image3);

        List<Image> images2 = new ArrayList<>();
        Image image2 = new Image();
        image2.setPath("스마트자바.jpg");
        images2.add(image2);


        Product product = new Product(1L,user,"컴퓨터",100000
                , Category.ELECTRONIC_PRODUCTS,true, SaleOption.AUCTION,Option.POST,images);
        Product product2 = new Product(2L,user2,"스마트 자바 프로그래밍",15400
                , Category.BOOKS,true, SaleOption.AUCTION,Option.POST,images2);

        //에시 게시물 2개 생성
        PostBoard postBoard = new PostBoard(1L,product,"컴퓨터 판매","",Status.RESERVED, Timestamp.valueOf(LocalDateTime.now()));
        PostBoard postBoard2 = new PostBoard(2L,product2,"자바 기본서 팝니다","",Status.RESERVED, Timestamp.valueOf(LocalDateTime.now()));


        //예시 옥션 2개생성

        Auction auction = new Auction();
        auction.setPostBordNumber(postBoard);
        auction.setCategory(Category.ELECTRONIC_PRODUCTS);
        auction.setOption(Option.AUCTION);

        Auction auction2 = new Auction();
        auction2.setPostBordNumber(postBoard2);
        auction2.setCategory(Category.BOOKS);
        auction2.setOption(Option.AUCTION);

        auctionTestRepository.save(auction);
        auctionTestRepository.save(auction2);

        AuctionTimeThread timeThread = new AuctionTimeThread(auction,auctionTestRepository);


        //예시 입찰금 각각 2개 생성

        BidInfo bidInfo = new BidInfo();
        bidInfo.setAuction(auction);
        bidInfo.setBid_Price(10050);
        bidInfo.setUserName("유저1");

        BidInfo bidInfo2 = new BidInfo();
        bidInfo2.setAuction(auction);
        bidInfo2.setBid_Price(12500);
        bidInfo2.setUserName("유저2");



        BidInfo bidInfo3 = new BidInfo();
        bidInfo3.setAuction(auction2);
        bidInfo3.setBid_Price(110150);
        bidInfo3.setUserName("유저3");

        BidInfo bidInfo4 = new BidInfo();
        bidInfo4.setAuction(auction2);
        bidInfo4.setBid_Price(122500);
        bidInfo4.setUserName("유저4");

        bidInfoTestRepository.save(bidInfo);
        bidInfoTestRepository.save(bidInfo2);
        bidInfoTestRepository.save(bidInfo3);
        bidInfoTestRepository.save(bidInfo4);



        System.out.println("옥션 데이터 추가");


/**
 *  이 로직은  JPARepository 용임
 auctionService.createAuction(auction);

 // 이건 입찰정보 추가로직임
 auctionService.fixed_bidV2("유저1",auction);
 auctionService.fixed_bidV2("유저2",auction);
 */


    }

}